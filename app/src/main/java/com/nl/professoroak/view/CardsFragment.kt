package com.nl.professoroak.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.nl.professoroak.databinding.FragmentCardsBinding
import com.nl.professoroak.viewmodel.PokeViewModel

class CardsFragment : Fragment() {

    private var _binding: FragmentCardsBinding? = null
    private val binding get() = _binding!!
    private val pokeViewModel by activityViewModels<PokeViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return super.onCreateView(inflater, container, savedInstanceState)
    }


    companion object {
        private const val TAG = "CardsFragment"
    }
}