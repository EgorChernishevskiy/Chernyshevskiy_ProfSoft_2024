package com.example.lesson10.ui.profilescreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.lesson10.R

@Composable
fun ProfileScreen(navController: NavHostController, name: String, surname: String, middleName: String, birthdate: String) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        TopAppBar(
            title = { Text(stringResource(R.string.profile_screen_header)) },
            navigationIcon = {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(Icons.Default.ArrowBack, contentDescription = stringResource(R.string.ic_back_description))
                }
            },
            backgroundColor = Color(0xFFD3D3D3),
            contentColor = Color.Black,
        )

        Divider(color = Color.Black, thickness = 1.dp)

        Column(modifier = Modifier) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFFD3D3D3))
                    .padding(top = 16.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.img),
                    contentDescription = stringResource(R.string.profile_image_description),
                    modifier = Modifier
                        .padding(16.dp)
                        .size(72.dp)
                )

                Spacer(modifier = Modifier.width(16.dp))

                Row(
                    modifier = Modifier
                        .background(Color.White, shape = RoundedCornerShape(8.dp))
                        .padding(16.dp)
                        .width(228.dp)
                ) {
                    Column {
                        Text(name)
                        Text(surname)
                        Text(middleName)
                    }

                    Spacer(modifier = Modifier.weight(1f))

                    Column {
                        Text(stringResource(R.string.dob_label))
                        Text(birthdate)
                    }
                }
            }

            Column(Modifier.padding(16.dp)) {
                Text(stringResource(R.string.city_label), color = Color.Gray)
                Text(stringResource(R.string.city_name_label), Modifier.padding(top = 8.dp))
            }


            Column(Modifier.padding(16.dp)) {
                Text(stringResource(R.string.about_label), color = Color.Gray)
                Text(
                    stringResource(R.string.about_content),
                    modifier = Modifier.padding(top = 8.dp)
                )
            }

            Spacer(modifier = Modifier.weight(1f))

            Button(
                onClick = {
                    navController.navigate("editProfile/$name/$surname/$middleName/$birthdate")
                },
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
                    .height(56.dp)
                    .clip(RoundedCornerShape(12.dp)),
                colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF1E88E5))
            ) {
                Text(
                    text = stringResource(R.string.button_edit_profile_label),
                    color = Color.White,
                    fontSize = 14.sp
                )
            }
        }
    }
}
