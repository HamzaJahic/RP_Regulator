package com.example.rpregulator.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.rpregulator.databinding.CharSelectRvItemBinding
import com.example.rpregulator.models.User
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.firebase.ui.database.FirebaseRecyclerOptions

class CharSelectAdapter(
    options: FirebaseRecyclerOptions<User>,
    val context: Context, val onClickListener: OnClickListener
) : FirebaseRecyclerAdapter<User, CharSelectAdapter.UserHolder>(options) {

    private val _progressBarShow = MutableLiveData<Boolean?>()
    val progressBar: LiveData<Boolean?>
        get() = _progressBarShow

    class UserHolder(private var binding: CharSelectRvItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(user: User, context: Context) {
            Glide.with(context).load(user.img).into(binding.imgUser)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserHolder {
        return UserHolder(
            CharSelectRvItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: UserHolder, position: Int, model: User) {
        val item = getItem(position)
        holder.bind(item, context)
        holder.itemView.setOnClickListener {
            onClickListener.onClick(item)
        }

    }

    override fun onDataChanged() {
        super.onDataChanged()
        _progressBarShow.value = true
    }

    class OnClickListener(val clickListener: (user: User) -> Unit) {
        fun onClick(user: User) = clickListener(user)
    }

}
