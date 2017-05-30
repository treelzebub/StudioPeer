package net.treelzebub.studiopeer.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import com.google.android.gms.auth.api.Auth
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.auth.api.signin.GoogleSignInResult
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.api.GoogleApiClient
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.activity_main.*
import net.treelzebub.knapsack.extensions.setGone
import net.treelzebub.studiopeer.R
import net.treelzebub.studiopeer.TAG
import net.treelzebub.studiopeer.activity.chat.StudioPeerChatActivity
import net.treelzebub.studiopeer.auth.AuthState
import net.treelzebub.studiopeer.auth.StudioPeerAuth
import net.treelzebub.studiopeer.view.onNextLayout
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

class MainActivity : StudioPeerActivity(), GoogleApiClient.OnConnectionFailedListener {

    companion object {
        private const val REQUEST_SIGN_IN = 0x13
    }

    private val gso by lazy {
        GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build()
    }
    private val googleApiClient by lazy {
        GoogleApiClient.Builder(this)
                .enableAutoManage(this, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (AuthState.isAuthed) {
            hideSignIn()
            startActivity<StudioPeerChatActivity>()
            toast("Signed In!")
        }

        sign_in.setOnClickListener {
            val signInIntent = Auth.GoogleSignInApi.getSignInIntent(googleApiClient)
            startActivityForResult(signInIntent, REQUEST_SIGN_IN)
        }
        button.setOnClickListener {
            startActivity<StudioPeerChatActivity>()
        }
        sign_out.setOnClickListener {
            StudioPeerAuth.logOut()
        }
    }

    override fun onConnectionFailed(result: ConnectionResult) {
        toast("Network failure.")
    }

    private fun firebaseAuthWithGoogle(account: GoogleSignInAccount?) {
        Log.d(TAG, "firebaseAuthWithGoogle:" + (account?.id ?: "null-id"))
        val credential = GoogleAuthProvider.getCredential(account?.idToken, null)
        FirebaseAuth.getInstance().signInWithCredential(credential)
                .addOnCompleteListener(this) {
                    task ->
                    Log.d(TAG, "signInWithCredential:onComplete:" + task.isSuccessful)

                    // If sign in fails, display a message to the user. If sign in succeeds
                    // the auth state listener will be notified and logic to handle the
                    // signed in user can be handled in the listener.
                    if (!task.isSuccessful) {
                        Log.w(TAG, "signInWithCredential", task.exception)
                        toast("Authentication failed.")
                    }
                    // ...
                }
    }

    private fun handleSignInResult(result: GoogleSignInResult) {
        Log.d(TAG, "Sign in successful: ${result.isSuccess}")
        if (!result.isSuccess) Log.d(TAG, result.status.statusMessage)
        result.signInAccount?.let {
            Log.d(TAG, "Got user. Logging into Firebase.")
            firebaseAuthWithGoogle(it)
            hideSignIn()
        } ?: toast("Sign in failed.")
    }

    public override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_SIGN_IN) {
            val result = Auth.GoogleSignInApi.getSignInResultFromIntent(data)
            handleSignInResult(result)
        }
    }

    private fun hideSignIn() = onNextLayout { sign_in.setGone() }

}