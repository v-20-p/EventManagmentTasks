package com.example.todotasks.data.entity

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation

@Entity(tableName = "tag_table")
data class Tag(
    @PrimaryKey @ColumnInfo(name = "tag_name") var tagName: String,
    @ColumnInfo(name = "tag_color") val tagColor: String,
    @ColumnInfo(name = "tag_border") val tagBorder: String,
)
data class TaskWithTagsList(
    @Embedded val tag: Tag,
    @Relation(
        parentColumn = "tag_name",
        entityColumn = "task_tag_name",
    )
    var tasks: List<Task>
)
