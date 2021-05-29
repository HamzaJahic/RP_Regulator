package com.example.rpregulator.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.rpregulator.databinding.RvItemMainBinding
import com.example.rpregulator.firebase.UsersFirebase
import com.example.rpregulator.models.CursesBlessingsHealth
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.firebase.ui.database.FirebaseRecyclerOptions

class StatusAdapter(options: FirebaseRecyclerOptions<CursesBlessingsHealth>, val user_id: String, val onClickListener: OnClickListener)
    : FirebaseRecyclerAdapter<CursesBlessingsHealth, StatusAdapter.StatusHolder>(options){

    class StatusHolder(private var binding: RvItemMainBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(cursesBlessingsHealth: CursesBlessingsHealth, user_id: String){
            binding.txtName.text = cursesBlessingsHealth.name
            binding.txtValue.text = "${cursesBlessingsHealth.value}"

            binding.btnIncrease.setOnClickListener {
                increaseValue(cursesBlessingsHealth, user_id)
            }
            binding.btnDecrease.setOnClickListener {
                decreaseValue(cursesBlessingsHealth, user_id)
            }
        }

        fun increaseValue(cursesBlessingsHealth: CursesBlessingsHealth, user_id: String){
            var valueOld = cursesBlessingsHealth.value!!.toInt()

            val valueNew = ++valueOld

            UsersFirebase.databaseReference
                    .child(user_id)
                    .child("health")
                    .child(cursesBlessingsHealth.id.toString())
                    .child("value")
                    .setValue(valueNew.toString())         }

        fun decreaseValue(cursesBlessingsHealth: CursesBlessingsHealth, user_id: String){
            var valueOld = cursesBlessingsHealth.value!!.toInt()

            val valueNew = --valueOld

            UsersFirebase.databaseReference
                    .child(user_id)
                    .child("health")
                    .child(cursesBlessingsHealth.id.toString())
                    .child("value")
                    .setValue(valueNew.toString())         }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StatusHolder {
        return StatusHolder(RvItemMainBinding.inflate(LayoutInflater.from(parent.context)))
    }


    override fun onBindViewHolder(holder: StatusHolder, position: Int, model:CursesBlessingsHealth) {
        val item = getItem(position)
        holder.bind(item, user_id)
        holder.itemView.setOnClickListener{
            onClickListener.onClick(item)
        }
    }

    class OnClickListener(val clickListener: (cursesBlessingsHealth: CursesBlessingsHealth) ->Unit){
        fun onClick(cursesBlessingsHealth: CursesBlessingsHealth) = clickListener(cursesBlessingsHealth)
    }





}