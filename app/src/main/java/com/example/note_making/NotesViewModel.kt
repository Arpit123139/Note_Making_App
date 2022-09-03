package com.example.note_making

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import androidx.room.Update
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.w3c.dom.Node


class NotesViewModel(application: Application) : AndroidViewModel(application) {




    val dao=NotesDatabase.getDataBase(application).getNotesDao()
    val repo:NoteRepository= NoteRepository(dao)
    val allNotes:LiveData<List<Note>> = repo.allNotes

//    init {
//        val dao =NotesDatabase.getDataBase(application).getNotesDao()
//        repo=NoteRepository(dao)
//        allNotes=repo.allNotes
//    }



    fun deleteNode(note:Note)=viewModelScope.launch (Dispatchers.IO){
        repo.delete(note)
    }
    fun UpdateNode(note:Note)=viewModelScope.launch (Dispatchers.IO){
        repo.update(note)
    }
    fun addNode(note:Note)=viewModelScope.launch (Dispatchers.IO){
        repo.insert(note)
    }
}