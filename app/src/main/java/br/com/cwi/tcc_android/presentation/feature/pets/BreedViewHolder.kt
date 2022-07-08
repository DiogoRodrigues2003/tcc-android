package br.com.cwi.tcc_android.presentation.feature.pets

import android.content.Context
import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import br.com.cwi.tcc_android.R
import br.com.cwi.tcc_android.databinding.FragmentBreedDetailsBinding
import br.com.cwi.tcc_android.databinding.ItemBreedBinding
import br.com.cwi.tcc_android.domain.entity.Breed
import br.com.cwi.tcc_android.domain.entity.Pet
import br.com.cwi.tcc_android.domain.entity.PetImage
import com.bumptech.glide.Glide

class BreedViewHolder(
    itemView: View,
    private val petType: String,
    private val onBreedClick: (Int, String) -> Unit
) : RecyclerView.ViewHolder(itemView) {
    private val tvBreedName = ItemBreedBinding.bind(itemView).tvBreedName
    private val ivPetImage = ItemBreedBinding.bind(itemView).ivPetImage
    private val ivPetType = ItemBreedBinding.bind(itemView).ivPetType

    fun bind(context: Context, item: Breed) {
        tvBreedName.text = item.name
        ivPetType.setImageDrawable(getPetIcon())

        Glide.with(context)
            .load(item.image.url)
            .into(ivPetImage)

        itemView.setOnClickListener {
            onBreedClick(item.id, item.name)
        }
    }

    private fun getPetIcon() = ContextCompat.getDrawable(
        itemView.context,
        if (petType == "dog") R.drawable.ic_dog
        else R.drawable.ic_cat
    )
}

class BreedDetailsViewHolder(itemView: View) {
    private val tvBreedName = FragmentBreedDetailsBinding.bind(itemView).tvBreedName
    private val tvBredFor = FragmentBreedDetailsBinding.bind(itemView).tvBredFor
    private val tvBreedGroup = FragmentBreedDetailsBinding.bind(itemView).tvBreedGroup
    private val tvLifeSpan = FragmentBreedDetailsBinding.bind(itemView).tvLifeSpan
    private val tvTemperament = FragmentBreedDetailsBinding.bind(itemView).tvTemperament
    private val tvWeight = FragmentBreedDetailsBinding.bind(itemView).tvWeight
    private val tvHeight = FragmentBreedDetailsBinding.bind(itemView).tvHeight

    fun bind(item: Pet) {
        tvBreedName.text = item.name
        tvBredFor.text = item.bredFor
        tvBreedGroup.text = item.breedGroup
        tvLifeSpan.text = item.lifeSpan
        tvTemperament.text = item.temperament
        tvWeight.text = item.weight
        tvHeight.text = item.height
    }
}

class PetImageViewHolder(itemView: View, private val onNewImageClick: () -> Unit) {
    private val mbNewImage = FragmentBreedDetailsBinding.bind(itemView).mbNewImage
    private val ivPetImage = FragmentBreedDetailsBinding.bind(itemView).ivPetImage

    fun bind(context: Fragment, item: PetImage) {
        Glide.with(context)
            .load(item.url)
            .into(ivPetImage)

        mbNewImage.setOnClickListener {
            onNewImageClick()
        }
    }
}



