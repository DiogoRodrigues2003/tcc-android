package br.com.cwi.tcc_android.presentation.feature.pets

import android.view.View
import androidx.fragment.app.Fragment
import br.com.cwi.tcc_android.databinding.FragmentBreedDetailsBinding
import br.com.cwi.tcc_android.domain.entity.PetImage
import com.bumptech.glide.Glide

class PetImageViewHolder(itemView: View, private val onNewImageClick: () -> Unit) {
    private val binding = FragmentBreedDetailsBinding.bind(itemView)

    private val mbNewImage = binding.mbNewImage
    private val ivPetImage = binding.ivPetImage

    fun bind(context: Fragment, item: PetImage) {
        Glide.with(context)
            .load(item.url)
            .into(ivPetImage)

        mbNewImage.setOnClickListener {
            onNewImageClick()
        }
    }
}