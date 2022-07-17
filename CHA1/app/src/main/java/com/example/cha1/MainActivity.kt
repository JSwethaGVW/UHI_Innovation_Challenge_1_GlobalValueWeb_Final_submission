package com.example.cha1

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.cha1.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)


        val languages = resources.getStringArray(R.array.india_top_places)
        val adapter = ArrayAdapter(this,
            android.R.layout.simple_list_item_1, languages)
        binding.etLocation.setAdapter(adapter)

        val specialisation = resources.getStringArray(R.array.specialist_list)
        val adapter2 = ArrayAdapter(this,
            android.R.layout.simple_list_item_1, specialisation)
        binding.etSpecialist.setAdapter(adapter2)

        val lang = resources.getStringArray(R.array.language_list)
        val adapter3 = ArrayAdapter(this,
            android.R.layout.simple_list_item_1, lang)
        binding.etLanguage.setAdapter(adapter3)

        val symptoms = resources.getStringArray(R.array.symptoms_list)
        val symptomsadapter = ArrayAdapter(this,
        android.R.layout.simple_list_item_1,symptoms)
        binding.etSymptoms.setAdapter(symptomsadapter)

        binding.btnSubmit.setOnClickListener {
            if (binding.etDescription.text.toString().isEmpty())
                "Enter Description!!".showToast(this)
            else {
            val intent = Intent(this@MainActivity,DoctorsListActivity::class.java)
            intent.putExtra("symptoms",binding.etDescription.text.toString())
            startActivity(intent)
            }
        }


        binding.etLanguage.setOnTouchListener { _, _ ->
            binding.etLanguage.showDropDown()
            true
        }

        binding.etSpecialist.setOnTouchListener { _, _ ->
            binding.etSpecialist.showDropDown()
            true
        }
    }

    private fun String.showToast(context: Context) {
        Toast.makeText(context, this, Toast.LENGTH_LONG).show()
    }
}