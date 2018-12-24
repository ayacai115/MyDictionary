package com.ayaoki.mydictionary

import android.os.Bundle
import android.support.design.R.styleable.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import android.text.Editable
import android.view.View
import android.widget.EditText
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    // なぜかOnNavigationItemSelectedListenerがunresolvedになるので一旦おいておく
//    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
//        when (item.itemId) {
//            R.id.navigation_home -> {
//                message.setText(R.string.title_home)
//                return@OnNavigationItemSelectedListener true
//            }
//            R.id.navigation_dashboard -> {
//                message.setText(R.string.title_dashboard)
//                return@OnNavigationItemSelectedListener true
//            }
//            R.id.navigation_notifications -> {
//                message.setText(R.string.title_notifications)
//                return@OnNavigationItemSelectedListener true
//            }
//        }
//        false
//    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // なぜかOnNavigationItemSelectedListenerがunresolvedになるので一旦おいておく
//        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }

    fun buttonIsClicked(buttonView: View) {
        var inputForm: EditText = findViewById(R.id.inputForm)
        var inputTxt: Editable? = inputForm.getText()

        Toast.makeText(this, "$inputTxt", Toast.LENGTH_SHORT).show()
    }
}
