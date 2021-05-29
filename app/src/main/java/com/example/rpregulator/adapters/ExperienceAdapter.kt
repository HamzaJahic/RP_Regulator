package com.example.rpregulator.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.rpregulator.databinding.RvItemMainBinding
import com.example.rpregulator.firebase.UsersFirebase
import com.example.rpregulator.models.Stats
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.firebase.ui.database.FirebaseRecyclerOptions

class ExperienceAdapter(private val options: FirebaseRecyclerOptions<Stats>, val onClickListener: OnClickListener, private val id : String)
    : FirebaseRecyclerAdapter<Stats, ExperienceAdapter.StatsHolder>(options){


    class StatsHolder(private var binding: RvItemMainBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(stats: Stats, id: String){
            binding.txtName.text = stats.name
            binding.txtValue.text = "${stats.value}%"
            binding.btnIncrease.setOnClickListener {
                increaseValue(stats,id)
            }
            binding.btnDecrease.setOnClickListener {
                decreaseValue(stats,id)
            }
        }


        fun increaseValue(stats: Stats, id: String){
            var valueOld = stats.value!!.toInt()

            valueOld = if (valueOld == 95) 0 else valueOld+5

            UsersFirebase.databaseReference.child(id).child("experience").child(stats.id.toString()).child("value").setValue(valueOld.toString())
        }


        fun decreaseValue(stats: Stats, id: String){
            var valueOld = stats.value!!.toInt()

            valueOld -= 5

            UsersFirebase.databaseReference.child(id).child("experience").child(stats.id.toString()).child("value").setValue(valueOld.toString())
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StatsHolder {
        return StatsHolder(RvItemMainBinding.inflate(LayoutInflater.from(parent.context)))
    }


    override fun onBindViewHolder(holder: StatsHolder, position: Int, model: Stats) {
        val item = getItem(position)
        val id = id
        holder.bind(item, id)
        holder.itemView.setOnClickListener{
            onClickListener.onClick(item)
        }
    }

    class OnClickListener(val clickListener: (stats: Stats) ->Unit){
        fun onClick(stats: Stats) = clickListener(stats)
    }





}