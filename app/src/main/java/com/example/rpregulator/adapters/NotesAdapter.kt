package com.example.rpregulator.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.rpregulator.databinding.RvItemNotesBinding
import com.example.rpregulator.models.Notes
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.firebase.ui.database.FirebaseRecyclerOptions

class NotesAdapter(options: FirebaseRecyclerOptions<Notes>, val onClickListener: OnClickListener) :
    FirebaseRecyclerAdapter<Notes, NotesAdapter.NotesHolder>(options) {

    class NotesHolder(private var binding: RvItemNotesBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(notes: Notes) {
            binding.txtTitle.text = notes.title
            binding.txtDesc.text = notes.desc

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesHolder {
        return NotesHolder(RvItemNotesBinding.inflate(LayoutInflater.from(parent.context)))
    }


    override fun onBindViewHolder(holder: NotesHolder, position: Int, model: Notes) {
        val item = getItem(position)
        holder.bind(item)
        holder.itemView.setOnClickListener {
            onClickListener.onClick(item)
        }
    }

    class OnClickListener(val clickListener: (notes: Notes) -> Unit) {
        fun onClick(notes: Notes) = clickListener(notes)
    }


}