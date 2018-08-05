package com.erby.casestudy

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.AuthResult
import kotlinx.android.synthetic.main.activity_main.*
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_registration.*
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.auth.FirebaseUser
//import jdk.nashorn.internal.runtime.ECMAException.getException
//import org.junit.experimental.results.ResultMatchers.isSuccessful
import android.support.annotation.NonNull
import android.support.v4.app.FragmentActivity
import android.util.Log
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import android.app.ProgressDialog
import android.support.v4.view.GravityCompat
import kotlinx.android.synthetic.main.activity_home.*


class registration : AppCompatActivity() {
    private var mAuth: FirebaseAuth? = null
    private val TAG = "FirebaseEmailPassword"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)
        mAuth = FirebaseAuth.getInstance()
    }

    override fun onStart() {
        super.onStart()

        val currentUser = mAuth!!.currentUser

        if(currentUser==null){

        }
        else
        {

        }

        //updateUI(currentUser)
    }


    fun createAccount2(view:View) {
        if(lastname.text.toString().isEmpty() && firstname.text.toString().isEmpty() &&
                businessAddress.text.toString().isEmpty() && emailAddress.text.toString().isEmpty() &&
                tinNumber.text.toString().isEmpty() && password1.text.toString().isEmpty() && password2.text.toString().isEmpty()){
            lastname.error = "Required" //if the lastname is empty, magpprompt or mag-aalert si error message
            firstname.error = "Required" //if the firstname is empty, magpprompt or mag-aalert si error message
            businessAddress.error = "Required" //if the Business Address is empty, magpprompt or mag-aalert si error message
            emailAddress.error = "Required" //if the Email Address is empty, magpprompt or mag-aalert si error message
            tinNumber.error = "Required" //if the Tin Number is empty, magpprompt or mag-aalert si error message
            password1.error = "Required" //if the Password is empty, magpprompt or mag-aalert si error message
            password2.error = "Required" //if the Second Password is empty, magpprompt or mag-aalert si error message
        }else if(lastname.text.toString().isEmpty()){
            lastname.error = "Required" //if the lastname is empty, magpprompt or mag-aalert si error message
        }else if(firstname.text.toString().isEmpty()){
            firstname.error = "Required" //if the firstname is empty, magpprompt or mag-aalert si error message
        }else if(businessAddress.text.toString().isEmpty()){
            businessAddress.error = "Required" //if the Business Address is empty, magpprompt or mag-aalert si error message
        }else if(emailAddress.text.toString().isEmpty()){
            emailAddress.error = "Required" //if the Email Address is empty, magpprompt or mag-aalert si error message
        }else if(tinNumber.text.toString().isEmpty()){
            tinNumber.error = "Required" //if the Tin Number is empty, magpprompt or mag-aalert si error message
        }else if(password1.text.toString().isEmpty()){
            password1.error = "Required" //if the Password is empty, magpprompt or mag-aalert si error message
        }else if(password2.text.toString().isEmpty()){
            password2.error = "Required" //if the Second Password is empty, magpprompt or mag-aalert si error message
        } else if(password1.text.toString().length<6){
            password1.error = "Password should be atleast 6 characters and above !" //kapag kulang ang characters na ininput ni user, mag-aalert ang error message
            password1.requestFocus()
        }else if(!password1.text.toString().contentEquals(password2.text.toString())){
            password2.error = "Invalid input ! Password mismatch" //kapag mali ang inilagay ni user na password, ang system ay magpprompt ng "Invalid Input!"
            password2.requestFocus()
        }else{
      val prog= ProgressDialog.show(this,"Please Wait .. ","Processing..",true) //kapag walang maling input si user, a progress dialog will pop

        val email = emailAddress.text.toString()
        val password = password1.text.toString()
        val lname= lastname.text.toString()
        val fname =firstname.text.toString()
        val badd = businessAddress.text.toString()
        val tin = tinNumber.text.toString()
        if(!lastname.text.toString().isEmpty() && !firstname.text.toString().isEmpty() &&
                !businessAddress.text.toString().isEmpty() && !emailAddress.text.toString().isEmpty() &&
                !tinNumber.text.toString().isEmpty() && !password1.text.toString().isEmpty() && !password2.text.toString().isEmpty()){
        mAuth!!.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    prog.dismiss()
                    if (task.isSuccessful) {

                        Log.e(TAG, "createAccount: Success!")
                        // update UI with the signed-in user's information
                        ///
                        val client =info1(lastname.text.toString().trim(),firstname.text.toString().trim(),businessAddress.text.toString().trim(),
                                tinNumber.text.toString().trim())
                        FirebaseDatabase.getInstance().getReference("Client").child(FirebaseAuth.getInstance().currentUser!!.uid).setValue(client)
                        FirebaseAuth.getInstance().currentUser!!.sendEmailVerification()
                            Toast.makeText(applicationContext,"Successfully save !",Toast.LENGTH_LONG).show()
                            clearInfo()
                            Toast.makeText(this,"Check your email for verification! ",Toast.LENGTH_LONG).show()
                            finish()
                        //

                    } else {

                        Log.e(TAG, "createAccount: Fail!", task.exception)
                        Toast.makeText(applicationContext, "Check Internet Connection !", Toast.LENGTH_SHORT).show()
                       // updateUI(null)
                    }
                }
        }else
        {

        }

        }// check inputs
    }

    //on the above condition, pinapakita dito na kapag mali or walang input si user, they can't proceed sa susunod na parte ng registration
    //yun ay ang pag-verify ni user ng account nya sa email nya
    //a verification code will be sent kay user once na maayos nyang naisagawa ang pagreregister.


    fun createAccount(view: View){
       // mAuth.createUserWithEmailAndPassword(emailAddress.text.toString().trim(), password1.text.toString().trim())
        if(!lastname.text.toString().isEmpty() && !firstname.text.toString().isEmpty() &&
                !businessAddress.text.toString().isEmpty() && !emailAddress.text.toString().isEmpty() &&
                !tinNumber.text.toString().isEmpty() && !password1.text.toString().isEmpty() && !password2.text.toString().isEmpty()){

            //mAuth.createUserWithEmailAndPassword(emailAddress.text.toString().trim(),password1.text.toString().trim()).addOnCompleteListener {new onCompleteListener<AuthResult>


            //}
            val email= emailAddress.text.toString().trim()
            val password = password1.text.toString().trim()
            mAuth!!.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            Log.e(TAG, "createAccount: Success!")

                            // update UI with the signed-in user's information
                            val user = mAuth!!.currentUser
                            //  updateUI(user)
                        } else {
                            Log.e(TAG, "createAccount: Fail!", task.exception)
                            Toast.makeText(applicationContext, "Authentication failed!", Toast.LENGTH_SHORT).show()
                            // updateUI(null)
                        }
                    }
                Toast.makeText(applicationContext,"Successfully save !",Toast.LENGTH_LONG).show()
                clearInfo()
            }

        }


    fun checkInputs(){
        if(lastname.text.toString().isEmpty() && firstname.text.toString().isEmpty() &&
                businessAddress.text.toString().isEmpty() && emailAddress.text.toString().isEmpty() &&
                tinNumber.text.toString().isEmpty() && password1.text.toString().isEmpty() && password2.text.toString().isEmpty()){
            lastname.error = "Required"
            firstname.error = "Required"
            businessAddress.error = "Required"
            emailAddress.error = "Required"
            tinNumber.error = "Required"
            password1.error = "Required"
            password2.error = "Required"
        }else if(lastname.text.toString().isEmpty()){
            lastname.error = "Required"
        }else if(firstname.text.toString().isEmpty()){
            firstname.error = "Required"
        }else if(businessAddress.text.toString().isEmpty()){
            businessAddress.error = "Required"
        }else if(emailAddress.text.toString().isEmpty()){
            emailAddress.error = "Required"
        }else if(tinNumber.text.toString().isEmpty()){
            tinNumber.error = "Required"
        }else if(password1.text.toString().isEmpty()){
            password1.error = "Required"
        }else if(password2.text.toString().isEmpty()){
            password2.error = "Required"
        } else if(password1.text.toString().length<6){
            password1.error = "Password should be atleast 6 characters and above !"
            password1.requestFocus()
        }else if(!password1.text.toString().contentEquals(password2.text.toString())){
            password2.error = "Invalid input ! Password mismatch"
            password2.requestFocus()
        }
    //on the above condition, pinapakita dito na kapag mali or walang input si user, they can't proceed sa susunod na parte ng registration
    //yun ay ang pag-verify ni user ng account nya sa email nya
    //a verification code will be sent kay user once na maayos nyang naisagawa ang pagreregister.

    }
    fun clearInfo(){
        lastname.text.clear()
        firstname.text.clear()
        businessAddress.text.clear()
        emailAddress.text.clear()
        tinNumber.text.clear()
        password1.text.clear()
        password2.text.clear()


    }

}
