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


    override fun onBackPressed() { //if clinick ng current user ang 'back button'

        finish()  //ang finish method ay para matawag ang onDestroy method, binubura nya ang activities na naganap once na tinawag
        //ginagamit ang finish() upang hindi na magkaroon pa ng access ang iba once na matrigger, 
        //for example, may ibang gagamit ng app, nag-log-out na muna si user, yung bagong gagamit ng app hindi na sya magkakaroon pa ng access sa account ni main user kahit na iclick nya ang back button.
        startActivity(Intent(this,MainActivity::class.java)) //after ng finish, nag-initiate dito ng panibagong activity gamit ang startActivity,
        //gumamit ng Intent class to initiate yung bagong activity na ipperform
        //so ang ibig sabihin ay after mag log-out ni user , magreredirect ang program sa MainAtivity::class.java
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
        val user = FirebaseAuth.getInstance().currentUser //ivvalidate ang current account ni user na illog-out
        if(user!!.isEmailVerified && user != null){ //if the user's email is verified
            Toast.makeText(this,"Successfully Logout! ", Toast.LENGTH_LONG).show() //an alert message will prompt/lalabas once na maayos na nakapag-log-out si user
            
           mAuth!!.signOut() //amethod to succesfully sign out the current account of user
            finish() //ang finish method ay para matawag ang onDestroy method, binubura nya ang activities na naganap once na tinawag
            //ginagamit ang finish() upang hindi na magkaroon pa ng access ang iba once na matrigger, 
            //for example, may ibang gagamit ng app, nag-log-out na muna si user, yung bagong gagamit ng app hindi na sya magkakaroon pa ng access sa account ni main user kahit na iclick nya ang back button.
            startActivity(Intent(this,MainActivity::class.java)) //after ng finish, nag-initiate dito ng panibagong activity gamit ang startActivity,
            //gumamit ng Intent class to initiate yung bagong activity na ipperform
            //so ang ibig sabihin ay after mag log-out ni user , magreredirect ang program sa MainAtivity::class.java
        }

    }
}
