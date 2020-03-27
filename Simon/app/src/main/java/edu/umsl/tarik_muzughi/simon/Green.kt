package edu.umsl.tarik_muzughi.simon

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.Button
import android.widget.TextView

import java.util.concurrent.TimeUnit
//import org.junit.rules.Timeout.seconds
import androidx.core.app.ComponentActivity
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_green.*
import android.R.string.no
import android.R.attr.name

import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.OnLifecycleEvent
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T




class Green : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_green)

        /* model stuff */
        val model = ViewModelProvider(this).get(MyViewModel::class.java)




        val title = findViewById<TextView>(R.id.title)
        val scoreText = findViewById<TextView>(R.id.score)
        val green = findViewById<Button>(R.id.greenBtn)
        val yellow = findViewById<Button>(R.id.yellowBtn)
        val blue = findViewById<Button>(R.id.blueBtn)
        val red = findViewById<Button>(R.id.redBtn)

        val restart = findViewById<Button>(R.id.restartBtn)
        val activitiesArray = arrayOf(Green::class.java)

        var score = intent.getIntExtra("score", -2)
        var count = intent.getIntExtra("count", -3)
        var diff = intent.getIntExtra("difficulty", 1)
        model.difficulty = diff
        val colors = intent.getStringArrayListExtra("colors")
        //var computerGo = false
        var colorGo = false
        var i = 0
        var random = (0..3).random()


        /* Set flash speed */
        if (model.difficulty == 1){
            model.flashSpeed = 1000
        }
        if (model.difficulty == 3){
            model.flashSpeed = 500
        }
        if (model.difficulty == 5){
            model.flashSpeed = 250
        }
        /* Initialize computer sequence */
        //model.compSeq = model.getRandom().toString()
        //model.seqLength += 1

        //scoreText.text = model.difficulty.toString()

        model.setCompSeq()


        fun computerGo(){
            if (model.compSeq[model.seqIndex] == '0'){
                doBlue(blue)
            }
            if (model.compSeq[model.seqIndex] == '1'){
                doRed(red)
            }
            if (model.compSeq[model.seqIndex] == '2'){
                doGreen(green)
            }
            if (model.compSeq[model.seqIndex] == '3'){
                doYellow(yellow)
            }
            //model.playersTurn = true
        }
        if (model.computersTurn){
            val timer = object: CountDownTimer(2000, 10) {
                override fun onTick(millisUntilFinished: Long) {}

                override fun onFinish() {
                    //computerGo()
                   // model.computersTurn = false

                }
            }
            timer.start()
        }




        

        model.randomNumber = (0..3).random()
        model.example += model.randomNumber.toString()
        model.randomNumber = (0..3).random()
        model.example += model.randomNumber.toString()
        val string: String = model.example[1].toString()









/*
        green.setOnClickListener {
            /* show color change */

            //green.setBackgroundColor(Color.parseColor("#4CAF50"))

            doGreen(green)


        }

        yellow.setOnClickListener {
            //onCorrect("Yellow", 1)


                doYellow(yellow)


        }
        blue.setOnClickListener {
            //onCorrect("Blue", 2)
            blue.setBackgroundColor(Color.parseColor("#183EFF"))
            val timer = object : CountDownTimer(500, 10) {
                override fun onTick(millisUntilFinished: Long) {}

                override fun onFinish() {
                    blue.setBackgroundColor(Color.parseColor("#00158B"))
                    //onResume()
                }
            }
            timer.start()
        }
        red.setOnClickListener {
            //onCorrect("Red", 3)
            red.setBackgroundColor(Color.RED)
            val timer = object : CountDownTimer(500, 10) {
                override fun onTick(millisUntilFinished: Long) {}

                override fun onFinish() {
                    red.setBackgroundColor(Color.parseColor("#7C0000"))
                }
            }
            timer.start()
        }
