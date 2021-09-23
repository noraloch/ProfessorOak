package com.nl.professoroak.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nl.professoroak.databinding.ItemPokeCardBinding
import com.nl.professoroak.model.PokeCard
import com.nl.professoroak.util.loadWithGlide

class PokeCardAdapter(
    private val pokeCardList: MutableList<PokeCard> = mutableListOf()
) : RecyclerView.Adapter<PokeCardAdapter.PokeCardViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ) = PokeCardViewHolder.getInstance(parent)

    class PokeCardViewHolder(
        private val binding: ItemPokeCardBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun loadCardImage(pokeCard: PokeCard) = with(binding) {
            ivPokeCard.loadWithGlide(pokeCard.data.images.small)
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