package edu.umsl.tarik_muzughi.simon

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class Results : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_results)

        val restart = findViewById<Button>(R.id.restartBtn)

        restart.setOnClickListener {
            val intent = Intent(this@Results, MainActivity::class.java)
            startActivity(intent)
        }
    }
}
