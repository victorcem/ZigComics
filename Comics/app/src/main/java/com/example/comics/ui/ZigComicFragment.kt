package com.example.comics.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.comics.databinding.FragmentZigComicBinding
import com.example.comics.model.Comic
import com.example.comics.presentation.ZigComicsListViewModel
import com.example.comics.ui.adapter.ZigComicListAdapter
import com.example.comics.ui.viewholder.ZigComicListViewHolder
import com.example.comics.util.ZigComicsListEvent
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ZigComicFragment : Fragment() {

    private lateinit var binding: FragmentZigComicBinding

    private val viewModel: ZigComicsListViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentZigComicBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.onViewCreated()
        observeViewModel()
    }


    private fun observeViewModel() {
        lifecycleScope.launchWhenStarted {
            viewModel.comics.collect { comicsEvent ->
                when (comicsEvent) {
                    is ZigComicsListEvent.Loading -> {
                        setupComicsListShimmer(true)
                    }
                    is ZigComicsListEvent.Failure -> {
                        setupComicsListShimmer(false)
                        Toast.makeText(requireContext(), comicsEvent.message, Toast.LENGTH_LONG).show()
                    }
                    is ZigComicsListEvent.Success -> {
                        setupComicsListShimmer(false)
                        initAdapter(comicsEvent.comicsList)
                    }
                    else -> Unit
                }
            }
        }
    }

    private fun initAdapter(comicList: List<Comic>) {
        val zigAdapter = ZigComicListAdapter(comicList, object : ZigComicListViewHolder.OnZigComicClickListener {
            override fun onZigComicClick(comic: Comic, itemPosition: Int) {
                viewModel.onZigCardSelect(comic)
            }
        })

        val linearLayoutManager = LinearLayoutManager(requireContext())
        linearLayoutManager.orientation = RecyclerView.VERTICAL

        binding.listItem.apply {
            this.layoutManager = linearLayoutManager
            this.adapter = zigAdapter
        }
    }

    private fun setupComicsListShimmer(isVisible: Boolean) {
    }
}