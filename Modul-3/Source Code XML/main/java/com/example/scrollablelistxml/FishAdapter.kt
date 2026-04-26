package com.example.scrollablelistxml

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.scrollablelistxml.databinding.ItemFishBinding
import data.Fish

class FishAdapter(
    private val fishList: List<Fish>,
    private val onWikiClick: (Fish) -> Unit,
    private val onDetailClick: (Fish) -> Unit
) : RecyclerView.Adapter<FishAdapter.FishViewHolder>() {

    class FishViewHolder(val binding: ItemFishBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FishViewHolder {
        val binding = ItemFishBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return FishViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FishViewHolder, position: Int) {
        val fish = fishList[position]
        with(holder.binding) {
            imgFish.setImageResource(fish.imageResId)
            tvFishName.text = fish.name
            tvLatinName.text = fish.latinName
            tvHabitatLabel.text = root.context.getString(R.string.label_habitat)
            tvHabitatValue.text = fish.habitat
            tvSizeLabel.text = root.context.getString(R.string.label_size)
            tvSizeValue.text = fish.size
            tvDescriptionLabel.text = root.context.getString(R.string.label_description)
            tvDescriptionValue.text = fish.description

            btnWiki.setOnClickListener { onWikiClick(fish) }
            btnDetail.setOnClickListener { onDetailClick(fish) }
        }
    }

    override fun getItemCount(): Int = fishList.size
}
