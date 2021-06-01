package com.example.rpregulator.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.rpregulator.databinding.RvItemMainBinding
import com.example.rpregulator.firebase.UsersFirebase
import com.example.rpregulator.models.Inventory
import com.example.rpregulator.utils.AlertDialogBuilders
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.firebase.ui.database.FirebaseRecyclerOptions

class InventoryAdapter(
    options: FirebaseRecyclerOptions<Inventory>,
    private val context: Context,
    val user_id: String,
    val onClickListener: OnClickListener
) : FirebaseRecyclerAdapter<Inventory, InventoryAdapter.InventoryHolder>(options) {

    private val _progressBarShow = MutableLiveData<Boolean?>()
    val progressBar: LiveData<Boolean?>
        get() = _progressBarShow

    class InventoryHolder(private var binding: RvItemMainBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(
            inventory: Inventory,
            onClickListener: OnClickListener,
            user_id: String,
            context: Context
        ) {
            binding.txtName.text = inventory.name
            binding.txtValue.text = "x${inventory.value}"

            binding.btnIncrease.setOnClickListener {
                increaseValue(inventory, user_id)
            }
            binding.btnDecrease.setOnClickListener {
                decreaseValue(inventory, user_id)
            }

            binding.cardView.setOnClickListener {
                onClickListener.onClick(inventory)
            }
            binding.cardView.setOnLongClickListener {
                AlertDialogBuilders.createDeleteAlert(context) {
                    UsersFirebase.databaseReference
                        .child(user_id)
                        .child("inventory")
                        .child(inventory.id.toString())
                        .removeValue()
                }
                true
            }
        }

        private fun increaseValue(inventory: Inventory, user_id: String) {
            var valueOld = inventory.value!!.toInt()

            val valueNew = ++valueOld

            UsersFirebase.databaseReference
                .child(user_id)
                .child("inventory")
                .child(inventory.id.toString())
                .child("value")
                .setValue(valueNew.toString())
        }

        private fun decreaseValue(inventory: Inventory, user_id: String) {
            var valueOld = inventory.value!!.toInt()

            val valueNew = --valueOld

            UsersFirebase.databaseReference
                .child(user_id)
                .child("inventory")
                .child(inventory.id.toString())
                .child("value")
                .setValue(valueNew.toString())
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InventoryHolder {
        return InventoryHolder(RvItemMainBinding.inflate(LayoutInflater.from(parent.context)))
    }


    override fun onBindViewHolder(holder: InventoryHolder, position: Int, model: Inventory) {
        val item = getItem(position)
        holder.bind(item, onClickListener, user_id, context)

    }

    class OnClickListener(val clickListener: (inventory: Inventory) -> Unit) {
        fun onClick(inventory: Inventory) = clickListener(inventory)
    }


    override fun onDataChanged() {
        super.onDataChanged()
        _progressBarShow.value = true
    }


}