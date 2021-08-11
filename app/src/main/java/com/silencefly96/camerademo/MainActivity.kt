package com.silencefly96.camerademo

import android.Manifest
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.permissionx.guolindev.PermissionX
import com.silencefly96.camerademo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //权限申请
        PermissionX.init(this)
            .permissions(
                Manifest.permission.CAMERA,
                Manifest.permission.RECORD_AUDIO,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            )
            .request { allGranted, grantedList, deniedList ->
                if (allGranted) {
                    //Toast.makeText(this, "所有申请的权限都已通过", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "您拒绝了如下权限：$deniedList", Toast.LENGTH_SHORT).show()
                }
            }
    }

    fun takePhotoBySystem(v: View) {
        startActivity(Intent(this, SystemActivity::class.java))
    }

    fun takePhotoByCustom(v: View) {
        startActivity(Intent(this, CustomActivity::class.java))
    }

    fun takePhotoByCameraX(v: View) {
        startActivity(Intent(this, CameraXActivity::class.java))
    }

}