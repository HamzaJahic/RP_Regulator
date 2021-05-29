package com.example.rpregulator.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.rpregulator.databinding.RvItemMainBinding
import com.example.rpregulator.firebase.UsersFirebase
import com.example.rpregulator.models.Inventory
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.firebase.ui.database.FirebaseRecyclerOptions

class InventoryAdapter(private val options: FirebaseRecyclerOptions<Inventory>,val user_id: String, val onClickListener: InventoryAdapter.OnClickListener)
    : FirebaseRecyclerAdapter<Inventory, InventoryAdapter.InventoryHolder>(options){

    class InventoryHolder(private var binding: RvItemMainBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(inventory: Inventory, onClickListener: OnClickListener, user_id: String){
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
        }

        fun increaseValue(inventory: Inventory, user_id: String){
            var valueOld = inventory.value!!.toInt()

            var valueNew = ++valueOld

            UsersFirebase.databaseReference
                    .child(user_id)
                    .child("inventory")
                    .child(inventory.id.toString())
                    .child("value")
                    .setValue(valueNew.toString())        }

        fun decreaseValue(inventory: Inventory, user_id: String){
            var valueOld = inventory.value!!.toInt()

            var valueNew = --valueOld

            UsersFirebase.databaseReference
                    .child(user_id)
                    .child("inventory")
                    .child(inventory.id.toString())
                    .child("value")
                    .setValue(valueNew.toString())        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InventoryAdapter.InventoryHolder {
        return InventoryHolder(RvItemMainBinding.inflate(LayoutInflater.from(parent.context)))
    }


    override fun onBindViewHolder(holder: InventoryAdapter.InventoryHolder, position: Int, model: Inventory) {
        val item = getItem(position)
        holder.bind(item, onClickListener, user_id)

    }

    class OnClickListener(val clickListener: (inventory: Inventory) ->Unit){
        fun onClick(inventory: Inventory) = clickListener(inventory)
    }





}