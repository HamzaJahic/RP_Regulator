package com.example.rpregulator.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.rpregulator.R
import com.example.rpregulator.databinding.RvItemSkillsBinding
import com.example.rpregulator.firebase.SkillsFirebase
import com.example.rpregulator.models.Skills
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.firebase.ui.database.FirebaseRecyclerOptions

class SkillsAdapter(private val options: FirebaseRecyclerOptions<Skills>, val onClickListener: SkillsAdapter.OnClickListener)
    : FirebaseRecyclerAdapter<Skills, SkillsAdapter.SkillsHolder>(options){

    class SkillsHolder(private var binding: RvItemSkillsBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(skills: Skills, onClickListener: OnClickListener){
            binding.txtName.text = skills.name
            binding.txtCost.text = skills.cost
            binding.txtValue.text = "${skills.value}"
            binding.imgType.setImageResource(when(skills.type){
                "ACTIVE" -> R.drawable.ic_active
                "PASSIVE" -> R.drawable.ic_passive
                else -> R.drawable.ic_active
            })

            if(skills.type=="ACTIVE"){
                binding.txtValue.visibility = View.GONE
                binding.btnDecrease.visibility = View.GONE
                binding.btnIncrease.visibility = View.GONE
            }else binding.txtCost.visibility = View.GONE

            binding.btnIncrease.setOnClickListener {
                increaseValue(skills)
            }
            binding.btnDecrease.setOnClickListener {
                decreaseValue(skills)
            }

            binding.cardView.setOnClickListener{
                onClickListener.onClick(skills)
            }


        }

        fun increaseValue(skills: Skills){
            var valueOld = skills.value!!.toInt()

            var valueNew = ++valueOld

            SkillsFirebase.databaseReference.child(skills.id.toString()).child("value").setValue(valueNew.toString())
        }

        fun decreaseValue(skills: Skills){
            var valueOld = skills.value!!.toInt()

            var valueNew = --valueOld

           SkillsFirebase.databaseReference.child(skills.id.toString()).child("value").setValue(valueNew.toString())
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SkillsAdapter.SkillsHolder {
        return SkillsAdapter.SkillsHolder(RvItemSkillsBinding.inflate(LayoutInflater.from(parent.context)))
    }


    override fun onBindViewHolder(holder: SkillsAdapter.SkillsHolder, position: Int, model: Skills) {
        val item = getItem(position)
        holder.bind(item, onClickListener)

    }

    class OnClickListener(val clickListener: (skills: Skills) ->Unit){
        fun onClick(skills: Skills) = clickListener(skills)
    }





}