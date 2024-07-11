package com.example.medicinetracker

import com.example.medicinetracker.vm.LoginViewModel
import junit.framework.TestCase.assertFalse
import junit.framework.TestCase.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class LoginValidatorTest {
    @Mock
    private lateinit var viewModel: LoginViewModel

    private val validEmail = "faraz@gmail.com"
    private val invalidEmail = "invalid.email"

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        viewModel = LoginViewModel()
    }


    @Test
    fun `validate should return true for valid email and password`() {

        viewModel = LoginViewModel()
        viewModel.email.value = validEmail
        viewModel.password.value = "password123"

        val result = viewModel.validate()

        assertTrue(result)
    }

    @Test
    fun `validate should return false if email is blank`() {

        viewModel = LoginViewModel()
        viewModel.email.value = ""
        viewModel.password.value = "password123"

        val result = viewModel.validate()

        assertFalse(result)
    }

    @Test
    fun `validate should return false if password is blank`() {

        viewModel = LoginViewModel()
        viewModel.email.value = "faraz@gmail.com"
        viewModel.password.value = ""

        val result = viewModel.validate()

        assertFalse(result)
    }

    @Test
    fun `validate should return false if email is invalid`() {

        viewModel = LoginViewModel()
        viewModel.email.value = invalidEmail
        viewModel.password.value = "password123"

        val result = viewModel.validate()

        assertFalse(result)
    }


}