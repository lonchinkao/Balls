package com.example.balls

import android.annotation.SuppressLint
import android.app.ProgressDialog.show
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast


class MainActivity : AppCompatActivity() {

    val tubeCollection = TubeCollection()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
      
        setContentView(R.layout.activity_main)
        
        // tubeCollection.initTube(4, 4)
       //  tubeCollection.addNewTube()

         // Create a new TextView
        // createText()
        createtube()
        createButtons()
    }

    private fun createText() {
        val linearLayout = findViewById<LinearLayout>(R.id.textView2)

        // Create a new TextView
        val textView = TextView(this)
        val no = tubeCollection.tubes.size
        val ba = tubeCollection.tubes[0].balls.size
        // Set text and other attributes for the TextView
        textView.text = "Tube set tube  $no ball $ba"
        textView.layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        // Add the TextView to the LinearLayout
        linearLayout.addView(textView)
    }
        
    private   fun createtube()
    {
        val tubeView = findViewById<TubeView>(R.id.tube_view)
        val screenWidth = resources.displayMetrics.widthPixels
        val screenHeight = resources.displayMetrics.heightPixels
        val desiredWidth = screenWidth
        val desiredHeight = screenHeight - 100

        // Customize TubeView (e.g., set its dimensions)
        val layoutParams = tubeView.layoutParams
        layoutParams.width = desiredWidth // Set your desired width
        layoutParams.height = desiredHeight // Set your desired height
        tubeView.layoutParams = layoutParams

        tubeView.onTubeClickListener = object : TubeView.OnTubeClickListener {
            override fun onTubeClick(rectangleIndex: Int) {
                // Handle the click event for the rectangle at the specified index
                // You can perform actions based on the clicked rectangle
                // For example, display a message, change its color, etc.
                Toast.makeText(
                    applicationContext,
                    "Rectangle $rectangleIndex clicked",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
        
    }
 
     private fun createButtons() {
        val bottomBar = findViewById<LinearLayout>(R.id.top_bar)

        // Define button data (You can customize this list as needed)
        val buttonData = listOf(
            ButtonData(R.drawable.ic_home, "Home") { onButtonClick("home") },
            ButtonData(R.drawable.ic_menu, "menu") { onButtonClick("menu") },
            ButtonData(R.drawable.ic_plus, "undo") { onButtonClick("undo") },
            ButtonData(R.drawable.ic_up0, "up") { onButtonClick("up") },
            ButtonData(R.drawable.ic_down0, "menu") { onButtonClick("down") },
            ButtonData(R.drawable.ic_right0, "undo") { onButtonClick("right") },
            ButtonData(R.drawable.ic_left0, "Home") { onButtonClick("left") },
            ButtonData(R.drawable.ic_setting, "menu") { onButtonClick("setting") }
          )

         var gla = ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
         gla.width = 48
         gla.height =48
        for (data in buttonData) {
            val button = ImageButton(this)
            button.layoutParams = gla
            button.setImageResource(data.iconRes)
            button.contentDescription = data.label
            button.setOnClickListener { data.onClick() }

            // Add the button to the bottom bar
            bottomBar.addView(button)
        }
    }

    private fun onButtonClick(buttonLabel: String) {
        when (buttonLabel) {
            "home" ->   Toast.makeText(applicationContext,  buttonLabel, Toast.LENGTH_SHORT).show()
            "undo" ->   Toast.makeText(applicationContext,  buttonLabel, Toast.LENGTH_SHORT).show()
            "plus" ->   Toast.makeText(applicationContext,  buttonLabel, Toast.LENGTH_SHORT).show()
            "left" ->   Toast.makeText(applicationContext,  buttonLabel, Toast.LENGTH_SHORT).show()
            "right" ->   Toast.makeText(applicationContext,  buttonLabel, Toast.LENGTH_SHORT).show()
            "up" ->   Toast.makeText(applicationContext,  buttonLabel, Toast.LENGTH_SHORT).show()
            "down" ->   Toast.makeText(applicationContext,  buttonLabel, Toast.LENGTH_SHORT).show()

        }
      //  Toast.makeText(applicationContext, " clicked home " + buttonLabel, Toast.LENGTH_SHORT).show()
                
    }
       
}

data class ButtonData(val iconRes: Int, val label: String, val onClick: () -> Unit)
