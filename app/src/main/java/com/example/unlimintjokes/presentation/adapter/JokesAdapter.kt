package com.example.unlimintjokes.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.unlimintjokes.databinding.ItemJokesRvBinding
import com.example.unlimintjokes.domain.model.JokeModel

class JokesAdapter(
    private var dataSet: List<JokeModel> = emptyList()
) : RecyclerView.Adapter<JokesAdapter.ViewHolder>() {

    class ViewHolder(
        val binding: ItemJokesRvBinding
    ) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemJokesRvBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.tvJoke.text = dataSet[position].joke
    }

    override fun getItemCount(): Int = dataSet.size
}
