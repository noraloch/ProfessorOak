package com.nl.professoroak.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.nl.professoroak.R
import com.nl.professoroak.databinding.FragmentCardsBinding
import com.nl.professoroak.databinding.ItemPokeCardBinding
import com.nl.professoroak.model.CardEntity
import com.nl.professoroak.model.Data
import com.nl.professoroak.repo.local.CollectionDatabase
import com.nl.professoroak.util.loadWithGlide
import com.nl.professoroak.view.CardsFragment
import com.nl.professoroak.viewmodel.PokeViewModel
import kotlinx.coroutines.coroutineScope

class PokeCardAdapter(
    val pokeCardList: MutableList<Data> = mutableListOf()
) : RecyclerView.Adapter<PokeCardAdapter.PokeCardViewHolder>() {

    private val cardsFragment by lazy { CardsFragment() }

    val TAG = "PokeCardAdapter"

//    private lateinit var database: CollectionDatabase
//    fun addToCollection(cardEntity: CardEntity) {
//        CollectionDatabase.getInstance().collectionDao().insertCard(cardEntity)
//        Log.d(TAG, "addToCollection: $cardEntity")
////        Toast.makeText(CardsFragment().context, "done", Toast.LENGTH_LONG).show()
//    }

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ) = PokeCardViewHolder.getInstance(parent)

    override fun onBindViewHolder(holder: PokeCardViewHolder, position: Int) {
        holder.loadCardImage(pokeCardList[position])
        holder.listen(pokeCardList[position])
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
//        val addBtn: FloatingActionButton = itemView.findViewById(R.id.add_btn)

//        init {
//            addBtn.isVisible = PokeCardAdapter().pokeCardList.size > 0
//            addBtn.setOnClickListener {
//                if (PokeCardAdapter().pokeCardList.size > 0) {
//                    val cardInstance = PokeCardAdapter().pokeCardList[adapterPosition]
//                    val cardEntityInstance = CardEntity(
//                        cardInstance.hp,
//                        cardInstance.id!!,
//                        cardInstance.images,
//                        cardInstance.name,
//                        cardInstance.nationalPokedexNumbers,
//                        cardInstance.number,
//                        cardInstance.rarity,
//                        cardInstance.weaknesses
//                    )
//                    PokeCardAdapter().addToCollection(cardEntityInstance)
//                }
//            }
//        }


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