package com.example.lesson9

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.lesson9.ui.components.CustomButton
import com.example.lesson9.ui.theme.Lesson9Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Lesson9Theme {
                MainScreen { navigateToSecondActivity() }
            }
        }
    }

    private fun navigateToSecondActivity() {
        val intent = Intent(this, SecondActivity::class.java).apply {
            putExtra("message", getString(R.string.greetings_textView_label))
        }
        startActivity(intent)
    }
}

@Composable
fun MainScreen(onNavigateClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(id = R.string.greetings_textView_label),
            style = MaterialTheme.typography.h6,
            modifier = Modifier.padding(bottom = 20.dp)
        )

        CustomButton(
            text = stringResource(id = R.string.navigate_button_label),
            onClick = onNavigateClick,
            modifier = Modifier.width(296.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    Lesson9Theme {
        MainScreen(onNavigateClick = {})
    }
}
