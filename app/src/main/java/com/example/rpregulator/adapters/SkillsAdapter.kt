package com.example.rpregulator.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.rpregulator.R
import com.example.rpregulator.databinding.RvItemSkillsBinding
import com.example.rpregulator.firebase.UsersFirebase
import com.example.rpregulator.models.Skills
import com.example.rpregulator.utils.AlertDialogBuilders
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.firebase.ui.database.FirebaseRecyclerOptions

class SkillsAdapter(
    options: FirebaseRecyclerOptions<Skills>,
    private val context: Context,
    val user_id: String,
    val onClickListener: OnClickListener
) : FirebaseRecyclerAdapter<Skills, SkillsAdapter.SkillsHolder>(options) {

    private val _progressBarShow = MutableLiveData<Boolean?>()
    val progressBar: LiveData<Boolean?>
        get() = _progressBarShow

    class SkillsHolder(private var binding: RvItemSkillsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(
            skills: Skills,
            onClickListener: OnClickListener,
            user_id: String,
            context: Context
        ) {
            binding.txtName.text = skills.name
            binding.txtCost.text = skills.cost
            binding.txtValue.text = "${skills.value}"
            binding.imgType.setImageResource(
                when (skills.type) {
                    "ACTIVE" -> R.drawable.ic_active
                    "PASSIVE" -> R.drawable.ic_passive
                    else -> R.drawable.ic_active
                }
            )

            if (skills.type == "ACTIVE") {
                binding.txtValue.visibility = View.GONE
                binding.btnDecrease.visibility = View.GONE
                binding.btnIncrease.visibility = View.GONE
                binding.txtCost.visibility = View.VISIBLE

            }else  {
                binding.txtCost.visibility = View.GONE
                binding.txtValue.visibility = View.VISIBLE
                binding.btnDecrease.visibility = View.VISIBLE
                binding.btnIncrease.visibility = View.VISIBLE
            }

            binding.btnIncrease.setOnClickListener {
                increaseValue(skills, user_id)
            }
            binding.btnDecrease.setOnClickListener {
                decreaseValue(skills, user_id)
            }

            binding.cardView.setOnClickListener {
                onClickListener.onClick(skills)
            }
            binding.cardView.setOnLongClickListener {
                AlertDialogBuilders.createDeleteAlert(context) {
                    UsersFirebase.databaseReference
                        .child(user_id)
                        .child("skills")
                        .child(skills.id.toString())
                        .removeValue()
                }
                true
            }
        }

        private fun increaseValue(skills: Skills, user_id: String) {
            var valueOld = skills.value!!.toInt()

            val valueNew = ++valueOld

            UsersFirebase.databaseReference
                .child(user_id)
                .child("skills")
                .child(skills.id.toString())
                .child("value")
                .setValue(valueNew.toString())
        }

        private fun decreaseValue(skills: Skills, user_id: String) {
            var valueOld = skills.value!!.toInt()

            val valueNew = --valueOld

            UsersFirebase.databaseReference
                .child(user_id)
                .child("skills")
                .child(skills.id.toString())
                .child("value")
                .setValue(valueNew.toString())
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SkillsHolder {
        return SkillsHolder(RvItemSkillsBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: SkillsHolder, position: Int, model: Skills) {
        val item = getItem(position)
        holder.bind(item, onClickListener, user_id, context)
    }

    class OnClickListener(val clickListener: (skills: Skills) -> Unit) {
        fun onClick(skills: Skills) = clickListener(skills)
    }

    override fun onDataChanged() {
        super.onDataChanged()
        _progressBarShow.value = true
    }

}