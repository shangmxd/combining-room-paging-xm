package com.example.roomandpagingwithxml.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import coil.Coil
import coil.request.ImageRequest
import com.example.roomandpagingwithxml.R
import com.example.roomandpagingwithxml.adapters.MangaRecyclerViewAdapter
import com.example.roomandpagingwithxml.data.Manga
import com.example.roomandpagingwithxml.database.data.MangaData
import com.example.roomandpagingwithxml.databinding.FragmentHomeBinding
import com.example.roomandpagingwithxml.extensions.toMangaDataClass
import com.example.roomandpagingwithxml.utils.RecyclerViewInterface
import com.example.roomandpagingwithxml.viewmodel.MainViewModel

class HomeFragment : Fragment(),RecyclerViewInterface {

    private var _binding: FragmentHomeBinding? = null
    private lateinit var binding:FragmentHomeBinding
    private var mangaRecyclerViewAdapter = MangaRecyclerViewAdapter(this)
    private val sharedViewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater,container,false)
        binding = _binding!!
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.mangaRecyclerView.adapter = mangaRecyclerViewAdapter
        binding.mangaRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        sharedViewModel.mangaResponse.observe(viewLifecycleOwner, Observer {
            mangaRecyclerViewAdapter.submitData(viewLifecycleOwner.lifecycle,it)
        })
    }

    override fun onSaveClicked(clickedManga: Manga?) {
        if(clickedManga!=null) {
            sharedViewModel.saveManga(clickedManga)
        }
        Toast.makeText(requireContext(),"onSaveClicked",Toast.LENGTH_SHORT).show()
    }
}