package com.example.todotasks.presentation.Screens.auth

import android.content.Intent
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon

import androidx.compose.material3.Text
import androidx.compose.material3.TextField

import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation

import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.todotasks.R
import com.example.todotasks.presentation.navigation.destination.destination
import com.example.todotasks.ui.theme.PrimaryColor

import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.Firebase
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.auth
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await


@Composable
fun  SignUpScreen(navController: NavHostController, authViewModel: AuthViewModel) {

    Column(
        Modifier
            .fillMaxSize()
            .padding(0.dp, 60.dp, 0.dp, 0.dp)) {
        Text(text = "Sign Up ", Modifier.padding(50.dp,60.dp),color = PrimaryColor, fontWeight = FontWeight.Bold, fontSize = 40.sp)
       Column(Modifier.fillMaxWidth(),Arrangement.spacedBy(20.dp),Alignment.CenterHorizontally) {
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
                value = textState.value["username"] ?: "",
                onValueChange = { newText ->
                    textState.value += ("username" to newText)
                },
                modifier = Modifier.fillMaxWidth(.8f),
                placeholder = {
                    Text(text = "username", color = Color.Gray)
                },
                leadingIcon = {
                    Icon(Icons.Default.Person, contentDescription = "Username",tint = Color.Gray)
                },
                colors = TextFieldDefaults.colors(
                    unfocusedContainerColor = Color.Transparent,
                    focusedContainerColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Gray,
                )

            )

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
                },visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                colors = TextFieldDefaults.colors(
                    unfocusedContainerColor = Color.Transparent,
                    focusedContainerColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Gray,
                )
            )
            Spacer(modifier= Modifier.height(15.dp))
            Button(onClick = { /*TODO*/ },
                Modifier
                    .fillMaxWidth(.8f)
                    .height(
                        80.dp
                    )
                    .padding(0.dp, 10.dp), colors = ButtonDefaults.buttonColors(
                PrimaryColor), shape = RoundedCornerShape(15.dp)
            ) {
                Text(text = "Create", fontSize = 18.sp)
            }
           Row(Modifier.padding(20.dp),verticalAlignment = Alignment.CenterVertically) {
               Divider(Modifier.weight(1f))
               Text("or with",Modifier.padding(10.dp,0.dp), color = Color.Gray, fontSize = 12.sp)
               Divider(Modifier.weight(1f))
           }
           SignInWithGoogle(authViewModel)

           Column(Modifier.fillMaxSize(.6f),Arrangement.Bottom,Alignment.CenterHorizontally) {
               Row{
                   Text(text = "Already have an account? ",fontSize = 12.sp, color = Color.Gray)
                   Text(text = "Login", modifier = Modifier.clickable { navController.navigate(destination.AuthScreens.LoginRoute.title) },fontSize = 12.sp)
               }
           }
        }
    }



}
@Composable
fun SignInWithGoogle(authViewModel: AuthViewModel) {
    val token="609482402985-n90ovl64ma8rdlraqgj8f5q9cahin7fh.apps.googleusercontent.com"
    var user by remember {
        mutableStateOf(Firebase.auth.currentUser)
    }
    val launcher = rememberFirebaseAuthLauncher(
        oAuthComplete = { result ->
            user =result.user
            authViewModel.isSigned.value=destination.MainScreens.title
        },
        onAuthError = {
            user=null
            authViewModel.isSigned.value=destination.AuthScreens.title
            println(it.message)

        })
    val context = LocalContext.current

    Column (Modifier ,Arrangement.Top,Alignment.CenterHorizontally) {

        if (user ==null){
//            Text(text = "not logged in ")
            authViewModel.isSigned.value=destination.AuthScreens.title
            Button(onClick = {
                val gso= GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                    .requestIdToken(token)
                    .requestEmail()
                    .build()
                val googleSignInClient =GoogleSignIn.getClient(context,gso)
                launcher.launch(googleSignInClient.signInIntent)

            },colors = ButtonDefaults.buttonColors(containerColor=Color.White,contentColor=Color.Black),
                elevation= ButtonDefaults.buttonElevation(6.dp),
                shape = RoundedCornerShape(15.dp),
                modifier = Modifier
                    .fillMaxWidth(.8f)
                    .height(
                        60.dp
                    )
                ) {
                Text(text = "sign in via google    ")
                Image(painter = painterResource(id = R.drawable.google), contentDescription = null)
            }
        }else{
//            navController.navigate(destination.MainScreens.HomeRoute.title)
            authViewModel.isSigned.value=destination.MainScreens.title
            Text(text = "Welcome ${user!!.displayName}")
            AsyncImage(model = user!!.photoUrl, contentDescription =null ,
                modifier = Modifier
                    .clip(CircleShape)
                    .size(40.dp)
            )
            Button(onClick = {
                Firebase.auth.signOut()
                user=null
            }) {
                Text(text = "Sign Out")
            }
            Button(onClick = {
//                navController.navigate(destination.MainScreens.HomeRoute.title)
                user=null
            }) {
                Text(text = "Sign")
            }
        }

    }

}

@Composable
fun rememberFirebaseAuthLauncher(
    oAuthComplete:(AuthResult) -> Unit,
    onAuthError: (ApiException)->Unit

    ):ManagedActivityResultLauncher<Intent,ActivityResult> {
    val scope = rememberCoroutineScope()
    return rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
        try {
            val account = task.getResult(ApiException::class.java)
            val credential = GoogleAuthProvider.getCredential(account.idToken, null)
            scope.launch {
                val authResult = Firebase.auth.signInWithCredential(credential).await()
                oAuthComplete(authResult)
            }
        } catch (e: ApiException) {
            onAuthError(e)
        }
    }
}





