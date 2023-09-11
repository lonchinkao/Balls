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

    fun addNewTube () {
        
        val numberofTube = tubes.size
        val numberofBall = tubes[0].balls.size

        val tube = Tube()
        repeat(numberofBall) {
            tube.balls.add(Ball(0, true))
        }
        tubes.add(tube)
    }  
    
    fun initTube(numberofTube: Int, numberofBall: Int) {

        for (i in 0 until numberofTube) {
            val tube = Tube()
            val value = i + 1
            repeat(numberofBall) {
                tube.balls.add(Ball(value, false))
            }
            tubes.add(tube)
        }
        
        val seed = 42L
        val random = Random(seed)
            for (i in 0 until numberofTube) {
                for (j in 0 until numberofBall) {
                    val i0 = nextInt(numberofBall)
                    val j0 = nextInt(numberofTube)

                    
                    val t  = getBallValue(i,j)
                     val t0 = getBallValue(i0,j0)
                    
                    if (t0 != null) {
                        if (t != null) {
                            setBallValue(i, j, t0)
                            setBallValue(i0, j0, t)
                        }
                    }
                    
                     
                }
            }
                            

    }

    fun setNumberOfTubes(numTubes: Int) {
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
        var r: Int = 0
        if (tubeIndex in 0 until tubes.size) {
            val tube = tubes[tubeIndex]
            if (ballIndex in 0 until tube.balls.size) {
                r = tube.balls[ballIndex].value
                return r
            }
        }
        return r
    }

    // You can add more methods to manipulate the tube collection if needed
}
