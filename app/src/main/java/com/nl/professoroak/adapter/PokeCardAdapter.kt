package com.nl.professoroak.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.nl.professoroak.databinding.ItemPokeCardBinding
import com.nl.professoroak.model.CardEntity
import com.nl.professoroak.model.Data
import com.nl.professoroak.util.loadWithGlide
import com.nl.professoroak.view.CardsFragment

class PokeCardAdapter(
    val pokeCardList: MutableList<Data> = mutableListOf()
) : RecyclerView.Adapter<PokeCardAdapter.PokeCardViewHolder>() {

    private val cardsFragment by lazy { CardsFragment() }


    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ) = PokeCardViewHolder.getInstance(parent)

    override fun onBindViewHolder(holder: PokeCardViewHolder, position: Int) {
        holder.loadCardImage(pokeCardList[position])
        holder.listen(pokeCardList[position])
        holder.addBtn.isVisible = pokeCardList.size > 0
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

        val addBtn by lazy { this.binding.addBtn }

        fun loadCardImage(data: Data) = with(binding) {
            ivPokeCard.loadWithGlide(data.images?.small)
        }

        fun listen(cardInstance: Data) = with(binding) {
            addBtn.setOnClickListener {
                val cardEntityInstance = CardEntity(
                    cardInstance.hp,
                    cardInstance.id!!,
                    cardInstance.images,
                    cardInstance.name,
                    cardInstance.nationalPokedexNumbers,
                    cardInstance.number,
                    cardInstance.rarity,
                    cardInstance.weaknesses
                )
                PokeCardAdapter().cardsFragment.addToCollection(cardEntityInstance)
            }
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