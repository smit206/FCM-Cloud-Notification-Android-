package com.example.notifymeapp

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class secondsession : AppCompatActivity() {
    var tvname:TextView? = null
    var tvphone:TextView? = null
    var bulogout:Button? = null

    lateinit var sharedPref:SharedPreferences
    var name = ""
    var phone = ""
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_secondsession)

        tvname = findViewById(R.id.opname)
        tvphone = findViewById(R.id.opphone)
        bulogout = findViewById(R.id.bulogout)

        sharedPref = getSharedPreferences("userdata",Context.MODE_PRIVATE)

        name = sharedPref.getString("name",null)!!
        phone = sharedPref.getString("phone",null)!!
        tvname!!.setText(name)
        tvphone!!.setText(phone)

        bulogout!!.setOnClickListener {

            val editor:SharedPreferences.Editor = sharedPref.edit()

            editor.clear()
            editor.apply()

            val intent = Intent(this@secondsession,session::class.java)
            startActivity(intent)

            finish()
        }
    }
}