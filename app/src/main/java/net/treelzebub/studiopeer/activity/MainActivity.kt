package net.treelzebub.studiopeer.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.google.android.gms.auth.api.Auth
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.auth.api.signin.GoogleSignInResult
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.api.GoogleApiClient
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import net.treelzebub.studiopeer.R
import net.treelzebub.studiopeer.TAG
import net.treelzebub.studiopeer.activity.tracklist.TracklistActivity
import net.treelzebub.studiopeer.bindView

val FirebaseAuth.isAuthed: Boolean
    get() = currentUser != null

class MainActivity : StudioPeerActivity(false), GoogleApiClient.OnConnectionFailedListener {

    companion object {
        private const val REQUEST_SIGN_IN = 13
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

    private val button by bindView<View>(R.id.button)
    private val signIn by bindView<View>(R.id.sign_in_button)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (auth.isAuthed) {
            signIn.visibility = View.GONE
            Toast.makeText(this, "Signed In!", Toast.LENGTH_SHORT).show()
        }

        signIn.setOnClickListener {
            val signInIntent = Auth.GoogleSignInApi.getSignInIntent(googleApiClient)
            startActivityForResult(signInIntent, REQUEST_SIGN_IN)
        }
        button.setOnClickListener {
            startActivity(Intent(this, TracklistActivity::class.java))
        }
    }

    override fun onConnectionFailed(result: ConnectionResult) {
    }

    private fun firebaseAuthWithGoogle(account: GoogleSignInAccount?) {
        Log.d(TAG, "firebaseAuthWithGoogle:" + (account?.id ?: "null-id"))
        val credential = GoogleAuthProvider.getCredential(account?.idToken, null)
        auth.signInWithCredential(credential)
                .addOnCompleteListener(this) {
                    task ->
                    Log.d(TAG, "signInWithCredential:onComplete:" + task.isSuccessful)

                    // If sign in fails, display a message to the user. If sign in succeeds
                    // the auth state listener will be notified and logic to handle the
                    // signed in user can be handled in the listener.
                    if (!task.isSuccessful) {
                        Log.w(TAG, "signInWithCredential", task.exception)
                        Toast.makeText(this, "Authentication failed.", Toast.LENGTH_SHORT).show()
                    }
                    // ...
                }
    }

    private fun handleSignInResult(result: GoogleSignInResult) {
        Log.d(TAG, "Sign in successful: ${result.isSuccess}")
        result.signInAccount?.let {
            Log.d(TAG, it.displayName + " " + it.email)
            firebaseAuthWithGoogle(it)
        } ?: Toast.makeText(this, "Sign in failed.", Toast.LENGTH_SHORT).show()
    }

    public override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_SIGN_IN) {
            val result = Auth.GoogleSignInApi.getSignInResultFromIntent(data)
            handleSignInResult(result)
        }
    }
}