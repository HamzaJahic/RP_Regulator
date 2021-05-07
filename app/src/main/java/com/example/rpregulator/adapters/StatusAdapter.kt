package com.example.rpregulator.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.rpregulator.databinding.RvItemMainBinding
import com.example.rpregulator.firebase.StatsFirebase
import com.example.rpregulator.models.CursesBlessingsHealth
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.firebase.ui.database.FirebaseRecyclerOptions

class StatusAdapter(private val options: FirebaseRecyclerOptions<CursesBlessingsHealth>, val onClickListener: StatusAdapter.OnClickListener)
    : FirebaseRecyclerAdapter<CursesBlessingsHealth, StatusAdapter.StatusHolder>(options){

    class StatusHolder(private var binding: RvItemMainBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(cursesBlessingsHealth: CursesBlessingsHealth){
            binding.txtName.text = cursesBlessingsHealth.name
            binding.txtValue.text = "${cursesBlessingsHealth.value}"

            binding.btnIncrease.setOnClickListener {
                increaseValue(cursesBlessingsHealth)
            }
            binding.btnDecrease.setOnClickListener {
                decreaseValue(cursesBlessingsHealth)
            }
        }

        fun increaseValue(cursesBlessingsHealth: CursesBlessingsHealth){
            var valueOld = cursesBlessingsHealth.value!!.toInt()

            var valueNew = ++valueOld

            StatsFirebase.databaseReference.child(cursesBlessingsHealth.id.toString()).child("value").setValue(valueNew.toString())
        }

        fun decreaseValue(cursesBlessingsHealth: CursesBlessingsHealth){
            var valueOld = cursesBlessingsHealth.value!!.toInt()

            var valueNew = --valueOld

            StatsFirebase.databaseReference.child(cursesBlessingsHealth.id.toString()).child("value").setValue(valueNew.toString())
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StatusAdapter.StatusHolder {
        return StatusAdapter.StatusHolder(RvItemMainBinding.inflate(LayoutInflater.from(parent.context)))
    }


    override fun onBindViewHolder(holder: StatusAdapter.StatusHolder, position: Int, model:CursesBlessingsHealth) {
        val item = getItem(position)
        holder.bind(item)
        holder.itemView.setOnClickListener{
            onClickListener.onClick(item)
        }
    }

    class OnClickListener(val clickListener: (cursesBlessingsHealth: CursesBlessingsHealth) ->Unit){
        fun onClick(cursesBlessingsHealth: CursesBlessingsHealth) = clickListener(cursesBlessingsHealth)
    }





}