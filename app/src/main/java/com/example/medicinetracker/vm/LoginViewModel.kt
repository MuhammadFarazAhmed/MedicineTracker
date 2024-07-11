package com.example.medicinetracker.vm

import android.util.Patterns
import androidx.compose.runtime.mutableStateOf
import androidx.core.util.PatternsCompat
import androidx.lifecycle.ViewModel

class LoginViewModel : ViewModel() {
    val email = mutableStateOf("faraz@gmail.com")
    val password = mutableStateOf("12345678")


    fun validate(): Boolean =
        (email.value.isNotBlank() && PatternsCompat.EMAIL_ADDRESS.matcher(email.value.trim())
            .matches()) && password.value.isNotBlank()
}