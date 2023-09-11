package com.example.balls


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
            val value = i + 2
            repeat(numberofBall) {
                tube.balls.add(Ball(value, false))
            }
            tubes.add(tube)
        }
        
    }

    fun initTube2(numberofTube: Int, numberofBall: Int) {
              
        // Create a list of Ball objects with unique values
        val allBalls = (1..(numberofTube * numberofBall)).map { Ball(it, false) }.toMutableList()
        // Shuffle the list of Ball objects randomly
        allBalls.shuffle()
        // Create Tube objects and add balls from the shuffled list to each tube
        val tubeCollection = TubeCollection()
        for (tubeIndex in 0 until numberofTube) {
            val tube = Tube()
            for (ballIndex in 0 until numberofBall) {
                tube.balls.add(allBalls.removeAt(0))
            }
            tubeCollection.tubes.add(tube)
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
        if (tubeIndex in 0 until tubes.size) {
            return tubes[tubeIndex].getBallValue(ballIndex)
        }
        return null
    }

    // You can add more methods to manipulate the tube collection if needed
}
