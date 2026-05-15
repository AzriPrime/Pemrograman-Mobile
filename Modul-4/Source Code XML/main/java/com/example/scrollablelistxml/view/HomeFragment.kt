package com.example.scrollablelistxml.view

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.scrollablelistxml.view.FishAdapter
import com.example.scrollablelistxml.R
import com.example.scrollablelistxml.databinding.FragmentHomeBinding
import com.example.scrollablelistxml.viewmodel.FishViewModel
import com.example.scrollablelistxml.viewmodel.FishViewModelFactory
import kotlinx.coroutines.launch
import timber.log.Timber

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: FishViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val factory = FishViewModelFactory(getString(R.string.title_fish_species))
        viewModel = ViewModelProvider(this, factory)[FishViewModel::class.java]

        val adapter = FishAdapter(
            fishList = emptyList(),
            onWikiClick = { fish -> viewModel.onWikiClick(fish) },
            onDetailClick = { fish -> viewModel.onDetailClick(fish) }
        )

        binding.rvHorizontalFish.adapter = adapter
        binding.rvVerticalFish.adapter = adapter

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {

                launch {
                    viewModel.fishList.collect { fishList ->
                        adapter.updateList(fishList)
                    }
                }

                launch {
                    viewModel.navigateToDetail.collect { fish ->
                        fish?.let {
                            Timber.d("Navigating to detail: name=%s, latin=%s, habitat=%s, size=%s",
                                it.name, it.latinName, it.habitat, it.size)
                            val action = HomeFragmentDirections
                                .actionHomeFragmentToDetailFragment(it)
                            findNavController().navigate(action)
                            viewModel.onDetailNavigated()
                        }
                    }
                }

                launch {
                    viewModel.openWikiUrl.collect { url ->
                        url?.let {
                            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(it))
                            startActivity(intent)
                            viewModel.onWikiOpened() 
                        }
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}