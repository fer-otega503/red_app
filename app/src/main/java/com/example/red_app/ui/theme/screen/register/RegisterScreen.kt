package com.example.red_app.ui.theme.screen.register

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.example.red_app.ui.theme.components.Input
import com.example.red_app.ui.theme.components.Message
import com.example.red_app.ui.theme.components.PrimaryButton
import com.example.red_app.ui.theme.validation.AuthValidator
import com.example.red_app.ui.theme.validation.ValidationResult

@Composable
fun RegisterScreen(
    modifier: Modifier = Modifier,
) {
    val nameState = remember { mutableStateOf("") }
    val emailState = remember { mutableStateOf("") }
    val passwordState = remember { mutableStateOf("") }
    val confirmPasswordState = remember { mutableStateOf("") }

    val nameError = remember { mutableStateOf<String?>(null) }
    val emailError = remember { mutableStateOf<String?>(null) }
    val passwordError = remember { mutableStateOf<String?>(null) }
    val confirmPasswordError = remember { mutableStateOf<String?>(null) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color.White)
            .systemBarsPadding(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Message(
            title = "Create Account",
            subtitle = "Register to continue"
        )
        Spacer(modifier = Modifier.height(24.dp))

        Input(
            value = nameState.value,
            onValueChange = {
                nameState.value = it
                nameError.value = null
            },
            label = "Nombre",
            isError = nameError.value != null,
            errorMessage = nameError.value
        )
        Spacer(modifier = Modifier.height(16.dp))

        Input(
            value = emailState.value,
            onValueChange = {
                emailState.value = it
                emailError.value = null
            },
            label = "Correo electrónico",
            isError = emailError.value != null,
            errorMessage = emailError.value
        )
        Spacer(modifier = Modifier.height(16.dp))

        Input(
            value = passwordState.value,
            onValueChange = {
                passwordState.value = it
                passwordError.value = null
            },
            label = "Contraseña",
            visualTransformation = PasswordVisualTransformation(),
            isError = passwordError.value != null,
            errorMessage = passwordError.value
        )
        Spacer(modifier = Modifier.height(16.dp))

        Input(
            value = confirmPasswordState.value,
            onValueChange = {
                confirmPasswordState.value = it
                confirmPasswordError.value = null
            },
            label = "Confirmar contraseña",
            visualTransformation = PasswordVisualTransformation(),
            isError = confirmPasswordError.value != null,
            errorMessage = confirmPasswordError.value
        )
        Spacer(modifier = Modifier.height(24.dp))

        PrimaryButton(
            text = "Register",
            onClick = {
                // Validar campos usando AuthValidator
                val validationResult = AuthValidator.validateRegister(
                    name = nameState.value,
                    email = emailState.value,
                    password = passwordState.value,
                    confirmPassword = confirmPasswordState.value
                )

                when (validationResult) {
                    is ValidationResult.Success -> {
                        // Validación exitosa - aquí puedes implementar el registro
                        // TODO: Implement registration logic
                    }
                    is ValidationResult.Errors -> {
                        // Mostrar errores en cada campo
                        nameError.value = validationResult.fieldErrors["name"]
                        emailError.value = validationResult.fieldErrors["email"]
                        passwordError.value = validationResult.fieldErrors["password"]
                        confirmPasswordError.value = validationResult.fieldErrors["confirmPassword"]
                    }
                }

                when (validationResult) {
                    is ValidationResult.Success -> {
                        // TODO: Implement registration logic
                    }
                    is ValidationResult.Errors -> {
                        nameError.value = validationResult.fieldErrors["name"]
                        emailError.value = validationResult.fieldErrors["email"]
                        passwordError.value = validationResult.fieldErrors["password"]
                        confirmPasswordError.value = validationResult.fieldErrors["confirmPassword"]
                    }
                }
            }
        )
    }
}