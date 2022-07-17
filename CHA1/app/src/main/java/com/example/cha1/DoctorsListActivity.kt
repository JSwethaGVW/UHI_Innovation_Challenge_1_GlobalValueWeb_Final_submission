package com.example.cha1

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cha1.databinding.ActivityPatientListBinding
import kotlinx.coroutines.delay

class DoctorsListActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPatientListBinding

    private val doctorList: MutableList<DoctorInfo> = mutableListOf()
    private val filteredList: MutableList<DoctorInfo> = mutableListOf()

    var symptoms: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_patient_list)

        symptoms = intent.getStringExtra("symptoms").toString()

        loadData()

        doctorList.forEach {
            if (it.symptoms.contains(symptoms.trim(), true)) {
                filteredList.add(it)
            }
        }

        binding.recyclerview.visibility = View.GONE
        binding.progressbar.visibility = View.VISIBLE
        binding.loadingText.visibility = View.VISIBLE


        binding.recyclerview.layoutManager = LinearLayoutManager(this)

        val adapter = CustomAdapter(filteredList)
        binding.recyclerview.adapter = adapter

        lifecycleScope.launchWhenCreated {
            delay(2000)
            if (filteredList.isEmpty()) {
                binding.recyclerview.visibility = View.GONE
                binding.progressbar.visibility = View.GONE
                binding.loadingText.visibility = View.VISIBLE
                binding.loadingText.text = "No Records Found!!"
            } else {
                binding.recyclerview.visibility = View.VISIBLE
                binding.progressbar.visibility = View.GONE
                binding.loadingText.visibility = View.GONE
            }
        }
    }

    private fun loadData() {
        doctorList.add(DoctorInfo("DR. BS Gupta", "ENT", "ENT Near me,Eye Swelling,Eye Pain"))
        doctorList.add(DoctorInfo("DR. Bhagvan Swaroop", "General Physician", "Physician Near me,Headache,Cold,Nose,Sinus pain"))
        doctorList.add(DoctorInfo("DR. Narayan Sharma", "General Physician", "Physician Near me,Body Pain,Fever"))
        doctorList.add(DoctorInfo("DR. Rahul C", "Cardiologist", "Cardiologist Near me,Heart Attack"))
        doctorList.add(DoctorInfo("DR. Sachin Vaidya", "ENT", "Eye Pain,Earring Loss"))
        doctorList.add(DoctorInfo("DR. Ayushman Kaliaperumal", "Cardiologist", "Chest Pain"))
        doctorList.add(DoctorInfo("DR. Vijayendran", "Diabetology", "Sugar,Frequent Urination"))
        doctorList.add(DoctorInfo("DR. Manu Bharath", "ENT", "Tonsil,Throat Pain,Fever"))
        doctorList.add(DoctorInfo("DR. Supreetha Shenoy","ENT","Sinus pain"))
        doctorList.add(DoctorInfo("DR. Shankar B Medikeri","Cardilogist","Chest Pain,irritation"))
    }
}