package com.example.rpregulator.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.rpregulator.databinding.RvItemNotesBinding
import com.example.rpregulator.models.PaperWork
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.firebase.ui.database.FirebaseRecyclerOptions

class PaperworkAdapter(
    options: FirebaseRecyclerOptions<PaperWork>,
    val onClickListener: OnClickListener
) : FirebaseRecyclerAdapter<PaperWork, PaperworkAdapter.PaperWorkHolder>(options) {

    private val _progressBarShow = MutableLiveData<Boolean?>()
    val progressBar: LiveData<Boolean?>
        get() = _progressBarShow

    class PaperWorkHolder(private var binding: RvItemNotesBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(paperWork: PaperWork) {
            binding.txtTitle.text = paperWork.title
            binding.txtDesc.text = paperWork.desc

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PaperWorkHolder {
        return PaperWorkHolder(RvItemNotesBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: PaperWorkHolder, position: Int, model: PaperWork) {
        val item = getItem(position)
        holder.bind(item)
        holder.itemView.setOnClickListener {
            onClickListener.onClick(item)
        }
    }

    class OnClickListener(val clickListener: (paperWork: PaperWork) -> Unit) {
        fun onClick(paperWork: PaperWork) = clickListener(paperWork)
    }

    override fun onDataChanged() {
        super.onDataChanged()
        _progressBarShow.value = true
    }

}