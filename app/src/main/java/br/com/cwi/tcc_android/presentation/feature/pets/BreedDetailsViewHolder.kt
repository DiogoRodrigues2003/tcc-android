package br.com.cwi.tcc_android.presentation.feature.pets

import android.view.View
import androidx.fragment.app.Fragment
import br.com.cwi.tcc_android.R
import br.com.cwi.tcc_android.databinding.FragmentBreedDetailsBinding
import br.com.cwi.tcc_android.domain.entity.Cat
import br.com.cwi.tcc_android.domain.entity.Dog
import br.com.cwi.tcc_android.domain.entity.PetType

class BreedDetailsViewHolder(itemView: View, private val onAddPetClick: (String, String, String) -> Unit) {
    private val binding = FragmentBreedDetailsBinding.bind(itemView)

    private val mbAddPet = binding.mbAddPet

    private val tvFirstInfoTitle = binding.tvFirstInfoTitle
    private val tvSecondInfoTitle = binding.tvSecondInfoTitle
    private val tvFifthInfoTitle = binding.tvFifthInfoTitle

    private val tvBreedName = binding.tvBreedName
    private val tvFirstInfo = binding.tvFirstInfo
    private val tvSecondInfo = binding.tvSecondInfo
    private val tvLifeSpan = binding.tvLifeSpan
    private val tvTemperament = binding.tvTemperament
    private val tvFifthInfo = binding.tvFifthInfo
    private val tvWeight = binding.tvWeight

    fun bind(context: Fragment, item: Dog) {
        tvFirstInfoTitle.text = context.getString(R.string.txt_bred_for)
        tvSecondInfoTitle.text = context.getString(R.string.txt_breed_group)
        tvFifthInfoTitle.text = context.getString(R.string.txt_height)

        tvBreedName.text = item.name
        tvFirstInfo.text = item.bredFor
        tvSecondInfo.text = item.breedGroup
        tvLifeSpan.text = item.lifeSpan
        tvTemperament.text = item.temperament
        tvFifthInfo.text = item.height
        tvWeight.text = item.weight

        addPetClickListener(item.id, item.name, item.type)
    }

    fun bind(context: Fragment, item: Cat) {
        tvFirstInfoTitle.text = context.getString(R.string.txt_natural)
        tvSecondInfoTitle.text = context.getString(R.string.txt_hairless)
        tvFifthInfoTitle.text = context.getString(R.string.txt_lap)

        tvBreedName.text = item.name
        tvFirstInfo.text = item.natural
        tvSecondInfo.text = item.hairless
        tvLifeSpan.text = item.lifeSpan
        tvTemperament.text = item.temperament
        tvFifthInfo.text = item.lap
        tvWeight.text = item.weight

        addPetClickListener(item.id, item.name, item.type)
    }

    private fun addPetClickListener(id: String, name: String, petType: PetType) {
        mbAddPet.setOnClickListener {
            onAddPetClick(id, name, petType.name)
        }
    }
}