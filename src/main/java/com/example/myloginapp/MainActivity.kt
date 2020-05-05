package com.example.myloginapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_register.*

class MainActivity : AppCompatActivity() {
    //Connection to the database
    val mAuth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val loginTxt = findViewById<View>(R.id.mBtnLogin) as Button
        val regTxt = findViewById<View>(R.id.mBtnRegister) as TextView

        mBtnLogin.setOnClickListener(View.OnClickListener{
                view -> login()
        })
        mBtnRegister.setOnClickListener(View.OnClickListener{
                view -> register()
        })
    }

    private fun register() {
        //Takes the user to the register page
        startActivity(Intent(this,Register::class.java))

    }

    private fun login() {
        val emailTxt = findViewById<View>(R.id.mEdtEmail) as EditText
        val passwordTxt = findViewById<View>(R.id.mEdtPassowrd) as EditText

        var email = emailTxt.text.toString()
        var password = passwordTxt.text.toString()
        //check if user has entered email and password
        if (!email.isEmpty() && !password.isEmpty()){
            mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(this,
                OnCompleteListener { task ->
                    if (task.isSuccessful){
                        startActivity(Intent(this,Timeline::class.java))
                        Toast.makeText(this,"You have Successfully logged in",Toast.LENGTH_LONG).show()
                    }else{
                        Toast.makeText(this,"incorrect email or password!!",Toast.LENGTH_LONG).show()
                    }
                })

        }else{
            Toast.makeText(this,"Fill out all the credentials!!",Toast.LENGTH_LONG).show()
        }
    }
}