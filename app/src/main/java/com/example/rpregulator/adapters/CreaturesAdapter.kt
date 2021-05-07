package com.example.rpregulator.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.rpregulator.databinding.RvItemCharsBinding
import com.example.rpregulator.models.Creatures
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.firebase.ui.database.FirebaseRecyclerOptions

class CreaturesAdapter(private val options: FirebaseRecyclerOptions<Creatures>, context: Context, val onClickListener: CreaturesAdapter.OnClickListener)
    : FirebaseRecyclerAdapter<Creatures, CreaturesAdapter.CreaturesHolder>(options){



    val context = context
    val _progressBarShow = MutableLiveData<Boolean?>()
    val progressBar: LiveData<Boolean?>
        get() = _progressBarShow


    class CreaturesHolder(private var binding: RvItemCharsBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(creatures: Creatures,context: Context,onClickListener: OnClickListener) {
            binding.txtTitle.text = creatures.name
            binding.txtDesc.text = creatures.desc
            Glide.with(context).load(creatures.img).into(binding.imgChar)
            binding.cardView.setOnClickListener {
                onClickListener.clickListener(creatures)
            }

        }
    }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CreaturesAdapter.CreaturesHolder {
            return CreaturesAdapter.CreaturesHolder(RvItemCharsBinding.inflate(LayoutInflater.from(parent.context)))
        }


        override fun onBindViewHolder(holder: CreaturesAdapter.CreaturesHolder, position: Int, model:Creatures) {
            val item = getItem(position)
            holder.bind(item, context,onClickListener)

        }

        class OnClickListener(val clickListener: (creatures:Creatures) ->Unit){
            fun onClick(creatures: Creatures) = clickListener(creatures)
        }

    override fun onDataChanged() {
        super.onDataChanged()
        _progressBarShow.value = true
    }


}