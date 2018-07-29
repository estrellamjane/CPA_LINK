package com.erby.casestudy

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.erby.casestudy.R.id.textWelcome2
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_welcome_home.*
import com.google.firebase.database.DatabaseReference



class WelcomeHome : AppCompatActivity() {


    private var mAuth: FirebaseAuth? = null

    var database = FirebaseDatabase.getInstance()
    var myRef = database.getReference("message")


    override fun onBackPressed() {

        finish()
        startActivity(Intent(this,MainActivity::class.java))
    }
    // Read from the database

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome_home)
        val user = FirebaseAuth.getInstance().currentUser

        if(user!=null){
            val email = user.email
            val uid = user.uid

            val root = FirebaseDatabase.getInstance().reference
            val userRef = root.child("Client").child(uid)

            textWelcome2.text = email
        }

        mAuth= FirebaseAuth.getInstance()



    }
    fun signOut(view: View){
        val user = FirebaseAuth.getInstance().currentUser
        if(user!!.isEmailVerified && user != null){
            Toast.makeText(this,"Successfully Logout! ", Toast.LENGTH_LONG).show()
           mAuth!!.signOut()
            finish()
            startActivity(Intent(this,MainActivity::class.java))
        }

    }
}
