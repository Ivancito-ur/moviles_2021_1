package com.ufps.movil2021_1

import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.database.FirebaseDatabase

class RegisterMedicine : AppCompatActivity() {
    lateinit var titulo : TextInputEditText
    lateinit var descripcion : TextInputEditText
    lateinit var sintomas : TextInputEditText
    lateinit var imagen : TextInputEditText
    lateinit var guardar : Button



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registrar_medicine)
        titulo = findViewById(R.id.registro_medicina)
        descripcion= findViewById(R.id.registro_descripcion)
        sintomas = findViewById(R.id.registro_sintomas)
        imagen = findViewById(R.id.registro_imagen)
        guardar = findViewById(R.id.registro_boton)
        guardar.setOnClickListener{
            registrarMedicina()
        }
    }

    fun registrarMedicina(){
        val database = FirebaseDatabase.getInstance()
        val myRef = database.reference
        val medicina = MedicineEntity(
                myRef.push().key.toString(),
                titulo.text.toString(),
                descripcion.text.toString(),
                sintomas.text.toString(),
                imagen.text.toString()
        )
        myRef.child("medicine").child(medicina.id.toString()).setValue(medicina)
        finish()
    }

}
