package net.treelzebub.studiopeer.storage

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Environment
import android.util.Log
import kotlinx.android.synthetic.main.activity_test_storage.*
import net.treelzebub.studiopeer.R
import net.treelzebub.studiopeer.activity.StudioPeerActivity
import easyfilepickerdialog.kingfisher.com.library.view.FilePickerDialogFragment
import easyfilepickerdialog.kingfisher.com.library.model.SupportFile
import easyfilepickerdialog.kingfisher.com.library.model.DialogConfig
import org.jetbrains.anko.toast
import java.io.File


/**
 * Created by Tre Murillo on 5/29/17
 */
class StorageTestActivity : StudioPeerActivity() {

    private var file: File? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_storage)

        upload.setOnClickListener {
            if (file == null) {
                toast("Must select file first.")
            } else {
                toast("Uploading ${file!!.name}")
                StudioPeerStorage.upload(file!!, null).addOnProgressListener {
                    val percentDone = (it.bytesTransferred / it.totalByteCount) * 100
                    progress.text = "Uploaded $percentDone%"
                }.addOnCompleteListener {
                    toast("Upload Complete. Success: ${it.isSuccessful}")
                }.addOnFailureListener {
                    toast("Failure. ${it.cause?.message}")
                }
            }
        }

        select.setOnClickListener {
            selectionDialog()
        }
    }

    private fun selectionDialog() {
        val dialogConfig = DialogConfig.Builder()
                .enableMultipleSelect(false)
                .initialDirectory(Environment.getExternalStorageDirectory().absolutePath + File.separator + "Android")
                .supportFiles(SupportFile(".mp3", 0))
                .build()
        FilePickerDialogFragment.Builder()
                .configs(dialogConfig)
                .onFilesSelected {
                    files ->
                    this.file = files.first()
                    dir.text = file!!.absolutePath
                    filename.text = file!!.name
                    upload.isClickable = true
                }.build().show(supportFragmentManager, null)
    }
}
