package com.nl.professoroak.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nl.professoroak.databinding.ItemPokeCardBinding
import com.nl.professoroak.model.Data
import com.nl.professoroak.util.loadWithGlide

class PokeCardAdapter(
    private val pokeCardList: MutableList<Data> = mutableListOf()
) : RecyclerView.Adapter<PokeCardAdapter.PokeCardViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ) = PokeCardViewHolder.getInstance(parent)

    override fun onBindViewHolder(holder: PokeCardViewHolder, position: Int) {
        holder.loadCardImage(pokeCardList[position])
    }

    override fun getItemCount() = pokeCardList.size

    fun updateList(pokeCards: List<Data>) {
        val positionStart = pokeCardList.size
        pokeCardList.addAll(pokeCards)
        notifyItemRangeInserted(positionStart, pokeCards.size)
    }

    fun clear() {
        val listSize = pokeCardList.size
        pokeCardList.clear()
        notifyItemRangeRemoved(0, listSize)
    }

    class PokeCardViewHolder(
        private val binding: ItemPokeCardBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun loadCardImage(data: Data) = with(binding) {
            ivPokeCard.loadWithGlide(data.images?.small)
        }

        companion object {
            fun getInstance(parent: ViewGroup): PokeCardViewHolder {
                val binding = ItemPokeCardBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
                return PokeCardViewHolder(binding)
            }
        }

    }
}