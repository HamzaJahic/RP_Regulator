package com.example.rpregulator.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.rpregulator.R
import com.example.rpregulator.databinding.RvItemSkillsBinding
import com.example.rpregulator.firebase.UsersFirebase
import com.example.rpregulator.models.Skills
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.firebase.ui.database.FirebaseRecyclerOptions

class SkillsAdapter(private val options: FirebaseRecyclerOptions<Skills>,val user_id: String, val onClickListener: SkillsAdapter.OnClickListener)
    : FirebaseRecyclerAdapter<Skills, SkillsAdapter.SkillsHolder>(options){

    class SkillsHolder(private var binding: RvItemSkillsBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(skills: Skills, onClickListener: OnClickListener, user_id: String){
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
                increaseValue(skills, user_id)
            }
            binding.btnDecrease.setOnClickListener {
                decreaseValue(skills, user_id)
            }

            binding.cardView.setOnClickListener{
                onClickListener.onClick(skills)
            }


        }

        fun increaseValue(skills: Skills, user_id: String){
            var valueOld = skills.value!!.toInt()

            var valueNew = ++valueOld

            UsersFirebase.databaseReference
                    .child(user_id)
                    .child("skills")
                    .child(skills.id.toString())
                    .child("value")
                    .setValue(valueNew.toString())        }

        fun decreaseValue(skills: Skills, user_id: String){
            var valueOld = skills.value!!.toInt()

            var valueNew = --valueOld

            UsersFirebase.databaseReference
                    .child(user_id)
                    .child("skills")
                    .child(skills.id.toString())
                    .child("value")
                    .setValue(valueNew.toString())        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SkillsAdapter.SkillsHolder {
        return SkillsAdapter.SkillsHolder(RvItemSkillsBinding.inflate(LayoutInflater.from(parent.context)))
    }


    override fun onBindViewHolder(holder: SkillsAdapter.SkillsHolder, position: Int, model: Skills) {
        val item = getItem(position)
        holder.bind(item, onClickListener, user_id)

    }

    class OnClickListener(val clickListener: (skills: Skills) ->Unit){
        fun onClick(skills: Skills) = clickListener(skills)
    }





}