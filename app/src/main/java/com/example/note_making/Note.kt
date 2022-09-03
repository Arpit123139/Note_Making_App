package com.example.note_making

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "notesTable", indices = arrayOf(Index(value = ["title"], unique = true)))
data class Note(@PrimaryKey(autoGenerate = true) var id:Int,
                @ColumnInfo(name = "title") val noteTitle:String,
                @ColumnInfo(name="Description")val noteDescription:String,
                @ColumnInfo(name="timeStamp")val timeStamp:String)