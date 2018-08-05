package com.erby.casestudy

//In Layers of Programming Language, merong Machine, Assembly, High-Level and Low-Level Programming Language
//Ang High Level Programming Language ay ginagamit para sa pagbuo ng program,
//ang halimbawa nito ay C++, C#, JAVA, C, Visual Basic, and J#.
//Habang ang low-level programming language naman ay ginagamit sa pagddesign.
//Machine Language ay ang standard language na ginagamit ng computer upang magprocess ng mga data, ito ay ang binary.
//Assembly naman ang kombinasyon ng High Level at Low Level Programming Language.

import android.app.ProgressDialog //naglalaman ng high-level classes na maaaring matawag para sa pagbuo ng Android Application model, ang tinawag na class dito ay ProgressDialog
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import kotlinx.android.synthetic.main.activity_main.*
import com.google.firebase.auth.FirebaseAuth
import android.graphics.Color.parseColor
import android.graphics.Color.parseColor
import com.ontbee.legacyforks.cn.pedant.SweetAlert.SweetAlertDialog
import com.google.firebase.auth.FirebaseUser
import android.net.NetworkInfo
import android.content.Context.CONNECTIVITY_SERVICE
import android.graphics.Color
import android.net.ConnectivityManager
import com.erby.casestudy.R.id.emailaddress
import android.graphics.Color.parseColor
import android.support.v4.view.GravityCompat
import kotlinx.android.synthetic.main.activity_home.*
//import means dalhin o ikonek, we are trying to make a connection here, tinatawag natin ang mga resource classes na ginagamit upang makabuo ng isang application


class MainActivity : AppCompatActivity() {


    private var mAuth: FirebaseAuth? = null

    var mAuthListener: FirebaseAuth.AuthStateListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mAuth = FirebaseAuth.getInstance()

    }

    override fun onStop() {
        super.onStop()
        finish()
        val currentUser = mAuth!!.currentUser
        //updateUI(currentUser)

    }

    fun register(view: View) {
        startActivity(Intent(this, registration::class.java))
    }

    fun login(view: View) {


        val email = emailaddress.text.toString()
        val password = password.text.toString()
        val pDialog = SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE)
        pDialog.progressHelper.barColor = Color.parseColor("#A5DC86")
        pDialog.titleText = "Loading"
        pDialog.setCancelable(false)

        if (!email.isEmpty() &&
                !password.isEmpty()) {
            pDialog.show()
            mAuth!!.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, OnCompleteListener { task ->
                        pDialog.dismiss()
                        if (task.isSuccessful) {
                            startActivity(Intent(this, registration::class.java))
                            //send email verification
                            val user = FirebaseAuth.getInstance().currentUser
                            if (user!!.isEmailVerified) {
                                welcomeMessage()
                                finish()
                            } else if (!(user.isEmailVerified)) {
                                warningAccountVerify()
                            }
                        } else {
                            warningInternet()
                        }
                    })


        } //else if(!isEmail()){//execute error message
            //password.setError(getString(R.string.err_pass))
           // texinemail.error = getString(R.string.err_email)
            //texinpass.error = getString(R.string.err_pass)
            //emailaddress.setText(Html.fromHtml("7<sup>2</sup>")) exponent mode
            return

        //}

    }


    fun welcomeMessage() {

        startActivity(Intent(this, HomeActivity::class.java))
        SweetAlertDialog(this, SweetAlertDialog.SUCCESS_TYPE)
                .setTitleText("Successfully Login !")
                .show()
    }

    fun warningInternet(){
        SweetAlertDialog(this, SweetAlertDialog.WARNING_TYPE)
                .setTitleText("Please Check your internet Access ..")
                .show()
    }
    fun warningAccountVerify(){
        SweetAlertDialog(this, SweetAlertDialog.WARNING_TYPE)
                .setTitleText("Check your email for verification ..")
                .show()
    }


    private fun isEmail():Boolean {

        val password = password.text.toString()
        val email = emailaddress.text.toString()
        if(email.isEmpty()){
            texinemail.error = getString(R.string.err_email)
            emailaddress.requestFocus()
            return false
        }
        else{
            texinemail.isErrorEnabled = false
            return true

        }
    }
    private fun isPassword():Boolean {
        val password = password.text.toString()
        if(password.isEmpty()){
            texinpass.error = getString(R.string.err_pass)
          ///  password.requestFocus()
            return false
        }
        else{
            texinpass.isErrorEnabled = false
            return true

        }
    }





}

