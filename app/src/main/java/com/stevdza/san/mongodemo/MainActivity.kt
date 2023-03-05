package com.stevdza.san.mongodemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.stevdza.san.mongodemo.navigation.Screen
import com.stevdza.san.mongodemo.navigation.SetupNavGraph
import com.stevdza.san.mongodemo.ui.theme.MongoDemoTheme
import com.stevdza.san.mongodemo.util.Constants.APP_ID
import io.realm.kotlin.mongodb.App

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MongoDemoTheme {
                val navController = rememberNavController()
                SetupNavGraph(
                    startDestination = getStartDestination(),
                    navController = navController
                )
            }
        }
    }
}

private fun getStartDestination(): String {
    val user = App.create(APP_ID).currentUser
    return if (user != null && user.loggedIn) Screen.Home.route
    else Screen.Authentication.route
}