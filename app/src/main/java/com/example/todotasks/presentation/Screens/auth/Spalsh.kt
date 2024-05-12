package com.example.todotasks.presentation.Screens.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.todotasks.R
import com.example.todotasks.presentation.navigation.destination.destination
import com.example.todotasks.ui.theme.PrimaryColor
import com.example.todotasks.ui.theme.Purple80



@Composable
fun Splash(navController: NavHostController) {
    Column(modifier = Modifier
        .fillMaxSize()
      , Arrangement.Center, Alignment.CenterHorizontally){


        Image(painter = painterResource(id = R.drawable.clock), contentDescription = "")
        Spacer(modifier = Modifier.padding(20.dp))
        Text(text =  buildAnnotatedString {
            append("Dailoz")
            withStyle(style = SpanStyle(color = Color.Red)) {
                append(".")
            }
        }, fontSize = 45.sp, color = PrimaryColor, fontWeight = FontWeight.ExtraBold)
        Spacer(modifier = Modifier.padding(10.dp))
        Text(text = "Plan what you will do to be more organized for today, tomorrow and beyond", textAlign = TextAlign.Center)
        Spacer(modifier = Modifier.padding(50.dp))

        Button(onClick = { navController.navigate(destination.AuthScreens.LoginRoute.title) },
            Modifier
                .fillMaxWidth(.9f)
                .height(
                    60.dp
                )
        , colors = ButtonDefaults.buttonColors(PrimaryColor),
            shape = RoundedCornerShape(18.dp)
        ) {
         Text(text = "Login", fontSize = 18.sp)
        }

        Box( modifier = Modifier.fillMaxWidth(.9f)
            .height(
                80.dp
            ).padding(10.dp),Alignment.Center) {
            Text(text = "Sign Up",Modifier.clickable { navController.navigate(destination.AuthScreens.SignUpRoute.title) }, color = PrimaryColor, fontWeight = FontWeight.Bold, fontSize = 18.sp)

        }
        }



    }
