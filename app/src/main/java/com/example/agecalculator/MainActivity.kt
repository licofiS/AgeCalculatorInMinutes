package com.example.agecalculator

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val btnDatePicker: Button = findViewById(R.id.btnDatePicker)

        btnDatePicker.setOnClickListener{ view ->
            clickDatePicker(view)
        }

    }

    //In java month starts from 0
    fun clickDatePicker(view: View){

        val myCalendar = Calendar.getInstance()
        val year = myCalendar.get(Calendar.YEAR)
        val month = myCalendar.get(Calendar.MONTH)
        val day = myCalendar.get(Calendar.DAY_OF_MONTH)

        DatePickerDialog(this,
            DatePickerDialog.OnDateSetListener { view, selectedYear, selectedMonth, selectedDayOfMonth ->

                //Logic is written here for calculation of minutes
                val selectedDate = "$selectedDayOfMonth/${selectedMonth+1}/$selectedYear"

                val tvSelectedDate: TextView = findViewById(R.id.tvSelectedDated)
                tvSelectedDate.text = selectedDate

                //sdf =  simple date format
                val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)

                val theDate = sdf.parse(selectedDate)
                val todayDate = sdf.parse("$day/${month+1}/$year")

                val diff: Long = todayDate.time - theDate.time

                var minutes = (diff / 60000) + (myCalendar.get(Calendar.HOUR)*60) + (myCalendar.get(Calendar.MINUTE))

                val tvSelectedDateInMinutes : TextView = findViewById(R.id.tvSelectedDateInMinutes)
                tvSelectedDateInMinutes.text = minutes.toString()
            },
            year,
            month,
            day).show()
    }
}