package com.example.red_app.ui.theme.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.example.red_app.ui.theme.theme.DarkTextColor
import androidx.compose.ui.Alignment
import androidx.compose.ui.tooling.preview.Preview



@Composable
fun Input(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    label: String,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    isError: Boolean = false,
    errorMessage: String? = null
) {
    Box(modifier = modifier) {
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            label = { Text(text = label) },
            visualTransformation = visualTransformation,
            isError = isError,
            shape = MaterialTheme.shapes.medium,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp)
                .height(64.dp),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = if (isError) Color.Red else Color.White,
                unfocusedContainerColor = if (isError) Color.Red else Color.White,
                disabledContainerColor = Color.Red,
                focusedIndicatorColor = DarkTextColor,
                unfocusedIndicatorColor = DarkTextColor,
                unfocusedPlaceholderColor = DarkTextColor,
                focusedPlaceholderColor = DarkTextColor,
            ),
            textStyle = MaterialTheme.typography.bodyLarge.copy(
                fontWeight = FontWeight.Medium,
            ),
            supportingText = {
                if (isError && errorMessage != null) {
                    Text(text = errorMessage, color = Color.Red)
                }
            }
        )
        if (isError && errorMessage != null) {
            Text(
                text = errorMessage,
                color = Color.Red,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(end = 32.dp, top = 0.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun InputPreview()  {
    Input(
        value = "",
        onValueChange = {},
        label = "Correo Electrónico",
        isError = false,
        errorMessage = null
    )
}
