package com.example.togetherapp.presentation.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.togetherapp.R
import com.example.togetherapp.presentation.ui.navigation.Routes

@Composable
fun BottomNavigationBar(navController: NavHostController) {
    val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route

    Surface(
        color = Color.White,
        modifier = Modifier.height(56.dp)
    ) {
        Column {
            Divider(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(1.dp),
                color = Color.Gray
            )
            NavigationBar(
                contentColor = Color.Black
            ) {
                NavigationBarItem(
                    icon = {
                        Icon(
                            painter = painterResource(R.drawable.ic_nav_home),
                            contentDescription = null,
                            modifier = Modifier
                                .width(20.dp)
                                .height(20.dp)
                        )
                    },
                    selected = currentRoute == Routes.Home,
                    colors = NavigationBarItemDefaults.colors(
                        indicatorColor = Color(0x66D7D7D7),
                        selectedIconColor = Color.Black
                    ),
                    onClick = {
                        if (currentRoute != Routes.Home) {
                            navController.navigate(Routes.Home) {
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    }
                )
                NavigationBarItem(
                    icon = {
                        Icon(
                            painter = painterResource(R.drawable.ic_nav_favourite),
                            contentDescription = null,
                            modifier = Modifier
                                .width(20.dp)
                                .height(20.dp)
                        )
                    },
                    selected = currentRoute == Routes.Favorite,
                    colors = NavigationBarItemDefaults.colors(
                        indicatorColor = Color(0x66D7D7D7),
                        selectedIconColor = Color.Black
                    ),
                    onClick = {
                        if (currentRoute != Routes.Favorite) {
                            navController.navigate(Routes.Favorite) {
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    }
                )
                NavigationBarItem(
                    icon = {
                        Icon(
                            painter = painterResource(R.drawable.ic_nav_add_note),
                            contentDescription = null,
                            modifier = Modifier
                                .width(20.dp)
                                .height(20.dp)
                        )
                    },
                    selected = false,
                    colors = NavigationBarItemDefaults.colors(
                        indicatorColor = Color(0x66D7D7D7),
                        selectedIconColor = Color.Black
                    ),
                    onClick = {
                        navController.navigate(Routes.CreateNote) {
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                )
                NavigationBarItem(
                    icon = {
                        Icon(
                            painter = painterResource(R.drawable.ic_nav_chat),
                            contentDescription = null,
                            modifier = Modifier
                                .width(20.dp)
                                .height(20.dp)
                        )
                    },
                    selected = currentRoute == Routes.Chat,
                    colors = NavigationBarItemDefaults.colors(
                        indicatorColor = Color(0x66D7D7D7),
                        selectedIconColor = Color.Black
                    ),
                    onClick = {
                        if (currentRoute != Routes.Chat) {
                            navController.navigate(Routes.Chat) {
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    }
                )
                NavigationBarItem(
                    icon = {
                        Icon(
                            painter = painterResource(R.drawable.ic_nav_profile),
                            contentDescription = null,
                            modifier = Modifier
                                .width(20.dp)
                                .height(20.dp)
                        )
                    },
                    selected = currentRoute == Routes.Profile,
                    colors = NavigationBarItemDefaults.colors(
                        indicatorColor = Color(0x66D7D7D7),
                        selectedIconColor = Color.Black
                    ),
                    onClick = {
                        if (currentRoute != Routes.Profile) {
                            navController.navigate(Routes.Profile) {
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    }
                )
            }
        }
    }
}