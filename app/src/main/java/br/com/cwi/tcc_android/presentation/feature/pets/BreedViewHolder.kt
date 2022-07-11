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
import br.com.cwi.tcc_android.domain.entity.Cat
import br.com.cwi.tcc_android.domain.entity.Dog
import br.com.cwi.tcc_android.domain.entity.PetImage
import com.bumptech.glide.Glide

const val TXT_BRED_FOR = "BRED FOR:"
const val TXT_BREED_GROUP = "BREED GROUP:"
const val TXT_HEIGHT = "HEIGHT:"
const val TXT_LAP = "LAP:"
const val TXT_NATURAL = "NATURAL:"
const val TXT_HAIRLESS = "HAIRLESS:"

class BreedViewHolder(
    itemView: View,
    private val petType: String,
    private val onBreedClick: (String, String) -> Unit
) : RecyclerView.ViewHolder(itemView) {
    private val tvBreedName = ItemBreedBinding.bind(itemView).tvBreedName
    private val ivPetImage = ItemBreedBinding.bind(itemView).ivPetImage
    private val ivPetType = ItemBreedBinding.bind(itemView).ivPetType

    fun bind(context: Context, item: Breed) {
        tvBreedName.text = item.name
        ivPetType.setImageDrawable(getPetIcon())

        Glide.with(context)
            .load(item.image?.url)
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
    private val tvFirstInfoTitle = FragmentBreedDetailsBinding.bind(itemView).tvFirstInfoTitle
    private val tvSecondInfoTitle = FragmentBreedDetailsBinding.bind(itemView).tvSecondInfoTitle
    private val tvFifthInfoTitle = FragmentBreedDetailsBinding.bind(itemView).tvFifthInfoTitle

    private val tvBreedName = FragmentBreedDetailsBinding.bind(itemView).tvBreedName
    private val tvFirstInfo = FragmentBreedDetailsBinding.bind(itemView).tvFirstInfo
    private val tvSecondInfo = FragmentBreedDetailsBinding.bind(itemView).tvSecondInfo
    private val tvLifeSpan = FragmentBreedDetailsBinding.bind(itemView).tvLifeSpan
    private val tvTemperament = FragmentBreedDetailsBinding.bind(itemView).tvTemperament
    private val tvFifthInfo = FragmentBreedDetailsBinding.bind(itemView).tvFifthInfo
    private val tvWeight = FragmentBreedDetailsBinding.bind(itemView).tvWeight

    fun bind(item: Dog) {
        tvFirstInfoTitle.text = TXT_BRED_FOR
        tvSecondInfoTitle.text = TXT_BREED_GROUP
        tvFifthInfoTitle.text = TXT_HEIGHT

        tvBreedName.text = item.name
        tvFirstInfo.text = item.bredFor
        tvSecondInfo.text = item.breedGroup
        tvLifeSpan.text = item.lifeSpan
        tvTemperament.text = item.temperament
        tvFifthInfo.text = item.height
        tvWeight.text = item.weight
    }

    fun bind(item: Cat) {
        tvFirstInfoTitle.text = TXT_NATURAL
        tvSecondInfoTitle.text = TXT_HAIRLESS
        tvFifthInfoTitle.text = TXT_LAP

        tvBreedName.text = item.name
        tvFirstInfo.text = item.natural
        tvSecondInfo.text = item.hairless
        tvLifeSpan.text = item.lifeSpan
        tvTemperament.text = item.temperament
        tvFifthInfo.text = item.lap
        tvWeight.text = item.weight
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



