package edu.umsl.tarik_muzughi.simon

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.lifecycle.ViewModelProvider

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val model = ViewModelProvider(this).get(MyViewModel::class.java)

        var random = (0..3).random()
        val fourColors = arrayOf("Green", "Yellow", "Blue", "Red")
        val allColors: ArrayList<String> = arrayListOf(fourColors[random])
        val easy = findViewById<Button>(R.id.start)
        val medium = findViewById<Button>(R.id.medium)
        val hard = findViewById<Button>(R.id.hard)
        val activitiesArray = arrayOf(Green::class.java, Green::class.java, Green::class.java, Green::class.java)
        var difficulty = 0;


        for(i in 0..3){
            random = (0..3).random()
            allColors.add(fourColors[random])
        }

        easy.setOnClickListener {
            val intent = Intent(this@MainActivity, activitiesArray[random])
            intent.putStringArrayListExtra("colors", allColors)
            intent.putExtra("count", 0)
            intent.putExtra("score", 0)
            intent.putExtra("difficulty", 1)
            model.difficulty = 1
            model.seqLength = 1
            startActivity(intent)
        }
        medium.setOnClickListener {
            val intent = Intent(this@MainActivity, activitiesArray[random])
            intent.putStringArrayListExtra("colors", allColors)
            intent.putExtra("count", 0)
            intent.putExtra("score", 0)
            intent.putExtra("difficulty", 3)
            model.difficulty = 3
            model.seqLength = 3
            startActivity(intent)
        }
        hard.setOnClickListener {
            val intent = Intent(this@MainActivity, activitiesArray[random])
            intent.putStringArrayListExtra("colors", allColors)
            intent.putExtra("count", 0)
            intent.putExtra("score", 0)
            intent.putExtra("difficulty", 5)
            model.difficulty = 5
            model.seqLength = 5
            startActivity(intent)
        }
    }

}
