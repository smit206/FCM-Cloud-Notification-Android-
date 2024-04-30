package com.example.notifymeapp

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class session : AppCompatActivity() {
    var etname:EditText? = null
    var etphone:EditText? = null
    var bulgn:Button? = null

    lateinit var sharedPref: SharedPreferences

    var name = ""
    var phone = ""
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_session)

        etname = findViewById(R.id.etname)
        etphone = findViewById(R.id.etphone)
        bulgn = findViewById(R.id.bulogin)

        sharedPref = getSharedPreferences("userdata",Context.MODE_PRIVATE)

        name = sharedPref.getString("name","").toString()
        phone = sharedPref.getString("phone","").toString()

        bulgn!!.setOnClickListener{
            if(etname!!.text.equals("") && etphone!!.text.equals("")){
                Toast.makeText(this@session, "Please Enter Email and Password!!", Toast.LENGTH_SHORT).show()
            }
            else{
                val editor:SharedPreferences.Editor = sharedPref.edit()

                editor.putString("name",etname!!.text.toString())
                editor.putString("phone",etphone!!.text.toString())

                editor.apply()

                val intent = Intent(this@session,secondsession::class.java)
                startActivity(intent)

                finish()
            }
        }
    }

    override fun onStart() {
        super.onStart()

        if(!name.isEmpty() && !phone.isEmpty()){
            val intent = Intent(this@session,secondsession::class.java)
            startActivity(intent)
            finish()
        }
        else{
            true
        }
    }
}