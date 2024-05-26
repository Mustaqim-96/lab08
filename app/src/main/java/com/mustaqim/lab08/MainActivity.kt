package com.mustaqim.lab08

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import android.icu.util.Calendar
import android.os.Bundle
import android.widget.SeekBar
import android.widget.SeekBar.OnSeekBarChangeListener
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.mustaqim.lab08.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding:ActivityMainBinding

    val pizzaSizes = arrayOf("Please Select Size","Small","Medium","Larger","Extra Large")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root

        setContentView(view)

        binding.scheduleBtn.setOnClickListener {
            var intent = Intent(this, ThanksActivity::class.java)
            intent.putExtra("name",binding.nameEditText.text.toString())
            intent.putExtra("phone",binding.phoneEditText.text.toString())
            intent.putExtra("size",binding.sizeTextView.text.toString())
            intent.putExtra("date",binding.selectedDateTextView.text.toString())
            intent.putExtra("time",binding.selectedTimeTextView.text.toString())

            startActivity(intent)
        }

        binding.sizeSeekBar.setOnSeekBarChangeListener(object : OnSeekBarChangeListener {

            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                binding.sizeTextView.text = pizzaSizes[progress]
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
            }

        })

        binding.selectDateBtn.setOnClickListener {
            val c = Calendar.getInstance()
            val day = c.get(Calendar.DAY_OF_MONTH)
            val month = c.get(Calendar.MONTH)
            val year = c.get(Calendar.YEAR)

            val myDatePicker =
                DatePickerDialog(
                    this,
                    android.R.style.ThemeOverlay,
                    DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
                        binding.selectedDateTextView.text = "$dayOfMonth/ ${month+1}/ $year"

                    },
                    year,
                    month,
                    day
                )
            myDatePicker.show()
        }

        binding.selectTimeBtn.setOnClickListener {
            val c = Calendar.getInstance()
            val hour = c.get(Calendar.HOUR_OF_DAY)
            val minutes = c.get(Calendar.MINUTE)

            val myTimePicker = TimePickerDialog(
                this,
                TimePickerDialog.OnTimeSetListener { view, hourOfDay, minute ->
                   binding.selectedTimeTextView.text = "$hourOfDay : $minutes"
                },
                hour,
                minutes,
                true
            )

            myTimePicker.show()
        }


    }
}