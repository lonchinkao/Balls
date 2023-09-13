package com.example.balls


import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup.LayoutParams
import android.widget.Button
import android.widget.GridLayout
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment

class NumberPickerDialog(private val context: Context) : DialogFragment() {
    private val itemsPerPage = 50
    private var start = 0
    private val totalbutton = 200

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(context)
        val inflater = LayoutInflater.from(context)
        val dialogView = inflater.inflate(R.layout.custom_number_picker_dialog, null)
        builder.setView(dialogView)

        val titleTextView = dialogView.findViewById<TextView>(R.id.dialog_title)
        val gridLayout = dialogView.findViewById<GridLayout>(R.id.gridLayout)
        val navigationLayout = dialogView.findViewById<LinearLayout>(R.id.navigationLayout)

        // Create navigation symbols
        val prevButton = Button(context)
        prevButton.text = "◄"
        val nextButton = Button(context)
        nextButton.text = "►"

        // Set click listeners for navigation buttons
        prevButton.setOnClickListener {
            if (start > 0) {
                start = start - itemsPerPage
            }  else {
                start = 150
            }
                updateButtons(gridLayout)
            
        }
        nextButton.setOnClickListener {
            if (start  <  150) {
                start = start + itemsPerPage
            } else {
                start = 0
                }
                updateButtons(gridLayout)
            }
        

        navigationLayout.addView(prevButton)
        navigationLayout.addView(nextButton)

        // Create buttons for the current page
        updateButtons(gridLayout)

        return builder.create()
    }

    private fun updateButtons(gridLayout: GridLayout) {
        gridLayout.removeAllViews()
        
        val end = minOf(start + itemsPerPage - 1, 200)

        for (i in start..end) {
            val button = Button(context)
            button.text = (i + 1).toString()
            button.background = context.getDrawable(R.drawable.rounded_rectangle)
            val params = GridLayout.LayoutParams()
            params.width = 110//LayoutParams.WRAP_CONTENT
            params.height = LayoutParams.WRAP_CONTENT
            button.layoutParams = params
            gridLayout.addView(button)
        }
    }
}

