package com.ayaoki.mydictionary.Controller

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.Editable
import android.view.View
import android.widget.Toast
import com.ayaoki.mydictionary.Model.Vocabulary
import com.ayaoki.mydictionary.R
import com.google.firebase.firestore.FirebaseFirestore
import android.util.Log
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
        var inputTxt: Editable? = vocabularyForm.getText()
        val meaning: String = meaningForm.getText().toString()
        var exampleSentence1: Editable? = exampleSentenceForm1.getText()
        var exampleSentence2: Editable? = exampleSentenceForm2.getText()
        var exampleSentence3: Editable? = exampleSentenceForm3.getText()
        Toast.makeText(this, "$inputTxt", Toast.LENGTH_SHORT).show()

        val db = FirebaseFirestore.getInstance()
        val exampleInputs = arrayListOf<String>("$exampleSentence1", "$exampleSentence2", "$exampleSentence3")
        val vocabulary = Vocabulary(meaning, exampleInputs)

        // DBへの書き込み
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
                vocabularyForm.setText("")
                meaningForm.setText("")
                exampleSentenceForm1.setText("")
                exampleSentenceForm2.setText("")
                exampleSentenceForm3.setText("")
            })
            .addOnFailureListener({
                Toast.makeText(this, "送信失敗", Toast.LENGTH_SHORT).show()
            })
    }

    fun listButtonIsClicked(buttonView: View) {
        Log.d("TAG", "listButtonIsClicked is called")
        // DBからの読み取り
        val db = FirebaseFirestore.getInstance()
        db.collection("users")
            .document("test-user201812")
            .collection("vocabularies")
            .document("tea")
            .get()
            .addOnCompleteListener({
                if (it.isSuccessful) {
                    val vocabulary = it.result!!.toObject(Vocabulary::class.java)
                    Toast.makeText(this, "成功", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "失敗", Toast.LENGTH_SHORT).show()
                }
            })

//            .addOnCompleteListener { task ->
//                if (task.isSuccessful) {
//                    Toast.makeText(this, "成功1", Toast.LENGTH_SHORT).show()
//                    Toast.makeText(this, "成功1", Toast.LENGTH_SHORT).show()
////                    for (document in task.result!!) {
////                        Log.d("TAG", "$document.id")
////                        Toast.makeText(this, "$document.id", Toast.LENGTH_SHORT).show()
////                        Toast.makeText(this, "$document.data", Toast.LENGTH_SHORT).show()
////                        Toast.makeText(this, "成功2", Toast.LENGTH_SHORT).show()
////                    }
//                } else {
////                    Log.w(FragmentActivity.TAG, "Error getting documents.", task.exception)
//                    Toast.makeText(this, "失敗", Toast.LENGTH_SHORT).show()
//                }
//            }
    }
}
