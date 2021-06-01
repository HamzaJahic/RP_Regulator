package com.example.rpregulator.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.rpregulator.databinding.RvItemMainBinding
import com.example.rpregulator.firebase.UsersFirebase
import com.example.rpregulator.models.CursesBlessingsHealth
import com.example.rpregulator.utils.AlertDialogBuilders
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.firebase.ui.database.FirebaseRecyclerOptions

class CursesAdapter(
    options: FirebaseRecyclerOptions<CursesBlessingsHealth>,
    val user_id: String, val context: Context, val onClickListener: OnClickListener,
) : FirebaseRecyclerAdapter<CursesBlessingsHealth, CursesAdapter.StatusHolder>(options) {

    private val _progressBarShow = MutableLiveData<Boolean?>()
    val progressBar: LiveData<Boolean?>
        get() = _progressBarShow

    class StatusHolder(private var binding: RvItemMainBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(
            cursesBlessingsHealth: CursesBlessingsHealth,
            onClickListener: OnClickListener,
            user_id: String,
            context: Context
        ) {
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
            binding.cardView.isLongClickable = true

            binding.cardView.setOnLongClickListener {
                AlertDialogBuilders.createDeleteAlert(context) {
                    UsersFirebase.databaseReference
                        .child(user_id)
                        .child("curses")
                        .child(cursesBlessingsHealth.id.toString())
                        .removeValue()
                }
                true
            }
        }

        private fun increaseValue(cursesBlessingsHealth: CursesBlessingsHealth, user_id: String) {
            var valueOld = cursesBlessingsHealth.value!!.toInt()

            val valueNew = ++valueOld

            UsersFirebase.databaseReference
                .child(user_id)
                .child("curses")
                .child(cursesBlessingsHealth.id.toString())
                .child("value")
                .setValue(valueNew.toString())
        }

        private fun decreaseValue(cursesBlessingsHealth: CursesBlessingsHealth, user_id: String) {
            var valueOld = cursesBlessingsHealth.value!!.toInt()

            val valueNew = --valueOld

            UsersFirebase.databaseReference
                .child(user_id)
                .child("curses")
                .child(cursesBlessingsHealth.id.toString())
                .child("value")
                .setValue(valueNew.toString())
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StatusHolder {
        return StatusHolder(RvItemMainBinding.inflate(LayoutInflater.from(parent.context)))
    }


    override fun onBindViewHolder(
        holder: StatusHolder,
        position: Int,
        model: CursesBlessingsHealth
    ) {
        val item = getItem(position)
        holder.bind(item, onClickListener, user_id, context)

    }

    class OnClickListener(val clickListener: (cursesBlessingsHealth: CursesBlessingsHealth) -> Unit) {
        fun onClick(cursesBlessingsHealth: CursesBlessingsHealth) =
            clickListener(cursesBlessingsHealth)
    }


    override fun onDataChanged() {
        super.onDataChanged()
        _progressBarShow.value = true
    }


}