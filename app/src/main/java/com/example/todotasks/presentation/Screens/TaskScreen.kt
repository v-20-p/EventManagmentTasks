package com.example.todotasks.presentation.Screens

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.Button

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetValue
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.todotasks.ui.theme.PrimaryColor
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.TextStyle
import java.util.Locale





@OptIn(ExperimentalFoundationApi::class)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun DateRange(taskDateViewModel: TaskDateViewModel) {
    val startDate = LocalDate.of(2022, 1, 1)
    val endDate = LocalDate.of(2027, 12, 31)
    val currentDate = LocalDate.of(taskDateViewModel.year.intValue, taskDateViewModel.month.intValue, taskDateViewModel.day.intValue)
    val dates = generateSequence(startDate) { it.plusDays(1) }
        .takeWhile { it <= endDate }
        .toList()
    val pagerState = rememberPagerState(pageCount = {
        dates.size / 7
    })



    LaunchedEffect(taskDateViewModel.year.intValue, taskDateViewModel.month.intValue, taskDateViewModel.day.intValue) {
        val index = dates.indexOf(currentDate)
        if (index != -1) {
            pagerState.scrollToPage(index / 7)
        }
    }


    HorizontalPager(state = pagerState) { page ->
        DateRow(dates = dates.subList(page * 7, (page + 1) * 7),currentDate)
    }
}
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun DateRow(dates: List<LocalDate>, currentDate: LocalDate) {
    val currentYear = dates.firstOrNull()?.year.toString()

    Column(
        modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        Text(
            text = currentYear
        )

        Row(
            modifier = Modifier.padding(16.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            val formatterCurrentDate= currentDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))
            dates.forEach { date ->
                val dayOfWeek = date.dayOfWeek.getDisplayName(TextStyle.SHORT, Locale.getDefault())
                val formattedDate = date.format(DateTimeFormatter.ofPattern("dd/MM"))
                val formattedDate2 = date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))


                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.padding(4.dp).background(if (formattedDate2 == formatterCurrentDate) PrimaryColor else Color.Transparent, RoundedCornerShape(4.dp))
                ) {
                    Text(text = formattedDate)
                    Text(text = dayOfWeek)

                }
            }
        }
    }
}



@Preview
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun TaskScreen() {
    val taskDateViewModel= TaskDateViewModel()
    Column{
        Btnsheet(taskDateViewModel)
        DateRange(taskDateViewModel)
    }

}


@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Btnsheet(taskDateViewModel: TaskDateViewModel){

    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true,
        confirmValueChange = { it != SheetValue.Hidden })
    var showBottomSheet by remember { mutableStateOf(false) }


            ExtendedFloatingActionButton(
                text = { Text("Show bottom sheet") },
                icon = { Icon(Icons.Filled.Add, contentDescription = "") },
                onClick = {
                    showBottomSheet = true
                })

        // Screen content

        if (showBottomSheet) {
            ModalBottomSheet(
                onDismissRequest = {
                    showBottomSheet = false
                },
                sheetState = sheetState,
                dragHandle = { BottomSheetDefaults.DragHandle()}
            ) {
                // Sheet content

                    Button(onClick = { showBottomSheet = false}) {
                        Text("Close")

                    }
                    MenuWithScrollStateSample(taskDateViewModel)





        }
    }
}


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MenuWithScrollStateSample(taskDateViewModel: TaskDateViewModel) {
//    val selectedIndexYear = remember { mutableIntStateOf(0) }
//    val selectedIndexMonth = remember { mutableIntStateOf(0) }
//    val selectedIndexDay = remember { mutableIntStateOf(0) }

    Row(
        modifier = Modifier
            .fillMaxWidth(1f)
            .fillMaxHeight(.4f)
            .padding(10.dp),
        Arrangement.Center,Alignment.CenterVertically

    ) {
        //year
        LazyColumn(modifier = Modifier.weight(.3f), verticalArrangement=Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
           val start=2022
            items(6) { index ->
                Box(modifier = Modifier
                    .fillMaxSize()
                    .padding(10.dp)
                    .background(
                        if (taskDateViewModel.year.intValue == start+index) PrimaryColor else Color.Transparent,
                        RoundedCornerShape(4.dp)
                    )
                    .clickable {
                        taskDateViewModel.year.intValue = start+index
                    },
                    contentAlignment = Alignment.Center


                ) {
                    Text(text = "${start+index}" , color = if (taskDateViewModel.year.intValue == index) Color.White else Color.Black )
                }




            }
        }
//        month
        LazyColumn(modifier = Modifier.weight(.3f), verticalArrangement=Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
           val start=1
            items(12) { index ->
                Box(modifier = Modifier
                    .fillMaxSize()
                    .padding(10.dp)
                    .background(
                        if (taskDateViewModel.month.intValue == index+1) PrimaryColor else Color.Transparent,
                        RoundedCornerShape(4.dp)
                    )
                    .clickable {
                        taskDateViewModel.month.intValue = index
                    },
                    contentAlignment = Alignment.Center


                ) {
                    Text(text = "${start+index}" , color = if (taskDateViewModel.month.intValue == index+1) Color.White else Color.Black )
                }




            }
        }
        LazyColumn(modifier = Modifier.weight(.3f), verticalArrangement=Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
           val start=1
            items(31) { index ->
                Box(modifier = Modifier
                    .fillMaxSize()
                    .padding(10.dp)
                    .background(
                        if (taskDateViewModel.day.intValue == index) PrimaryColor else Color.Transparent,
                        RoundedCornerShape(4.dp)
                    )
                    .clickable {
                        taskDateViewModel.day.intValue = index
                    },
                    contentAlignment = Alignment.Center


                ) {
                    Text(text = "${start+index}" , color = if (taskDateViewModel.day.intValue == index) Color.White else Color.Black )
                }




            }
        }
    }

}
