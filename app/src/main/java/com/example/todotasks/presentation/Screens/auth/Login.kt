package com.example.todotasks.presentation.Screens.auth

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.todotasks.presentation.navigation.destination.destination
import com.example.todotasks.ui.theme.PrimaryColor

@Composable
fun LoginScreen(navController: NavHostController, authViewModel: AuthViewModel) {
    Column(
        Modifier
            .fillMaxSize()
            .padding(0.dp, 100.dp, 0.dp, 0.dp)) {
        Text(text = "Login ", Modifier.padding(50.dp,60.dp),color = PrimaryColor, fontWeight = FontWeight.Bold, fontSize = 40.sp)
        Column(Modifier.fillMaxWidth(), Arrangement.spacedBy(20.dp), Alignment.CenterHorizontally) {
            val textState = remember {
                mutableStateOf(
                    mapOf(
                        "username" to "",
                        "email" to "",
                        "password" to ""
                    )
                )
            }



            TextField(
                value = textState.value["email"] ?: "",
                onValueChange = { newText ->
                    textState.value += ("email" to newText)
                },
                modifier = Modifier.fillMaxWidth(.8f),
                placeholder = {
                    Text(text = "email", color = Color.Gray)
                },
                leadingIcon = {
                    Icon(Icons.Default.Email, contentDescription = "Username",tint = Color.Gray)
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                colors = TextFieldDefaults.colors(
                    unfocusedContainerColor = Color.Transparent,
                    focusedContainerColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Gray,
                )
            )

            TextField(
                value = textState.value["password"] ?: "",
                onValueChange = { newText ->
                    textState.value += ("password" to newText)
                },
                placeholder = {
                    Text(text = "password", color = Color.Gray)
                },
                modifier = Modifier.fillMaxWidth(.8f),
                leadingIcon = {
                    Icon(Icons.Default.Lock, contentDescription = "Username",tint = Color.Gray)
                },
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                colors = TextFieldDefaults.colors(
                    unfocusedContainerColor = Color.Transparent,
                    focusedContainerColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Gray,
                )
            )
            Row(Modifier.fillMaxWidth(.8f), Arrangement.End, Alignment.CenterVertically) {
                Text(text = "Forgot Password?", fontSize = 12.sp, color = PrimaryColor)

            }
            Spacer(modifier= Modifier.height(15.dp))
            Button(onClick = { /*TODO*/ },
                Modifier
                    .fillMaxWidth(.8f)
                    .height(
                        80.dp
                    )
                    .padding(0.dp, 10.dp), colors = ButtonDefaults.buttonColors(
                    PrimaryColor
                ), shape = RoundedCornerShape(15.dp)
            ) {
                Text(text = "Login", fontSize = 18.sp)
            }
            Row(Modifier.padding(20.dp),verticalAlignment = Alignment.CenterVertically) {
                Divider(Modifier.weight(1f))
                Text("or with", Modifier.padding(10.dp,0.dp), color = Color.Gray, fontSize = 12.sp)
                Divider(Modifier.weight(1f))
            }
            SignInWithGoogle(authViewModel)

            Column(Modifier.fillMaxSize(.8f), Arrangement.Bottom, Alignment.CenterHorizontally) {
                Row{
                    Text(text = "Don't have an account?",fontSize = 12.sp, color = Color.Gray)
                    Text(text = "Sign Up", modifier = Modifier.clickable { navController.navigate(
                        destination.AuthScreens.SignUpRoute.title) },fontSize = 12.sp)
                }
            }
        }
    }
}