package com.ufps.movil2021_1

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import com.google.android.gms.auth.api.signin.internal.Storage
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import java.io.File
import kotlin.math.log

class SubidaImagenActivity : AppCompatActivity() {

    lateinit var subirImagen :ImageView
    var database = FirebaseDatabase.getInstance()
    var myRef = database.getReference()
    var file = 1



    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_subida_imagen)
        subirImagen = findViewById(R.id.uploadImage)
        subirImagen.setOnClickListener{
            //fileUpload()
        }
    }
    fun fileUpload(imagen : ImageView){
        this.subirImagen = imagen
        //setContentView(R.layout.activity_registrar_medicine)
        val intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.type = "*/*"
        println(file)
        print(intent.toString())
        startActivityForResult(intent,file)

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == file){
            if (resultCode == RESULT_OK){
                val fileUri = data!!.data
                val folder : StorageReference = FirebaseStorage.getInstance().getReference().child("medicinas")
                val file_name: StorageReference = folder.child("file"+fileUri!!.lastPathSegment)
                file_name.putFile(fileUri).addOnSuccessListener {
                    file_name.downloadUrl.addOnSuccessListener {
                        val hashMap = HashMap<String,String>()
                        hashMap["link"] = java.lang.String.valueOf(it)
                        myRef.setValue(hashMap)
                        Log.d("Mensaje que dejo","se subió correctamente")
                    }
                }
            }
        }
    }
}