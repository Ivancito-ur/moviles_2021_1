package com.ufps.movil2021_1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    val TAG: String = "MainActivity"
    // ...
// Initialize Firebase Auth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val user:TextInputEditText=findViewById(R.id.user)
        val pass:TextInputEditText=findViewById(R.id.pass)
        val sesion:Button=findViewById(R.id.sesion)
        val create_user: TextView=findViewById(R.id.create_user)
        val repair_pass: TextView=findViewById(R.id.repair_pass)
        auth = FirebaseAuth.getInstance()
        sesion.setOnClickListener {
            start(user.text.toString(), pass.text.toString())
        }

    }
    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
        if(currentUser != null){
            //reload();
        }
    }
    public fun start(email:String, password:String){
        auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d(TAG, "signInWithEmail:success")
                        Toast.makeText(baseContext, "Authentication Successful.",
                                Toast.LENGTH_SHORT).show()
                        val user = auth.currentUser
                        goHome()
                        //updateUI(user)
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w(TAG, "signInWithEmail:failure", task.exception)
                        Toast.makeText(baseContext, "Authentication failed.",
                                Toast.LENGTH_SHORT).show()
                        //updateUI(null)
                    }
                }
    }

    public fun goHome(){
        val intent = Intent(this,HomeActivity::class.java)
        startActivity(intent)
    }
    public fun goHome1(){
        val intent = Intent(this,HomeActivity::class.java)
        startActivity(intent)
    }
}
