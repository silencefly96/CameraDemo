package com.silencefly96.camerademo

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.silencefly96.camerademo.databinding.ActivitySystemBinding
import java.io.File
import java.text.SimpleDateFormat
import java.util.*


class SystemActivity : AppCompatActivity() {

    companion object{
        const val REQUEST_VIDEO_CAPTURE = 1
    }

    private lateinit var binding: ActivitySystemBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySystemBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    fun take(v: View) {
        val fileUri = Uri.fromFile(getOutputMediaFile());
        Intent(MediaStore.ACTION_VIDEO_CAPTURE).also { takeVideoIntent ->
            //判断是否有activity能处理次intent
            takeVideoIntent.resolveActivity(packageManager)?.also {
                intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);
                intent.putExtra(MediaStore.EXTRA_DURATION_LIMIT, 10); //限制的录制时长 以秒为单位
                //intent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY,1); //设置拍摄的质量最小是0，最大是1（建议不要设置中间值，不同手机似乎效果不同。。。）
                //intent.putExtra(MediaStore.EXTRA_SIZE_LIMIT, 1024 * 1024);//限制视频文件大小 以字节为单位
                startActivityForResult(takeVideoIntent, REQUEST_VIDEO_CAPTURE)
            }
        }
    }

    @SuppressLint("SimpleDateFormat")
    private fun getOutputMediaFile(): File? {
        if(!Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
            Toast.makeText(this, "请检查SDCard！", Toast.LENGTH_SHORT).show();
            return null;
        }

        val mediaStorageDir = File(
            Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM),
            "CameraDemo"
        )

        if (!mediaStorageDir.exists()) {
            mediaStorageDir.mkdirs()
        }

        // Create a media file name
        val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
        val mediaFile = File(mediaStorageDir.getPath() + File.separator + "VID_" + timeStamp + ".mp4");
        return mediaFile;
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != RESULT_OK) {
            return;
        }
        when(requestCode) {
            REQUEST_VIDEO_CAPTURE -> {
                binding.video.setVideoURI(data?.data)
                binding.video.start()

//                try {
//                    val videoAsset =
//                        data?.getData()?.let { contentResolver.openAssetFileDescriptor(it, "r") }
//                    val fis: FileInputStream = videoAsset!!.createInputStream()
//                    val tmpFile = File(Environment.getExternalStorageDirectory(), "VideoFile.mp4")
//                    val fos = FileOutputStream(tmpFile)
//                    val buf = ByteArray(1024)
//                    var len: Int
//                    while (fis.read(buf).also { len = it } > 0) {
//                        fos.write(buf, 0, len)
//                    }
//                    fis.close()
//                    fos.close()
//                } catch (io_e: IOException) {
//                    io_e.printStackTrace()
//                }
            }
        }
    }
}