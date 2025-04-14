package com.example.roomandpagingwithxml.ui.save

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.roomandpagingwithxml.adapters.SavedMangasRecyclerViewAdapter
import com.example.roomandpagingwithxml.databinding.FragmentSavedMangasBinding
import com.example.roomandpagingwithxml.utils.SwipeGesture
import com.example.roomandpagingwithxml.viewmodel.MainViewModel

class SavedMangasFragment : Fragment() {

    private var _binding: FragmentSavedMangasBinding? = null
    private val mainViewModel: MainViewModel by activityViewModels()
    private val savedMangasRecyclerViewAdapter = SavedMangasRecyclerViewAdapter()

    private lateinit var swipeGesture: SwipeGesture
    private lateinit var binding: FragmentSavedMangasBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentSavedMangasBinding.inflate(inflater, container, false)
        binding = _binding!!
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecyclerView()

        mainViewModel.savedManga.observe(viewLifecycleOwner) {
            savedMangasRecyclerViewAdapter.submitList(it)
        }
    }

    private fun initRecyclerView() {
        binding.savedMangaRv.adapter = savedMangasRecyclerViewAdapter
        binding.savedMangaRv.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

        swipeGesture = object : SwipeGesture() {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                super.onSwiped(viewHolder, direction)
                if (direction == ItemTouchHelper.LEFT) {
                    val position = viewHolder.absoluteAdapterPosition
                    val savedManga = savedMangasRecyclerViewAdapter.currentList[position]
                    mainViewModel.deleteMangaFromSaved(savedManga)
                    Toast.makeText(
                        requireContext(),
                        "${savedManga.mangaTitle} removed from Watchlist",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }

        val itemTouchHelper = ItemTouchHelper(swipeGesture)
        itemTouchHelper.attachToRecyclerView(binding.savedMangaRv)
    }
}