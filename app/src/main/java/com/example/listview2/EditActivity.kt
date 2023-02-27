package com.example.listview2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.listview2.databinding.ActivityEditBinding

class EditActivity : AppCompatActivity() {
    lateinit var binding: ActivityEditBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityEditBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.editOfEditText.setText(intent.getStringExtra("name"))

        binding.saveBtn.setOnClickListener {
            finish()
        }



    }
}