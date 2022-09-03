package com.example.note_making

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
//import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity(), NoteClickDeleteInterface, NoteClickInterface {

    lateinit var notesRV:RecyclerView
    lateinit var addFAB:FloatingActionButton
     lateinit var viewModal:NotesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
      //  viewModal = ViewModelProviders.of(this).get(NotesViewModel::class.java)

        notesRV=findViewById(R.id.idrVNotes)
        addFAB=findViewById(R.id.idFABAddNote)
        notesRV.layoutManager=LinearLayoutManager(this)

        val notesRVAdapter=NotesRVAdapter(this,this,this)
        notesRV.adapter=notesRVAdapter
       viewModal= ViewModelProvider(this,ViewModelProvider.AndroidViewModelFactory.getInstance(application)).get(NotesViewModel::class.java)

        viewModal.allNotes.observe(this, Observer { list->
            list?.let {
                notesRVAdapter.updateList(it)
            }
        })

        addFAB.setOnClickListener{
           //new Intent is created
            val intent= Intent(this@MainActivity,AddEditNodeActivity::class.java)
            startActivity(intent)
            this.finish()
        }
    }

    override fun onDeleteIconClick(note: Note) {
        viewModal.deleteNode(note)
        Toast.makeText(this,"${note.noteTitle} Deleted",Toast.LENGTH_LONG).show()
    }

    override fun onNoteClick(note: Note) {
        //pass the data
        val intent= Intent(this@MainActivity,AddEditNodeActivity::class.java)
        intent.putExtra("noteType","Edit")
        intent.putExtra("noteTitle",note.noteTitle)
        intent.putExtra("noteDescription",note.noteDescription)
        intent.putExtra("noteID",note.id)
        startActivity(intent)
        this.finish()
    }
}