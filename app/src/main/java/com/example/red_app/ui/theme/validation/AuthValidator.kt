package com.example.red_app.ui.theme.validation
import android.util.Patterns
/**
 * AuthValidator provides form validation for authentication flows.
 * Includes methods for validating registration and login forms,
 * returning a ValidationResult indicating success or field-specific errors.
 */
object AuthValidator {
    private val EMAIL_PATTERN = Patterns.EMAIL_ADDRESS

    fun validateRegister(
        name: String,
        email: String,
        password: String,
        confirmPassword: String
    ): ValidationResult {
        val errors = mutableMapOf<String, String>()

        // Nombre
        if (name.isBlank()) {
            errors["name"] = "El nombre es requerido"
        }

        // Email
        if (email.isBlank()) {
            errors["email"] = "El email es requerido"
        } else if (!EMAIL_PATTERN.matcher(email).matches()) {
            errors["email"] = "El email no es válido"
        }

        // Contraseña
        if (password.isBlank()) {
            errors["password"] = "La contraseña es requerida"
        } else if (password.length < 8) {
            errors["password"] = "La contraseña debe tener al menos 8 caracteres"
        }

        // Confirmar contraseña
        if (confirmPassword.isBlank()) {
            errors["confirmPassword"] = "La confirmación es requerida"
        } else if (password != confirmPassword) {
            errors["confirmPassword"] = "Las contraseñas no coinciden"
        }

        return if (errors.isEmpty()) {
            ValidationResult.Success
        } else {
            ValidationResult.Errors(errors)
        }
    }

    fun validateLogin(
        email: String,
        password: String
    ): ValidationResult {
        val errors = mutableMapOf<String, String>()

        // Email
        if (email.isBlank()) {
            errors["email"] = "El email es requerido"
        } else if (!EMAIL_PATTERN.matcher(email).matches()) {
            errors["email"] = "El email no es válido"
        }

        // Contraseña
        if (password.isBlank()) {
            errors["password"] = "La contraseña es requerida"
        }

        return if (errors.isEmpty()) {
            ValidationResult.Success
        } else {
            ValidationResult.Errors(errors)
        }
    }
}

// Resultado de la validación
sealed class ValidationResult {
    object Success : ValidationResult()
    data class Errors(val fieldErrors: Map<String, String>) : ValidationResult()
}