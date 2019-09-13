package com.example.bottomnavigationissue

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.bottomnavigationissue.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = DataBindingUtil.setContentView<ActivityLoginBinding>(this, R.layout.activity_login)

        binding.model = this
        binding.lifecycleOwner = this
    }

    fun loginClicked() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}
