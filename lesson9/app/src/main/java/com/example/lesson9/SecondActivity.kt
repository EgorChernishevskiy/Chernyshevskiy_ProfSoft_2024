package com.example.lesson9

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.lesson9.ui.components.CustomButton
import com.example.lesson9.ui.components.RedRectangle
import com.example.lesson9.ui.theme.Lesson9Theme

class SecondActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val message = intent.getStringExtra("message")

        setContent {
            Lesson9Theme {
                SecondScreen(message = message ?: "") {
                    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}

@Composable
fun SecondScreen(message: String, onShowNotificationClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        RedRectangle()

        Spacer(modifier = Modifier.height(50.dp))

        Image(
            painter = painterResource(id = R.drawable.img),
            contentDescription = null,
            modifier = Modifier.size(260.dp, 242.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        CustomButton(
            text = stringResource(id = R.string.notification_button_label),
            onClick = onShowNotificationClick,
            modifier = Modifier.width(296.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SecondScreenPreview() {
    Lesson9Theme {
        SecondScreen(message = "Hello", onShowNotificationClick = {})
    }
}
