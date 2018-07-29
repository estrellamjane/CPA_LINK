package com.erby.casestudy

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.activity_welcome_home.*
import kotlinx.android.synthetic.main.app_bar_home.*

class HomeActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {


    private var mAuth: FirebaseAuth? = null

    var database = FirebaseDatabase.getInstance()
    var myRef = database.getReference("message")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        setSupportActionBar(toolbar)



        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)


        val user = FirebaseAuth.getInstance().currentUser

        if(user!=null){
            val email = user.email
            val uid = user.uid

            val root = FirebaseDatabase.getInstance().reference
            val userRef = root.child("Client").child(uid)


        }

        mAuth= FirebaseAuth.getInstance()


    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.home, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        when (item.itemId) {
            R.id.action_settings -> return true
            else -> return super.onOptionsItemSelected(item)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_jobs -> {
                // Handle the camera action
            }
            R.id.nav_people -> {

            }
            R.id.nav_transaction -> {

            }
            R.id.nav_profile -> {

            }
            R.id.nav_logout -> {
                signOut()
            }
//            R.id.nav_send -> {
//
//            }id
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }
    fun signOut(){
        val user = FirebaseAuth.getInstance().currentUser
        if(user!!.isEmailVerified){
            Toast.makeText(this,"Successfully Logout! ", Toast.LENGTH_LONG).show()
            mAuth!!.signOut()
            finish()
            startActivity(Intent(this,MainActivity::class.java))
        }
        else if(!(user.isEmailVerified))
        {
            Toast.makeText(this,"Check your Email for verification ! ", Toast.LENGTH_LONG).show()
            finish()
            startActivity(Intent(this,MainActivity::class.java))
        }

    }
}
