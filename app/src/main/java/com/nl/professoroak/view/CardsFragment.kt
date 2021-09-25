package com.nl.professoroak.view

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.datastore.dataStoreFile
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import com.nl.professoroak.adapter.PokeCardAdapter
import com.nl.professoroak.databinding.FragmentCardsBinding
import com.nl.professoroak.model.Data
import com.nl.professoroak.model.DataWrapper
import com.nl.professoroak.model.request.Queries
import com.nl.professoroak.util.ApiState
import com.nl.professoroak.viewmodel.PokeViewModel
import kotlinx.coroutines.launch

class CardsFragment : Fragment() {

    private var _binding: FragmentCardsBinding? = null
    private val binding get() = _binding!!
    private val pokeViewModel by activityViewModels<PokeViewModel>()
    private val pokeCardAdapter by lazy { PokeCardAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentCardsBinding.inflate(layoutInflater, container, false).also {
        _binding = it
    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        pokeViewModel.getImages(Queries("[1 TO 151]","name:char*"))
        setupObservers()
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
        Log.d(TAG, "loadCardImages data: ${data[3]}")
        if (adapter == null) adapter = pokeCardAdapter
        pokeCardAdapter.clear()
        pokeCardAdapter.updateList(data)
    }


    private fun handleFailure(errorMsg: String) {
        val dialogBuilder = AlertDialog.Builder(requireActivity())
        dialogBuilder.setMessage("ApiState.Failure: $errorMsg")
        dialogBuilder.show()
        Log.d(TAG, "ApiState.Failure: $errorMsg")
    }


    //    private fun initViews() = with(binding) {
//
//        rvList.addOnScrollListener(object : RecyclerView.OnScrollListener() {
//            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
//
//            }
//        })
//    }

    companion object {
        private const val TAG = "CardsFragment"
    }
}