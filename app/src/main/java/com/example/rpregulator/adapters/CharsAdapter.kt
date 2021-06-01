package com.example.rpregulator.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.rpregulator.databinding.RvItemCharsBinding
import com.example.rpregulator.models.Chars
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.firebase.ui.database.FirebaseRecyclerOptions

class CharsAdapter(
    options: FirebaseRecyclerOptions<Chars>,
    val context: Context,
    val onClickListener: OnClickListener
) : FirebaseRecyclerAdapter<Chars, CharsAdapter.CharHolder>(options) {


    private val _progressBarShow = MutableLiveData<Boolean?>()
    val progressBar: LiveData<Boolean?>
        get() = _progressBarShow


    class CharHolder(private var binding: RvItemCharsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(chars: Chars, context: Context, onClickListener: OnClickListener) {
            binding.txtTitle.text = chars.name
            binding.txtDesc.text = chars.desc
            Glide.with(context).load(chars.img).into(binding.imgChar)
            binding.cardView.setOnClickListener {
                onClickListener.clickListener(chars)
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharHolder {
        return CharHolder(RvItemCharsBinding.inflate(LayoutInflater.from(parent.context)))
    }


    override fun onBindViewHolder(holder: CharHolder, position: Int, model: Chars) {
        val item = getItem(position)
        holder.bind(item, context, onClickListener)

    }

    class OnClickListener(val clickListener: (chars: Chars) -> Unit) {
        fun onClick(chars: Chars) = clickListener(chars)
    }

    override fun onDataChanged() {
        super.onDataChanged()
        _progressBarShow.value = true
    }


}