package com.example.rpregulator.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.rpregulator.databinding.RvItemMainBinding
import com.example.rpregulator.firebase.UsersFirebase
import com.example.rpregulator.models.CursesBlessingsHealth
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.firebase.ui.database.FirebaseRecyclerOptions

class CursesAdapter(private val options: FirebaseRecyclerOptions<CursesBlessingsHealth>,val user_id: String, val onClickListener: CursesAdapter.OnClickListener)
    : FirebaseRecyclerAdapter<CursesBlessingsHealth, CursesAdapter.StatusHolder>(options){

    class StatusHolder(private var binding: RvItemMainBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(cursesBlessingsHealth: CursesBlessingsHealth, onClickListener: OnClickListener, user_id: String){
            binding.txtName.text = cursesBlessingsHealth.name
            binding.txtValue.text = "${cursesBlessingsHealth.value}"

            binding.btnIncrease.setOnClickListener {
                increaseValue(cursesBlessingsHealth, user_id)
            }
            binding.btnDecrease.setOnClickListener {
                decreaseValue(cursesBlessingsHealth, user_id)
            }
            binding.cardView.setOnClickListener {
                onClickListener.clickListener(cursesBlessingsHealth)
            }
        }

        fun increaseValue(cursesBlessingsHealth: CursesBlessingsHealth, user_id: String){
            var valueOld = cursesBlessingsHealth.value!!.toInt()

            var valueNew = ++valueOld

            UsersFirebase.databaseReference
                    .child(user_id)
                    .child("curses")
                    .child(cursesBlessingsHealth.id.toString())
                    .child("value")
                    .setValue(valueNew.toString())        }

        fun decreaseValue(cursesBlessingsHealth: CursesBlessingsHealth, user_id: String){
            var valueOld = cursesBlessingsHealth.value!!.toInt()

            var valueNew = --valueOld

            UsersFirebase.databaseReference
                    .child(user_id)
                    .child("curses")
                    .child(cursesBlessingsHealth.id.toString())
                    .child("value")
                    .setValue(valueNew.toString())        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CursesAdapter.StatusHolder {
        return CursesAdapter.StatusHolder(RvItemMainBinding.inflate(LayoutInflater.from(parent.context)))
    }


    override fun onBindViewHolder(holder: CursesAdapter.StatusHolder, position: Int, model:CursesBlessingsHealth) {
        val item = getItem(position)
        holder.bind(item, onClickListener, user_id)

    }

    class OnClickListener(val clickListener: (cursesBlessingsHealth: CursesBlessingsHealth) ->Unit){
        fun onClick(cursesBlessingsHealth: CursesBlessingsHealth) = clickListener(cursesBlessingsHealth)
    }





}