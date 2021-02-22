package com.testapp.downloadmanager

import android.Manifest
import android.app.Activity
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Environment
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.testapp.downloadmanager.databinding.ActivityMainBinding
import com.testapp.downloadmanager.manager.DownloadManager
import java.io.File
import java.text.SimpleDateFormat
import java.util.*


class MainActivity : AppCompatActivity() {

    var REQUEST_CODE_WRITE_STORAGE_PERMISION = 105
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.btn?.setOnClickListener {
            checkStoragePermissions(this)
        }
    }

    private fun checkStoragePermissions(activity: Activity) {
        if (ContextCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            if (ContextCompat.checkSelfPermission(activity,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                // Should we show an explanation?
                if (ActivityCompat.shouldShowRequestPermissionRationale(activity,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                    ActivityCompat.requestPermissions(activity,
                        arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                        REQUEST_CODE_WRITE_STORAGE_PERMISION)
                } else {
                    // No explanation needed, we can request the permission.
                    ActivityCompat.requestPermissions(activity,
                        arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                        REQUEST_CODE_WRITE_STORAGE_PERMISION)
                }
            }
        } else {
            if (ContextCompat.checkSelfPermission(activity,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {

                if (ContextCompat.checkSelfPermission(activity,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    // Should we show an explanation?
                    if (ActivityCompat.shouldShowRequestPermissionRationale(activity,
                            Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                    } else {
                        // No explanation needed, we can request the permission.
                        ActivityCompat.requestPermissions(activity, arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE), REQUEST_CODE_WRITE_STORAGE_PERMISION)
                    }
                }
            } else {
                val folder = File(Environment.getExternalStorageDirectory().toString() + "/" + "HelloWorld")
                if (!folder.exists()) {
                    folder.mkdirs()
                }
                val fileName = SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(Date()) + ".mp3"
                val urlOfTheFile = "https://sample-videos.com/audio/mp3/wave.mp3"
                DownloadManager.initDownload(this, urlOfTheFile, folder.absolutePath, fileName)
            }
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            REQUEST_CODE_WRITE_STORAGE_PERMISION -> {
                if (grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    val folder = File(Environment.getExternalStorageDirectory().toString() + "/" + "HelloWorld")
                    if (!folder.exists()) {
                        folder.mkdirs()
                    }
                    val fileName = SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(Date()) + ".mp3"
                    val urlOfTheFile = "https://sample-videos.com/audio/mp3/wave.mp3"
                    DownloadManager.initDownload(this, urlOfTheFile, folder.absolutePath, fileName)
                }
            }

        }
    }
}
