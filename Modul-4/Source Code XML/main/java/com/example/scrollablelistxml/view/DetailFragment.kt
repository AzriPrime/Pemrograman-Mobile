package com.example.scrollablelistxml.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.scrollablelistxml.R
import com.example.scrollablelistxml.databinding.FragmentDetailBinding

class DetailFragment : Fragment() {

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!
    private val args: DetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val fish = args.fish

        with(binding) {
            imgDetailFish.setImageResource(fish.imageResId)
            tvDetailName.text = fish.name
            tvDetailLatinName.text = fish.latinName

            tvDetailHabitatLabel.text = getString(R.string.label_habitat)
            tvDetailHabitatValue.text = fish.habitat

            tvDetailSizeLabel.text = getString(R.string.label_size)
            tvDetailSizeValue.text = fish.size

            tvDetailDescLabel.text = getString(R.string.label_description)
            tvDetailDescValue.text = fish.description

            btnBack.setOnClickListener {
                findNavController().navigateUp()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}