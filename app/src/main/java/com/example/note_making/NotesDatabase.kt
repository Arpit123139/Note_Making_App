package com.example.note_making

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

    @Database(entities = [Note::class], version = 4 , exportSchema = false )
    abstract class NotesDatabase : RoomDatabase() {

        abstract fun getNotesDao(): NotesDao

        companion object {
            @Volatile
            private var instance: NotesDatabase? = null

         fun getDataBase(context:Context) = instance?: synchronized(this){
             Room.databaseBuilder(
                 context.applicationContext,
                 NotesDatabase::class.java,
             "notesTable"
             ).build().also { instance = it }
         }


        }
    }
