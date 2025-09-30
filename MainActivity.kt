package com.app.image_captureandgallery

import android.app.Activity

import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.ImageView
import java.net.URI


class MainActivity : AppCompatActivity() {
     val REQUSET_CODE = 1
    val gallery_code = 2
    private var imageURI : Uri? = null
    private lateinit var btncamera :Button
    private lateinit var imageView: ImageView
    private lateinit var btngallery: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btncamera = findViewById(R.id.btn_camera)
        imageView = findViewById(R.id.imageview)
        btngallery = findViewById(R.id.btn_gallery)

        btncamera.setOnClickListener{
            val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivityForResult(cameraIntent,REQUSET_CODE)
        }
        btngallery.setOnClickListener {
            val pick_image_from_gallery = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
            startActivityForResult(pick_image_from_gallery,gallery_code)
        }


    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == REQUSET_CODE && data!= null){
            imageView.setImageBitmap(data.extras!!.get("data") as Bitmap )
        }
        if (resultCode == Activity.RESULT_OK && requestCode == gallery_code ) {

            imageURI = data?.data
            imageView.setImageURI(imageURI)
        }

    }




}