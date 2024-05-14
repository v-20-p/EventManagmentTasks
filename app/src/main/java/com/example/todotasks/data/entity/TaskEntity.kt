package com.example.todotasks.data.entity

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class Task (
    @PrimaryKey (autoGenerate = true)
    @ColumnInfo(name = "task_id")
    var taskId: Long?=null ,
    @ColumnInfo(name = "task_title")
    val taskTitle: String ,
    @ColumnInfo(name = "task_description")
    val taskDescription: String,
    @ColumnInfo(name = "task_date")
    val taskDate: String ,
    @ColumnInfo(name = "task_time")
    val taskTime: String ,
    @ColumnInfo (name = "time_to" )
    val timeTo: String?="",
    @ColumnInfo (name = "time_from" )
    val timeFrom: String?="",
    @ColumnInfo(name = "task_Type")
    val taskType: String,
    @ColumnInfo(name = "task_tag_name")
    val taskTag: String,

)

enum class TaskType(val type: String){
    Pending("Pending"),
    Completed("Completed"),
    Cancelled("Cancelled"),
    OnGoing("OnGoing")
}