package edu.umsl.tarik_muzughi.simon


import androidx.lifecycle.ViewModel

class MyViewModel : ViewModel()  {


    private lateinit var model: MyViewModel
    public var randomNumber: Int = (0..3).random()
    public var example: String = randomNumber.toString()

    /* Assign values to colors */
    var blue: Int = 0
    var red: Int = 1
    var green: Int = 2
    var yellow: Int = 3

    var playersTurn: Boolean = false
    var computersTurn: Boolean = true
    var init: Boolean = true

    var compSeq: String = ""
    var playerSequence: String = ""
    var seqLength: Int = 0
    var seqIndex: Int = 0
    var playerIndex: Int = 0

    var score:Int = 0

    var brief: String = ""

    var difficulty = 1

    var flashSpeed: Long = 0

    var gameOver: Boolean = false

    fun getRandom(): Int{
        var randomNumber: Int = (0..3).random()
        return randomNumber
    }

    fun setCompSeq(){
        if (difficulty == 1) {
            compSeq += (0..3).random().toString()
            seqLength++
        }
        if (difficulty == 3){
            for (i in 1..3)
                compSeq += (0..3).random().toString()
            seqLength += 3
        }
        if (difficulty == 5){
            for (i in 1..5)
                compSeq += (0..3).random().toString()
            seqLength += 5
        }
    }


}