*/

    }
    /*--------------------------------------------------------------------------------------
                                             RESUME
    ---------------------------------------------------------------------------------------*/
    override fun onResume() {
        super.onResume()
        val model = ViewModelProvider(this).get(MyViewModel::class.java)

            val blue = findViewById<Button>(R.id.blueBtn)
            val red = findViewById<Button>(R.id.redBtn)
            val green = findViewById<Button>(R.id.greenBtn)
            val yellow = findViewById<Button>(R.id.yellowBtn)

        val scoreText = findViewById<TextView>(R.id.score)
        val title = findViewById<TextView>(R.id.title)


        fun computerDoStuff(){
            if (model.compSeq[model.seqIndex] == '0')
                doBlue(blue)
            if (model.compSeq[model.seqIndex] == '1')
                doRed(red)
            if (model.compSeq[model.seqIndex] == '2')
                doGreen(green)
            if (model.compSeq[model.seqIndex] == '3')
                doYellow(yellow)
        }
        if (!model.gameOver) {
            /* Computer begins doing stuff */
            var waitTime: Long = 0
            if (model.seqIndex == 0) {
                waitTime = (2000)
                model.brief = "C"
            }
            if (model.seqIndex == model.seqLength) {
                model.brief = "P"
            }
            val timer = object : CountDownTimer(waitTime, 10) {
                override fun onTick(millisUntilFinished: Long) {}

                override fun onFinish() {

                    if (model.seqIndex < (model.seqLength)) {
                        model.playersTurn = false
                        computerDoStuff()

                        val timer = object : CountDownTimer(model.flashSpeed * 2, 10) {
                            override fun onTick(millisUntilFinished: Long) {}

                            override fun onFinish() {

                                model.seqIndex += 1
                                onResume()
                            }
                        }
                        timer.start()
                    }
                    if (model.seqIndex == model.seqLength) {
                        model.playersTurn = true
                        model.computersTurn = false
                    }
                }
            }
            timer.start()


        }

            title.text = model.brief
            scoreText.text = model.score.toString()

            /* Computer stops doing stuff */


        if (model.playersTurn) {
            //begin players controls
            green.setOnClickListener {
                if (!model.gameOver) {
                    if (model.compSeq[model.playerIndex] == '2') {
                        green.setBackgroundColor(Color.GREEN)
                    } else {
                        if (model.compSeq[model.playerIndex] == '0')
                            blue.setBackgroundColor(Color.BLUE)
                        if (model.compSeq[model.playerIndex] == '1')
                            red.setBackgroundColor(Color.RED)
                        if (model.compSeq[model.playerIndex] == '2')
                            green.setBackgroundColor(Color.GREEN)
                        if (model.compSeq[model.playerIndex] == '3')
                            yellow.setBackgroundColor(Color.YELLOW)
                        model.gameOver = true
                        model.brief = "Game Over"
                        title.text = model.brief
                        val timer = object : CountDownTimer(5000, 10) {
                            override fun onTick(millisUntilFinished: Long) {}

                            override fun onFinish() {
                                val intent = Intent(this@Green, Results::class.java)
                                startActivity(intent)
                            }
                        }
                        timer.start()
                    }
                    val timer = object : CountDownTimer(250, 10) {
                        override fun onTick(millisUntilFinished: Long) {}

                        override fun onFinish() {
                            green.setBackgroundColor(Color.parseColor("#007E04"))
                            if (model.compSeq[model.playerIndex] == '2') {
                                model.playerIndex++
                            }

                            if (model.playerIndex == model.seqLength) {
                                //model.compSeq += (0..3).random().toString()
                                model.setCompSeq()
                                //model.seqLength++
                                model.seqIndex = 0
                                model.playerIndex = 0
                                model.score++
                                model.playersTurn = false
                                onResume()
                            }
                        }
                    }
                    timer.start()
                }
                else
                {
                    onResume()
                }

            }
            blue.setOnClickListener {
                //onCorrect("Blue", 2)
                if (!model.gameOver) {
                    if (model.compSeq[model.playerIndex] == '0') {
                        blue.setBackgroundColor(Color.BLUE)
                    } else {
                        if (model.compSeq[model.playerIndex] == '0')
                            blue.setBackgroundColor(Color.BLUE)
                        if (model.compSeq[model.playerIndex] == '1')
                            red.setBackgroundColor(Color.RED)
                        if (model.compSeq[model.playerIndex] == '2')
                            green.setBackgroundColor(Color.GREEN)
                        if (model.compSeq[model.playerIndex] == '3')
                            yellow.setBackgroundColor(Color.YELLOW)
                        model.gameOver = true
                        model.brief = "Game Over"
                        title.text = model.brief
                        val timer = object : CountDownTimer(5000, 10) {
                            override fun onTick(millisUntilFinished: Long) {}

                            override fun onFinish() {
                                val intent = Intent(this@Green, Results::class.java)
                                startActivity(intent)
                            }
                        }
                        timer.start()
                    }
                    val timer = object : CountDownTimer(250, 10) {
                        override fun onTick(millisUntilFinished: Long) {}

                        override fun onFinish() {
                            blue.setBackgroundColor(Color.parseColor("#00158B"))
                            if (model.compSeq[model.playerIndex] == '0') {
                                model.playerIndex++
                            }

                            if (model.playerIndex == model.seqLength) {
                                // model.compSeq += (0..3).random().toString()
                                model.setCompSeq()
                                //model.seqLength++
                                model.seqIndex = 0
                                model.playerIndex = 0
                                model.score++
                                model.playersTurn = false
                                onResume()
                            }
                        }
                    }
                    timer.start()
                }
                else
                {
                    onResume()
                }
            }
            red.setOnClickListener {
                if (!model.gameOver) {
                    //onCorrect("Red", 3)
                    if (model.compSeq[model.playerIndex] == '1') {
                        red.setBackgroundColor(Color.RED)
                    }
                    else {
                        if (model.compSeq[model.playerIndex] == '0')
                            blue.setBackgroundColor(Color.BLUE)
                        if (model.compSeq[model.playerIndex] == '1')
                            red.setBackgroundColor(Color.RED)
                        if (model.compSeq[model.playerIndex] == '2')
                            green.setBackgroundColor(Color.GREEN)
                        if (model.compSeq[model.playerIndex] == '3')
                            yellow.setBackgroundColor(Color.YELLOW)
                        model.gameOver = true
                        model.brief = "Game Over"
                        title.text = model.brief
                        val timer = object : CountDownTimer(5000, 10) {
                            override fun onTick(millisUntilFinished: Long) {}

                            override fun onFinish() {
                                val intent = Intent(this@Green, Results::class.java)
                                startActivity(intent)
                            }
                        }
                        timer.start()
                    }
                    val timer = object : CountDownTimer(250, 10) {
                        override fun onTick(millisUntilFinished: Long) {}

                        override fun onFinish() {
                            red.setBackgroundColor(Color.parseColor("#7C0000"))
                            if (model.compSeq[model.playerIndex] == '1') {
                                model.playerIndex++
                            }

                            if (model.playerIndex == model.seqLength) {
                                //model.compSeq += (0..3).random().toString()
                                model.setCompSeq()
                                //model.seqLength++
                                model.seqIndex = 0
                                model.playerIndex = 0
                                model.score++
                                model.playersTurn = false
                                onResume()
                            }
                        }
                    }
                    timer.start()
                }
                else
                {
                    onResume()
                }
            }
            yellow.setOnClickListener {
                if (!model.gameOver) {
                    if (model.compSeq[model.playerIndex] == '3') {
                        yellow.setBackgroundColor(Color.YELLOW)
                    }
                    else {
                        if (model.compSeq[model.playerIndex] == '0')
                            blue.setBackgroundColor(Color.BLUE)
                        if (model.compSeq[model.playerIndex] == '1')
                            red.setBackgroundColor(Color.RED)
                        if (model.compSeq[model.playerIndex] == '2')
                            green.setBackgroundColor(Color.GREEN)
                        if (model.compSeq[model.playerIndex] == '3')
                            yellow.setBackgroundColor(Color.YELLOW)
                        model.gameOver = true
                        model.brief = "Game Over"
                        title.text = model.brief
                        val timer = object : CountDownTimer(5000, 10) {
                            override fun onTick(millisUntilFinished: Long) {}

                            override fun onFinish() {
                                val intent = Intent(this@Green, Results::class.java)
                                startActivity(intent)
                                }
                            }
                        timer.start()
                    }

                    val timer = object : CountDownTimer(250, 10) {
                        override fun onTick(millisUntilFinished: Long) {}

                        override fun onFinish() {
                            yellow.setBackgroundColor(Color.parseColor("#B19900"))
                            if (model.compSeq[model.playerIndex] == '3') {
                                model.playerIndex++
                            }

                            if (model.playerIndex == model.seqLength) {
                                //model.compSeq += (0..3).random().toString()
                                model.setCompSeq()
                                //model.seqLength++
                                model.seqIndex = 0
                                model.playerIndex = 0
                                model.score++
                                model.playersTurn = false
                                onResume()
                            }
                        }
                    }
                    timer.start()
                }
                    else
                    {
                        onResume()
                    }
            }
//end players controls
        }

    }
    fun doGreen(green: Button){

        val model = ViewModelProvider(this).get(MyViewModel::class.java)
            green.setBackgroundColor(Color.GREEN)


        val timer = object: CountDownTimer(model.flashSpeed, 10) {
            override fun onTick(millisUntilFinished: Long) {}

            override fun onFinish() {
                green.setBackgroundColor(Color.parseColor("#007E04"))

                }
            }
        timer.start()
        }


    fun doYellow(yellow: Button){
        val model = ViewModelProvider(this).get(MyViewModel::class.java)
        yellow.setBackgroundColor(Color.YELLOW)
        val timer = object : CountDownTimer(model.flashSpeed, 10) {
            override fun onTick(millisUntilFinished: Long) {}

            override fun onFinish() {
                yellow.setBackgroundColor(Color.parseColor("#B19900"))
            }
        }
        timer.start()
    }
    fun doRed(red: Button){
        val model = ViewModelProvider(this).get(MyViewModel::class.java)
        red.setBackgroundColor(Color.RED)
        val timer = object : CountDownTimer(model.flashSpeed, 10) {
            override fun onTick(millisUntilFinished: Long) {}

            override fun onFinish() {
                red.setBackgroundColor(Color.parseColor("#7C0000"))

            }
        }
        timer.start()
    }
    fun doBlue(blue: Button){
        val model = ViewModelProvider(this).get(MyViewModel::class.java)
        blue.setBackgroundColor(Color.BLUE)
        val timer = object : CountDownTimer(model.flashSpeed, 10) {
            override fun onTick(millisUntilFinished: Long) {}

            override fun onFinish() {
                blue.setBackgroundColor(Color.parseColor("#00158B"))

            }
        }
        timer.start()
    }
    fun gameTimer(){

    }

    public override fun onSaveInstanceState(savedInstanceState: Bundle) {
        super.onSaveInstanceState(savedInstanceState)
        //savedInstanceState.putInt("score", scoreNumber)

    }


}


