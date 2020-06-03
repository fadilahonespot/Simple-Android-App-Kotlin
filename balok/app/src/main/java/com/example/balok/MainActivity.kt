package com.example.balok

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.lang.NumberFormatException

class MainActivity : AppCompatActivity() {

    private lateinit var editWidt: EditText
    private lateinit var editHeight: EditText
    private lateinit var editLength: EditText
    private lateinit var calculate: Button
    private lateinit var result: TextView

    companion object {
        private const val STATE_RESULT = "state_result"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_layout)

        editWidt = findViewById(R.id.edit_width)
        editHeight = findViewById(R.id.edit_height)
        editLength = findViewById(R.id.edit_length)
        calculate = findViewById(R.id.btn_count)
        result = findViewById(R.id.text_result)

        if (savedInstanceState != null) {
            val resultState = savedInstanceState.getString(STATE_RESULT) as String
            result.text = resultState
        }
    }

    fun ResultButton(view: View) {
        if (view.id == R.id.btn_count) {
            val inputLength = editLength.text.toString().trim()
            val inputWidth = editWidt.text.toString().trim()
            val inputHeight = editHeight.text.toString().trim()

            var isEmptyFields = false
            var isInvalidDouble = false

            if (inputLength.isEmpty()) {
                isEmptyFields = true
                editLength.error = "Field tidak boleh kosong"
            }
            if (inputWidth.isEmpty()) {
                isEmptyFields = true
                editWidt.error = "Field tidak boleh kosong"
            }
            if (inputHeight.isEmpty()) {
                isEmptyFields = true
                editHeight.error = "Field tidak boleh kosong"
            }

            val length = toDouble(inputLength)
            val height = toDouble(inputHeight)
            val width = toDouble(inputWidth)

            if (length == null) {
                editLength.error = "Field ini harus di isi nomer yang valid"
                isInvalidDouble = true
            }
            if (height == null) {
                editHeight.error = "Field ini harus di isi dengan nomer yang valid"
                isInvalidDouble = true
            }
            if (width == null) {
                editWidt.error = "Field ini harus diisi dengan nomer yang valid"
                isInvalidDouble = true
            }

            if (isEmptyFields == false && isInvalidDouble == false) {
                val volume = length as Double * height as Double * width as Double
                result.text = volume.toString()
            }
        }
    }

    private fun toDouble(str: String): Double? {
        return try {
            str.toDouble()
        } catch (e: NumberFormatException) {
            null
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(STATE_RESULT, result.text.toString())
    }
}

