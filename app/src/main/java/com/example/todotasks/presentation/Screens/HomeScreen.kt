package com.example.todotasks.presentation.Screens


import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.todotasks.R
import com.example.todotasks.ui.theme.PrimaryColor

@Composable
fun HomeScreen(navController: NavHostController) {
Column(Modifier.fillMaxSize()) {

    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(30.dp, 50.dp, 30.dp, 0.dp),Arrangement.SpaceBetween,Alignment.CenterVertically) {
        Column {
            Text(text = "Hi, Khalid", Modifier, fontSize = 30.sp, fontWeight = FontWeight.Bold)
            Text(text = "Let’s make this day productive",fontSize = 13.sp,)
        }
            Icon(Icons.Default.Person, contentDescription ="" )

    }
    LazyColumn(modifier = Modifier
        .fillMaxWidth()
        .padding(30.dp, 50.dp, 30.dp, 0.dp)) {
        item {
            Column {
                Text(text = "My Task", Modifier, fontSize = 25.sp, fontWeight = FontWeight.Bold)
               Spacer(modifier = Modifier.height(10.dp))

                Row(Modifier.fillMaxWidth()) {
                    Column(Modifier.weight(1f)) {
                        CardTasks(

                            task = "Completed",
                            taskImage = R.drawable.imac_1,
                            containerColor1 = Color(0xff81cae8),
                            contentColor1 = Color.Black,
                            size1 = 130.dp,
                            size2 = 100.dp
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                        CardTasks(

                            task = "Canceled",
                            taskImage = R.drawable.close_square,
                            containerColor1 = Color(0xffe77d7d),
                            contentColor1 = Color.White
                        )

                    }
                    Spacer(modifier = Modifier.width(15.dp))
                    Column(Modifier.weight(1f)) {
                        CardTasks(

                            task = "Pending",
                            taskImage = R.drawable.stroke_1,
                            containerColor1 = Color(0xff8a94ea),
                            contentColor1 = Color.White
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                        CardTasks(

                            task = "On Going",
                            taskImage = R.drawable.folder_1,
                            containerColor1 = Color(0xff82e89e),
                            contentColor1 = Color.Black,
                            size1 = 130.dp,
                            size2 = 100.dp
                        )

                    }
                }
                }
            

            }
        item { Row(
            Modifier
                .fillMaxWidth()
                .padding(0.dp, 23.dp),Arrangement.SpaceBetween){
            Text(
                text = "Today Task",
                Modifier,
                fontSize = 25.sp,
                fontWeight = FontWeight.Bold
            )
            Text(text = "view All")

        } }

            items(5){
                Spacer(modifier = Modifier.height(15.dp))
                Card(modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = Color(0xFFF1F4FF)
                    )

                    ) {
                    Column{
                        Row(
                            Modifier
                                .fillMaxWidth()
                                .padding(15.dp), Arrangement.SpaceBetween
                        ) {

                            Row {
                                Box(
                                    modifier = Modifier
                                        .height(50.dp)
                                        .width(3.dp)
                                        .background(PrimaryColor)
                                        .padding(0.dp, 40.dp)

                                )
                                Column(modifier = Modifier.padding(10.dp, 0.dp)) {
                                    Text(
                                        text = "Cleaning Clothes",
                                        fontSize = 25.sp,
                                        fontWeight = FontWeight.Bold
                                    )
                                    Text(text = "7:00 - 7:30", fontSize = 15.sp, color = Color.Gray)
                                }
                            }
                            Icon(
                                Icons.Default.MoreVert,
                                contentDescription = "",
                                tint = Color.Gray,
                                modifier = Modifier.size(24.dp)
                            )

                        }
                        Row(
                            Modifier
                                .fillMaxWidth()
                                .padding(25.dp, 10.dp), Arrangement.spacedBy(10.dp)){
                            for (i in 1..3) {
                                Box (Modifier.background(PrimaryColor, shape = RoundedCornerShape(5.dp))) {
                                   Text(text = "Urgent",modifier = Modifier.padding(5.dp),color = Color.White)
                                }


                            }
                        }
                    }

                }


            }
        item { 
            Spacer(modifier = Modifier.padding(70.dp))
        }


        }
    }

}



@Composable
fun CardTasks( task:String, @DrawableRes taskImage:Int, containerColor1: Color, contentColor1:Color,size1:Dp=65.dp,size2:Dp=size1){
    val containerColorList = listOf( containerColor1,containerColor1.copy(alpha = .6f)).reversed()
    Card(modifier = Modifier
        .fillMaxSize()
        .padding(1.dp)
        .shadow(
            elevation = 25.dp,
            spotColor = containerColor1,
            shape = RoundedCornerShape(10.dp),
            ambientColor = containerColor1
        ), colors =  CardDefaults.cardColors(
        contentColor = contentColor1
    ),
        shape= RoundedCornerShape(10.dp),
        elevation = CardDefaults.outlinedCardElevation(
            defaultElevation= 10.dp
        )


        ) {
        Box(modifier = Modifier
            .fillMaxSize()
            .background(Brush.verticalGradient(containerColorList),)){
            Row(Modifier.fillMaxWidth(.85f),Arrangement.End){
                Icon(
                    painter = painterResource(id = R.drawable.arrowback),
                    contentDescription = "",
                    tint = containerColor1,

                )
                Icon(
                    painter = painterResource(id = R.drawable.arrowback),
                    contentDescription = "",
                    tint = containerColor1

                )
            }
            Column(modifier = Modifier) {
               Row(Modifier.fillMaxWidth(.9f),Arrangement.SpaceBetween,) {
                    Image(
                        painter = painterResource(id = taskImage),
                        modifier = Modifier
                            .size(size1, size2)
                            .padding(10.dp),
                        contentDescription = task,

                    )
                    Column{
                        Spacer(modifier = Modifier.height(8.dp))
                        Icon(
                            painter = painterResource(id = R.drawable.arrow___right),
                            contentDescription = "",
                            tint = contentColor1,
                            modifier = Modifier.size(17.dp)
                        )
                    }

                }
               Column(Modifier.padding(15.dp)){
                   Text(text = task, fontWeight = FontWeight.Bold, fontSize = 20.sp)
                   Text(text = "12 tasks")
               }
            }
        }


    }

}