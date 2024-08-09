package com.example.lesson10.ui.profileeditscreen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.lesson10.R

@Composable
fun EditProfileScreen(navController: NavHostController, name: String, surname: String, middleName: String, birthdate: String) {
    var updatedName by remember { mutableStateOf(name) }
    var updatedSurname by remember { mutableStateOf(surname) }
    var updatedMiddleName by remember { mutableStateOf(middleName) }
    var updatedBirthdate by remember { mutableStateOf(birthdate) }
    var errorMessage by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        TopAppBar(
            title = { Text(stringResource(R.string.edit_screen_header)) },
            navigationIcon = {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(Icons.Default.ArrowBack, contentDescription = stringResource(R.string.ic_back_description))
                }
            },
            backgroundColor = Color.White,
            contentColor = Color.Black,
        )

        Divider(color = Color.Black, thickness = 1.dp)

        Column(modifier = Modifier.padding(16.dp)) {
            Row(Modifier.padding(top = 16.dp)) {
                Text(
                    text = stringResource(R.string.name_label),
                    fontSize = 20.sp
                )
                BasicTextField(
                    value = updatedName,
                    onValueChange = { updatedName = it },
                    textStyle = TextStyle(
                        fontSize = 20.sp,
                        color = Color.Black
                    ),
                    modifier = Modifier.fillMaxWidth()
                )
            }

            Row(Modifier.padding(top = 16.dp)) {
                Text(
                    text = stringResource(R.string.surname_label),
                    fontSize = 20.sp
                )
                BasicTextField(
                    value = updatedSurname,
                    onValueChange = { updatedSurname = it },
                    textStyle = TextStyle(
                        fontSize = 20.sp,
                        color = Color.Black
                    ),
                    modifier = Modifier.fillMaxWidth()
                )
            }

            Row(Modifier.padding(top = 16.dp)) {
                Text(
                    text = stringResource(R.string.middle_name_label),
                    fontSize = 20.sp
                )
                BasicTextField(
                    value = updatedMiddleName,
                    onValueChange = { updatedMiddleName = it },
                    textStyle = TextStyle(
                        fontSize = 20.sp,
                        color = Color.Black
                    ),
                    modifier = Modifier.fillMaxWidth()
                )
            }

            Row(Modifier.padding(top = 16.dp)) {
                Text(
                    text = stringResource(R.string.dob_label),
                    fontSize = 20.sp
                )
                BasicTextField(
                    value = updatedBirthdate,
                    onValueChange = { updatedBirthdate = it },
                    textStyle = TextStyle(
                        fontSize = 20.sp,
                        color = Color.Black
                    ),
                    modifier = Modifier.fillMaxWidth()
                )
            }

            Spacer(modifier = Modifier.weight(1f))

            if (errorMessage.isNotEmpty()) {
                Text(
                    text = errorMessage,
                    color = Color.Red,
                    fontSize = 14.sp,
                    modifier = Modifier.padding(bottom = 16.dp)
                )
            }

            Button(
                onClick = {
                    if (updatedName.isBlank() || updatedSurname.isBlank() || updatedMiddleName.isBlank() || updatedBirthdate.isBlank()) {
                        errorMessage = "Все поля должны быть заполнены."
                    } else {
                        errorMessage = ""
                        navController.navigate("profile/$updatedName/$updatedSurname/$updatedMiddleName/$updatedBirthdate") {
                            popUpTo("profile") { inclusive = true }
                        }
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .clip(RoundedCornerShape(12.dp)),
                colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF1E88E5))
            ) {
                Text(
                    stringResource(R.string.button_save_label),
                    color = Color.White,
                    fontSize = 14.sp
                )
            }
        }
    }
}
