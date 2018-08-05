package com.erby.casestudy

//In Layers of Programming Language, merong Machine, Assembly, High-Level and Low-Level Programming Language
//Ang High Level Programming Language ay ginagamit para sa pagbuo ng program,
//ang halimbawa nito ay C++, C#, JAVA, C, Visual Basic, and J#.
//Habang ang low-level programming language naman ay ginagamit sa pagddesign.
//Machine Language ay ang standard language na ginagamit ng computer upang magprocess ng mga data, ito ay ang binary.
//Assembly naman ang kombinasyon ng High Level at Low Level Programming Language.

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
//import means dalhin o ikonek, we are trying to make a connection here, tinatawag natin ang mga resource classes na ginagamit upang makabuo ng isang application.

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
            lastname.error = "Required" //if the last name is empty, magpprompt or mag-aalert si error message
            firstname.error = "Required" //if the first name is empty, magpprompt or mag-aalert si error message
            businessAddress.error = "Required" //if the Business Address is empty, magpprompt or mag-aalert si error message
            emailAddress.error = "Required" //if the Email Adress is empty, magpprompt or mag-aalert si error message
            tinNumber.error = "Required" //if the Tin Number is empty, magpprompt or mag-aalert si error message
            password1.error = "Required" //if the Password is empty, magpprompt or mag-aalert si error message
            password2.error = "Required" //if the Second Password is empty, magpprompt or mag-aalert si error message
        }else if(lastname.text.toString().isEmpty()){
            lastname.error = "Required" //if the last name is empty, magpprompt or mag-aalert si error message
        }else if(firstname.text.toString().isEmpty()){
            firstname.error = "Required" //if the first name is empty, magpprompt or mag-aalert si error message
        }else if(businessAddress.text.toString().isEmpty()){
            businessAddress.error = "Required" //if the Business Address is empty, magpprompt or mag-aalert si error message
        }else if(emailAddress.text.toString().isEmpty()){
            emailAddress.error = "Required" //if the Email Adress is empty, magpprompt or mag-aalert si error message
        }else if(tinNumber.text.toString().isEmpty()){
            tinNumber.error = "Required"  //if the Tin Number is empty, magpprompt or mag-aalert si error message
        }else if(password1.text.toString().isEmpty()){
            password1.error = "Required" //if the Password is empty, magpprompt or mag-aalert si error message
        }else if(password2.text.toString().isEmpty()){
            password2.error = "Required" //if the Second Password is empty, magpprompt or mag-aalert si error message
        } else if(password1.text.toString().length<6){
            password1.error = "Password should be atleast 6 characters and above !" //kapag ang length ng characters na ininput ni user ay hindi umabot sa 6 characters, magpprompt or mag-aalert si error message 
            password1.requestFocus() //once na nag-error at after lumabas ni error message, magsstay lang ang focus sa current editText
            //hindi na kelangan pang magpipindot-pindot ni user para lang ma-access muli yung ffill-upan
        }else if(!password1.text.toString().contentEquals(password2.text.toString())){
            password2.error = "Invalid input ! Password mismatch"//kapag hindi kaparehas ng naunang password,  magpprompt or mag-aalert si error message
            password2.requestFocus() //once na nag-error at after lumabas ni error message, magsstay lang ang focus sa current editText
            //hindi na kelangan pang magpipindot-pindot ni user para lang ma-access muli yung ffill-upan
        }
    //on the above condition, pinapakita dito na kapag mali or walang input si user, they can't proceed sa susunod na parte ng registration
    //yun ay ang pag-verify ni user ng account nya sa email nya
    //a verification code will be sent kay user once na maayos nyang naisagawa ang pagreregister.

    }
    fun clearInfo(){ //ma-clear ang mga current information na nakalagay sa input area 
        lastname.text.clear() //clears the current lastname
        firstname.text.clear() //clears the current firstname
        businessAddress.text.clear() //clears the current Business Address
        emailAddress.text.clear() //clears the current Email Address
        tinNumber.text.clear() //clears the current Tin Number
        password1.text.clear() //clears the current password
        password2.text.clear() //clears the current second password


    }

}
