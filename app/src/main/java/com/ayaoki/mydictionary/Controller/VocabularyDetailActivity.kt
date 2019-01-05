package com.ayaoki.mydictionary.Controller

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.ayaoki.mydictionary.Model.Vocabulary
import com.ayaoki.mydictionary.R
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_vocabulary_detail.*

class VocabularyDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vocabulary_detail)

        val text: String = "contempt"
        vocabularyDetailTitle.setText(text)

        // DBからの読み取り
        val db = FirebaseFirestore.getInstance()
        db.collection("users")
            .document("test-user201812")
            .collection("vocabularies")
            .document(text)
            .get()
            .addOnCompleteListener({
                if (it.isSuccessful) {
                    val vocabulary = it.result!!.toObject(Vocabulary::class.java)
                    val meaning: String = vocabulary!!.meaning
                    vocabularyDetailMeaning.setText(meaning)
                    for (i in vocabulary.examples.indices) {
                        when (i) {
                            0 -> vocabularyDetailExample1.setText(vocabulary.examples[0])
                            1 -> vocabularyDetailExample2.setText(vocabulary.examples[1])
                            2 -> vocabularyDetailExample3.setText(vocabulary.examples[2])
                        }
                    }

                    Toast.makeText(this, "成功", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "失敗", Toast.LENGTH_SHORT).show()
                }
            })
    }
}
