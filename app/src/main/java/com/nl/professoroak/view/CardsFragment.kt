package com.nl.professoroak.view

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.nl.professoroak.adapter.PokeCardAdapter
import com.nl.professoroak.databinding.FragmentCardsBinding
import com.nl.professoroak.model.CardEntity
import com.nl.professoroak.model.Data
import com.nl.professoroak.model.request.Queries
import com.nl.professoroak.repo.local.CollectionDatabase
import com.nl.professoroak.util.ApiState
import com.nl.professoroak.util.UserPrefManager
import com.nl.professoroak.viewmodel.PokeViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlin.math.log


class CardsFragment : Fragment() {

    private var _binding: FragmentCardsBinding? = null
    private val binding get() = _binding!!
    private val pokeViewModel by activityViewModels<PokeViewModel>()
    private val pokeCardAdapter by lazy { PokeCardAdapter() }
    private lateinit var database: CollectionDatabase


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentCardsBinding.inflate(layoutInflater, container, false).also {
        _binding = it
    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // first we if we are not just getting a brand new call
        if (pokeViewModel.queries == null) viewLifecycleOwner.lifecycleScope.launch {
                UserPrefManager.getInstance(view.context).queries.collect {
                pokeViewModel.getImages(Queries(it?.q))
                    Log.d(TAG, "onViewCreated 1: ${Queries(it?.q)}")
            }
        }
        setupObservers()
    }

    fun addToCollection(cardEntity: CardEntity) {
        context?.let { CollectionDatabase.getInstance(it).collectionDao().insertCard(cardEntity) }
        Log.d(TAG, "addToCollection: $cardEntity")
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupObservers() = with(pokeViewModel) {
        pokeCardsState.observe(viewLifecycleOwner) { state ->
            binding.viewLoading.isVisible = state is ApiState.Loading
            if (state is ApiState.Success) loadCardImages(state.data.data)
            if (state is ApiState.Failure) handleFailure(state.errorMsg)
        }
    }

    private fun loadCardImages(data: List<Data>) = with(binding.rvList) {
        if (data.isEmpty()) {
            val dialogBuilder = AlertDialog.Builder(requireActivity())
            dialogBuilder.setMessage("Your search did not match any of our valid names! Try again!")
            dialogBuilder.show()
        } else {
            if (adapter == null) adapter = pokeCardAdapter
            pokeCardAdapter.clear()
            pokeCardAdapter.updateList(data)
        }
    }


    private fun handleFailure(errorMsg: String) {
        val dialogBuilder = AlertDialog.Builder(requireActivity())
        dialogBuilder.setMessage("ApiState.Failure: $errorMsg")
        dialogBuilder.show()
    }


    companion object {
        private const val TAG = "CardsFragment"
    }
}