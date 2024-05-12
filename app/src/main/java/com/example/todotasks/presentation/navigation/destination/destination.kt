package com.example.todotasks.presentation.navigation.destination

import androidx.annotation.DrawableRes
import com.example.todotasks.R


sealed class destination(val title: String,  @DrawableRes val SelectedIcon:Int=0, @DrawableRes val Icon:Int=0) {

    data object  AuthScreens:destination("auth"){
        data object SplashRoute : destination(title = "Splash")
        data object SignUpRoute : destination(title = "SignUp")
        data object LoginRoute : destination(title = "Login")
    }

    data object MainScreens :destination("main"){
        data object HomeRoute :
            destination(title = "Home", R.drawable.selctedhome, R.drawable.home__2_)

        data object TaskRoute :
            destination(title = "Task", R.drawable.selecteddocument__2_, R.drawable.document)

        data object GraphicRoute :
            destination(title = "graphic", R.drawable.selectedactivity, R.drawable.activity__2_)

        data object ProfileRoute :
            destination(title = "profile", R.drawable.selectedfolder, R.drawable.folder__2_)
    }
}
