package com.example.rpregulator.adapters

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.rpregulator.databinding.RvItemMainBinding
import com.example.rpregulator.firebase.UsersFirebase
import com.example.rpregulator.models.Stats
import com.example.rpregulator.utils.AlertDialogBuilders
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.firebase.ui.database.FirebaseRecyclerOptions

class StatsAdapter(
    options: FirebaseRecyclerOptions<Stats>,
    private val context: Context,
    val user_id: String,
    val onClickListener: OnClickListener
) : FirebaseRecyclerAdapter<Stats, StatsAdapter.StatsHolder>(options) {

    private val _progressBarShow = MutableLiveData<Boolean?>()
    val progressBar: LiveData<Boolean?>
        get() = _progressBarShow

    class StatsHolder(private var binding: RvItemMainBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(stats: Stats, user_id: String, context: Context) {
            binding.txtName.text = stats.name
            binding.txtValue.text = "${stats.value}"
            binding.textBackground.setBackgroundColor(
                when (stats.name) {
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

                }
            )

            binding.btnIncrease.setOnClickListener {
                increaseValue(stats, user_id)
            }
            binding.btnDecrease.setOnClickListener {
                decreaseValue(stats, user_id)
            }
            binding.cardView.setOnLongClickListener {
                AlertDialogBuilders.createDeleteAlert(context) {
                    UsersFirebase.databaseReference
                        .child(user_id)
                        .child("stats")
                        .child(stats.id.toString())
                        .removeValue()
                }
                true
            }
        }

        private fun increaseValue(stats: Stats, user_id: String) {
            var valueOld = stats.value!!.toInt()

            val valueNew = if (stats.name == "Stamina") valueOld + 10 else ++valueOld

            UsersFirebase.databaseReference
                .child(user_id)
                .child("stats")
                .child(stats.id.toString())
                .child("value")
                .setValue(valueNew.toString())
        }

        private fun decreaseValue(stats: Stats, user_id: String) {
            var valueOld = stats.value!!.toInt()

            val valueNew = if (stats.name == "Stamina") valueOld - 10 else --valueOld

            UsersFirebase.databaseReference
                .child(user_id)
                .child("stats")
                .child(stats.id.toString())
                .child("value")
                .setValue(valueNew.toString())
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StatsHolder {
        return StatsHolder(RvItemMainBinding.inflate(LayoutInflater.from(parent.context)))
    }


    override fun onBindViewHolder(holder: StatsHolder, position: Int, model: Stats) {
        val item = getItem(position)
        holder.bind(item, user_id, context)
        holder.itemView.setOnClickListener {
            onClickListener.onClick(item)
        }
    }

    class OnClickListener(val clickListener: (stats: Stats) -> Unit) {
        fun onClick(stats: Stats) = clickListener(stats)
    }


    override fun onDataChanged() {
        super.onDataChanged()
        _progressBarShow.value = true
    }


}