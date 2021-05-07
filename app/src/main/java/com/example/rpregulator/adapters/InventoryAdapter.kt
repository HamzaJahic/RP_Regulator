package com.example.rpregulator.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.rpregulator.databinding.RvItemMainBinding
import com.example.rpregulator.firebase.InventoryFirebase
import com.example.rpregulator.models.Inventory
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.firebase.ui.database.FirebaseRecyclerOptions

class InventoryAdapter(private val options: FirebaseRecyclerOptions<Inventory>, val onClickListener: InventoryAdapter.OnClickListener)
    : FirebaseRecyclerAdapter<Inventory, InventoryAdapter.InventoryHolder>(options){

    class InventoryHolder(private var binding: RvItemMainBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(inventory: Inventory, onClickListener: OnClickListener){
            binding.txtName.text = inventory.name
            binding.txtValue.text = "x${inventory.value}"

            binding.btnIncrease.setOnClickListener {
                increaseValue(inventory)
            }
            binding.btnDecrease.setOnClickListener {
                decreaseValue(inventory)
            }

            binding.cardView.setOnClickListener {
                onClickListener.onClick(inventory)
            }
        }

        fun increaseValue(inventory: Inventory){
            var valueOld = inventory.value!!.toInt()

            var valueNew = ++valueOld

            InventoryFirebase.databaseReference.child(inventory.id.toString()).child("value").setValue(valueNew.toString())
        }

        fun decreaseValue(inventory: Inventory){
            var valueOld = inventory.value!!.toInt()

            var valueNew = --valueOld

            InventoryFirebase.databaseReference.child(inventory.id.toString()).child("value").setValue(valueNew.toString())
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InventoryAdapter.InventoryHolder {
        return InventoryHolder(RvItemMainBinding.inflate(LayoutInflater.from(parent.context)))
    }


    override fun onBindViewHolder(holder: InventoryAdapter.InventoryHolder, position: Int, model: Inventory) {
        val item = getItem(position)
        holder.bind(item, onClickListener)

    }

    class OnClickListener(val clickListener: (inventory: Inventory) ->Unit){
        fun onClick(inventory: Inventory) = clickListener(inventory)
    }





}