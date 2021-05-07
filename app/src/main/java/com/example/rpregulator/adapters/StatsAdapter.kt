package com.example.rpregulator.adapters

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.rpregulator.databinding.RvItemMainBinding
import com.example.rpregulator.firebase.StatsFirebase
import com.example.rpregulator.models.Stats
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.firebase.ui.database.FirebaseRecyclerOptions

class StatsAdapter(private val options: FirebaseRecyclerOptions<Stats>, val onClickListener: StatsAdapter.OnClickListener)
    : FirebaseRecyclerAdapter<Stats, StatsAdapter.StatsHolder>(options){

    class StatsHolder(private var binding: RvItemMainBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(stats: Stats){
            binding.txtName.text = stats.name
            binding.txtValue.text = "${stats.value}"
            binding.textBackground.setBackgroundColor( when(stats.name){
                "Health Points" -> Color.parseColor("#a33636")
                "Mana Points" -> Color.parseColor("#368ba3")
                "Magum" -> Color.parseColor("#36a39a")
                "Stamina" -> Color.parseColor("#57a336")
                "Strength" -> Color.parseColor("#c48c2b")
                "Dexterity" -> Color.parseColor("#55a832")
                "Defense" -> Color.parseColor("#86769c")
                "Magic Resist" -> Color.parseColor("#6b33b5")
                "Speed" -> Color.parseColor("#b5b333")
                else -> Color.parseColor("#55a832")

            })

            binding.btnIncrease.setOnClickListener {
                increaseValue(stats)
            }
            binding.btnDecrease.setOnClickListener {
                decreaseValue(stats)
            }
        }

        fun increaseValue(stats: Stats){
            var valueOld = stats.value!!.toInt()

            var valueNew = if(stats.name == "Stamina") valueOld+10 else ++valueOld

            StatsFirebase.databaseReference.child(stats.id.toString()).child("value").setValue(valueNew.toString())
        }

        fun decreaseValue(stats: Stats){
            var valueOld = stats.value!!.toInt()

            var valueNew =if(stats.name == "Stamina") valueOld-10 else --valueOld

            StatsFirebase.databaseReference.child(stats.id.toString()).child("value").setValue(valueNew.toString())
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StatsAdapter.StatsHolder {
        return StatsHolder(RvItemMainBinding.inflate(LayoutInflater.from(parent.context)))
    }


    override fun onBindViewHolder(holder: StatsAdapter.StatsHolder, position: Int, model: Stats) {
        val item = getItem(position)
        holder.bind(item)
        holder.itemView.setOnClickListener{
            onClickListener.onClick(item)
        }
    }

    class OnClickListener(val clickListener: (stats: Stats) ->Unit){
        fun onClick(stats: Stats) = clickListener(stats)
    }





}