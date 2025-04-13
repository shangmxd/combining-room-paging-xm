package com.example.roomandpagingwithxml.ui.save

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.roomandpagingwithxml.adapters.SavedMangasRecyclerViewAdapter
import com.example.roomandpagingwithxml.databinding.FragmentSavedMangasBinding
import com.example.roomandpagingwithxml.viewmodel.MainViewModel

class SavedMangasFragment : Fragment() {
    private var _binding: FragmentSavedMangasBinding? = null
    private lateinit var binding: FragmentSavedMangasBinding
    private val savedMangasRecyclerViewAdapter = SavedMangasRecyclerViewAdapter()
    private val mainViewModel: MainViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentSavedMangasBinding.inflate(inflater, container, false)
        binding = _binding!!
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.savedMangaRv.adapter = savedMangasRecyclerViewAdapter
        binding.savedMangaRv.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

        mainViewModel.savedManga.observe(viewLifecycleOwner, Observer {
            savedMangasRecyclerViewAdapter.submitList(it)
        })
    }
}