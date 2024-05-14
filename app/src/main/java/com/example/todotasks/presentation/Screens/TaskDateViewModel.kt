package com.example.todotasks.presentation.Screens

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import java.time.LocalDate
import java.time.MonthDay
import java.time.Year
import java.time.YearMonth

@RequiresApi(Build.VERSION_CODES.O)
class TaskDateViewModel :ViewModel() {

    val year = mutableIntStateOf(Year.now().value)

    val month = mutableIntStateOf(MonthDay.now().monthValue)
    val day = mutableIntStateOf(LocalDate.now().dayOfMonth)
    val daysInMonth =  mutableIntStateOf(0)
    fun getDaysInMonth(year: Int, month: Int) {
         daysInMonth.intValue = YearMonth.of(year, month).lengthOfMonth()
    }

}