package com.example.balls
import java.util.Random
import kotlin.random.Random.Default.nextInt

class Ball(val value: Int, val isempty : Boolean) {
}
class Tube {
    val balls: MutableList<Ball> = mutableListOf()

    fun addBall(ball: Ball) {
        balls.add(ball)
    }

    fun getBallValue(index: Int): Int? {
        if (index in 0 until balls.size) {
            return balls[index].value
        }
        return null
    }


}

// TubeCollection class to manage a collection of tubes
class TubeCollection {
    val tubes: MutableList<Tube> = mutableListOf()
    val allBall: MutableList<Ball> = mutableListOf()
    fun addNewTube () {

        val numberofTube = tubes.size
        val numberofBall = tubes[0].balls.size

        val tube = Tube()
        repeat(numberofBall) {
            tube.balls.add(Ball(0, true))
        }
        tubes.add(tube)
    }
     
    fun initTube(numberoSelected: Int) {
        class Level(var start: Int, var size: Int, var numberofTune: Int, var numberofBall:Int)

        val arrayLev = arrayOf (
            Level(1, 5, 4, 3),
            Level(11, 5, 5, 3),
            Level(21, 5, 6, 3),
            Level(31, 5, 7, 3),
            Level(41, 5, 7, 4),
            Level(51, 5, 7, 5),
            Level(61, 5, 8, 3),
            Level(71, 5, 8, 4),
            Level(81, 5, 9, 5),
            Level(91, 5, 9, 3),
            Level(101, 5, 10, 4),
            Level(111, 5, 10, 5),
        )
        for (Level in arrayLev) {
            if (numberoSelected >= Level.start && numberoSelected <= (Level.start + Level.size)) {
                initTubeBall(Level.numberofTune, Level.numberofTune )
            }
        }

    }
    
    fun initTubeBall(numberofTube: Int, numberofBall: Int) {

        allBall.clear()
        for (i in 0 until numberofTube) {
            // val tube = Tube()
            val value = i + 1
            for (j in 0 until numberofBall) {
                // tube.balls.add(Ball(value, false))
                allBall.add(Ball(value, false))
            }
            // tubes.add(tube)
        }
    
        val seed = 412L
        val random = Random(seed)
        allBall.shuffle(random)
        tubes.clear()
        var inx = 0
        for (tubeIndex in 0 until numberofTube) {
            val tube = Tube()
            for (ballIndex in 0 until numberofBall) {
                val vv = getallBallValue(inx)
                if (vv != null ) {
                        tube.balls.add(Ball(vv, false))
                        inx = inx + 1
                    }
                }
            tubes.add(tube)
        }
    }
    fun getallBallValue(index: Int): Int? {
        if (index in 0 until allBall.size) {
            return allBall[index].value
        }
        return null
    }    fun setNumberOfTubes(numTubes: Int) {
        tubes.clear()
        for (i in 0 until numTubes) {
            tubes.add(Tube())
        }
    }

    fun setBallValue(tubeIndex: Int, ballIndex: Int, value: Int) {
        if (tubeIndex in 0 until tubes.size) {
            val tube = tubes[tubeIndex]
            if (ballIndex in 0 until tube.balls.size) {
                tube.balls[ballIndex] = Ball(value, false)
            }
        }
    }

    fun getBallValue(tubeIndex: Int, ballIndex: Int): Int? {
        if (tubeIndex in 0 until tubes.size) {
            return tubes[tubeIndex].getBallValue(ballIndex)
        }
        return null
    }

    // You can add more methods to manipulate the tube collection if needed
}
