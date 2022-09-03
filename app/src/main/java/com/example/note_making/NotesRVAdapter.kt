package com.example.note_making

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class NotesRVAdapter(val context: Context,val  noteClickInteface:NoteClickInterface,val noteClickDeleteInterface:NoteClickDeleteInterface) :RecyclerView.Adapter<NotesRVAdapter.ViewHolder>(){

    private val allNotes=ArrayList<Note>()

    inner class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){

        val noteTV=itemView.findViewById<TextView>(R.id.idTVNoteTitle)
        val timeTV=itemView.findViewById<TextView>(R.id.idTVTimeStamp)
        val deleteTV=itemView.findViewById<ImageView>(R.id.idTVDelete)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView=LayoutInflater.from(parent.context).inflate(R.layout.notes_rv_item,parent,false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        //Initializing all the views
        holder.noteTV.setText(allNotes.get(position).noteTitle)
        holder.timeTV.setText("Last Updated : "+allNotes.get(position).timeStamp)

        holder.deleteTV.setOnClickListener{
            noteClickDeleteInterface.onDeleteIconClick(allNotes.get(position))
        }

        holder.itemView.setOnClickListener{
            noteClickInteface.onNoteClick(allNotes.get((position)))
        }
    }

    override fun getItemCount(): Int {
        return allNotes.size
    }

    fun updateList(newList: List<Note>)
    {
        allNotes.clear()
        allNotes.addAll(newList)
        notifyDataSetChanged()
    }


}

interface NoteClickDeleteInterface{
    fun onDeleteIconClick(note:Note)
}
interface NoteClickInterface{
    fun onNoteClick(note:Note)
}