package com.example.todotasks.presentation.navigation.nav

import androidx.annotation.DrawableRes
import androidx.compose.animation.animateColor
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row


import androidx.compose.foundation.layout.fillMaxWidth

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material3.Button

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.navigation
import com.example.todotasks.presentation.Screens.HomeScreen
import com.example.todotasks.presentation.Screens.TaskScreen
import com.example.todotasks.presentation.Screens.auth.AuthViewModel
import com.example.todotasks.presentation.Screens.auth.LoginScreen
import com.example.todotasks.presentation.Screens.auth.SignUpScreen
import com.example.todotasks.presentation.Screens.auth.Splash
import com.example.todotasks.presentation.navigation.destination.destination
import com.example.todotasks.ui.theme.PrimaryColor




@Composable
fun EventAppNavigation(authViewModel: AuthViewModel, navController: NavHostController) {
    val context = LocalContext.current
    NavHost(navController = navController,
        startDestination = authViewModel.isSigned.value){
        authNavigation(navController,authViewModel)

        mainNavigation(navController,authViewModel){
            authViewModel.logOut(context)
        }
    }

}

fun NavGraphBuilder.authNavigation(navController: NavHostController, authViewModel: AuthViewModel) {
    navigation(startDestination = destination.AuthScreens.SplashRoute.title,route= destination.AuthScreens.title){
        composable(
                route = destination.AuthScreens.SplashRoute.title,
            ) {
                Splash(navController)
            }
            composable(route = destination.AuthScreens.SignUpRoute.title) {
                SignUpScreen(navController,authViewModel)
            }
            composable(route = destination.AuthScreens.LoginRoute.title) {
                LoginScreen(navController,authViewModel)
            }
    }
}
fun NavGraphBuilder.mainNavigation(navController: NavHostController,
                                   authViewModel: AuthViewModel,
                                   logOut:()->Unit) {

    navigation(
        startDestination = destination.MainScreens.HomeRoute.title,
        route = destination.MainScreens.title
    ) {
        composable(route = destination.MainScreens.HomeRoute.title) {
            HomeScreen(navController)
        }
        composable(route = destination.MainScreens.TaskRoute.title) {
//            TaskScreen()
Column {

}
        }
        composable(route = destination.MainScreens.GraphicRoute.title) {
            //GraphicScreen(navController)
            Column{
                Button(onClick = { logOut.invoke()}){

                }
                }
        }
        composable(route = destination.MainScreens.ProfileRoute.title) {
            //ProfileScreen(navController)
            Column {

            }
    }
}
}

@Composable
fun ButtonNav(
    navController: NavHostController,
    showBottonBar: Boolean,
    function: @Composable (Modifier) -> Unit
) {

    val tabBarItems = listOf(
        destination.MainScreens.HomeRoute,
        destination.MainScreens.TaskRoute,
        destination.MainScreens.GraphicRoute,
        destination.MainScreens.ProfileRoute
    )


    val bottomBarContent: @Composable () -> Unit = {
        TabView(
            tabBarItems = tabBarItems,
            navController = navController
        )
    }


    Scaffold(
        bottomBar = {
            if (showBottonBar) {
                Surface(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp),
                    color = Color.Transparent,
                    shape = RoundedCornerShape(20.dp),
                    shadowElevation=4.dp
                ) {
                    bottomBarContent()
                }
            }
        },
        content = {

            Column{
                function(Modifier.padding(it))
            }

        }
    )
}

fun NavOptionsBuilder.popUpToTop(navController: NavController){
    popUpTo(navController.currentBackStackEntry?.destination?.route ?:return){
        inclusive=true
        saveState=true
    }

}


@Composable
fun TabView(tabBarItems: List<destination>, navController: NavHostController) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    NavigationBar(containerColor = Color.White, contentColor = Color.Blue, modifier = Modifier

    ) {
        Row(Modifier.fillMaxWidth(),verticalAlignment=Alignment.CenterVertically){
            tabBarItems.forEachIndexed { index, tabBarItem ->

                NavigationBarItem(
                    selected = currentRoute == tabBarItem.title,
                    onClick = {
                        if (currentRoute != tabBarItem.title) {
                            navController.navigate(tabBarItem.title) {
                                popUpToTop(navController)
                                restoreState = true
                                launchSingleTop = true

                            }
                        }
                    },
                    icon = {
                        TabBarIconView(
                            isSelected = currentRoute == tabBarItem.title,
                            selectedIcon = tabBarItem.SelectedIcon,
                            icon = tabBarItem.Icon

                        )
                    },


                    alwaysShowLabel = false,
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = Color.Transparent,
                        unselectedIconColor = Color.Transparent,

                        indicatorColor = Color.White
                    ),
                    modifier = Modifier.padding(top = 20.dp)
                )
                if (index == 1) {
                    Icon(
                        Icons.Default.AddCircle,
                        contentDescription = null,
                        modifier = Modifier
                            .size(90.dp)
                            .padding(top = 10.dp),
                        tint = PrimaryColor
                    )
                }
            }
        }
    }
}

@Composable
fun TabBarIconView(
    isSelected: Boolean,
    @DrawableRes
    selectedIcon: Int,
    @DrawableRes
    icon: Int,
) {
    val iconSize = remember { Animatable(10.dp.value) }
    val boxSize = remember { Animatable(7.dp.value) }

    LaunchedEffect(isSelected) {
        if (isSelected) {
            iconSize.animateTo(30.dp.value)
            boxSize.animateTo(10.dp.value)
        } else {
            iconSize.animateTo(30.dp.value)
            boxSize.animateTo(7.dp.value)
        }
    }
    val infiniteTransition = rememberInfiniteTransition(label = "")
    val color by infiniteTransition.animateColor(
        initialValue = PrimaryColor,
        targetValue = PrimaryColor.copy(.5f), // Dark Red
        animationSpec = infiniteRepeatable(
            animation = tween(1000, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        ), label = ""
    )

    Column(
        verticalArrangement = Arrangement.spacedBy(5.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            painter = if (isSelected) painterResource(id = selectedIcon) else painterResource(id = icon),
            contentDescription = null,
            tint = if (isSelected) color else Color.Gray,
            modifier = Modifier.size(iconSize.value.dp)
        )
        if (isSelected) {
            Box(
                modifier = Modifier
                    .size(boxSize.value.dp)
                    .background(color, RoundedCornerShape(100f))
            ) {}
        }
    }
}
