package com.ayaoki.mydictionary.Controller

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.Editable
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.ayaoki.mydictionary.Model.Vocabulary
import com.ayaoki.mydictionary.R
import com.google.firebase.firestore.FirebaseFirestore




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

        val db = FirebaseFirestore.getInstance()
        // このクラスはあとで消す
        val vocabulary = Vocabulary( "hoge")

        db.collection("users")
            .document("test-user201812")
            .collection("vocabularies")
            .document("$inputTxt")
            .set(vocabulary)
            .addOnCompleteListener {
                Toast.makeText(this, "addOnCompleteListener is called", Toast.LENGTH_SHORT).show()
            }
            .addOnSuccessListener({
                Toast.makeText(this, "送信完了", Toast.LENGTH_SHORT).show()
            })
            .addOnFailureListener({
                Toast.makeText(this, "送信失敗", Toast.LENGTH_SHORT).show()
            })
    }
}
