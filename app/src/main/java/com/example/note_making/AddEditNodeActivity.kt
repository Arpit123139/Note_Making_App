package com.example.note_making

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.lifecycle.ViewModelProvider
import java.text.SimpleDateFormat
import java.util.*

class AddEditNodeActivity : AppCompatActivity() {

    lateinit var noteTitleEdt:EditText
    lateinit var noteDescriptionEdt:EditText
    lateinit var addupdateBtn:Button
    lateinit var viewModal: NotesViewModel
    var noteID=-1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_edit_node)

        noteTitleEdt=findViewById(R.id.idEditNoteTitle)
        noteDescriptionEdt=findViewById(R.id.idEditNoteDescription)
        addupdateBtn=findViewById(R.id.idBtnAddUpdate)

        viewModal=ViewModelProvider(this,ViewModelProvider.AndroidViewModelFactory.getInstance(application)).get(NotesViewModel::class.java)

        val noteType=intent.getStringExtra("noteType")
        if(noteType.equals("Edit")){
            val noteTitle=intent.getStringExtra("noteTitle")
            val noteDesc=intent.getStringExtra("noteDescription")              // these are the key value pairs
            noteID=intent.getIntExtra("noteID",-1)
            addupdateBtn.text="Update Note"
            noteTitleEdt.setText(noteTitle)
            noteDescriptionEdt.setText(noteDesc)
        }else{
            addupdateBtn.text="Save Note"
        }
        // create a note class and add it in a list
        addupdateBtn.setOnClickListener{
            val noteTitle=noteTitleEdt.text.toString()
            val noteDescription=noteDescriptionEdt.text.toString()

            if(noteType.equals("Edit")){
                if(noteTitle.isNotEmpty() && noteDescription.isNotEmpty()){

                    val sdf=SimpleDateFormat("dd MMM, yyyy - HH:mm")
                    val currentDate:String=sdf.format(Date())
                    val updateNote=Note(noteID,noteTitle,noteDescription,currentDate)
                    viewModal.UpdateNode(updateNote)
                }
            }else{
                if(noteTitle.isNotEmpty() && noteDescription.isNotEmpty()){
                    val sdf=SimpleDateFormat("dd MMM, yyyy - HH:mm")
                    val currentDate:String=sdf.format(Date())
                    val newNote=Note(noteID,noteTitle,noteDescription,currentDate)
                    viewModal.addNode(newNote)

                }

            }
            startActivity(Intent(applicationContext,MainActivity::class.java))
            this.finish()
        }
    }
}