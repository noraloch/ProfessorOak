package com.nl.professoroak.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.nl.professoroak.R
import com.nl.professoroak.databinding.FragmentMyCollectionBinding

class MyCollectionFragment : Fragment(R.layout.fragment_my_collection) {

    private var _binding: FragmentMyCollectionBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentMyCollectionBinding.inflate(layoutInflater, container, false).also {
        _binding = it
    }.root

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}