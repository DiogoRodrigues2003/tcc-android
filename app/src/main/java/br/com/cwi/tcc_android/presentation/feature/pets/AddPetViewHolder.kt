package br.com.cwi.tcc_android.presentation.feature.pets

import android.view.View
import androidx.fragment.app.Fragment
import br.com.cwi.tcc_android.R
import br.com.cwi.tcc_android.databinding.FragmentAddPetBinding
import br.com.cwi.tcc_android.databinding.FragmentBreedDetailsBinding
import br.com.cwi.tcc_android.domain.entity.PetImage
import com.bumptech.glide.Glide

class AddPetViewHolder(itemView: View, private val onPetSubmitClick: (String) -> Unit) {
    private val binding = FragmentAddPetBinding.bind(itemView)

    private val tvNameInputTitle = binding.tvNameInputTitle
    private val etPetName = binding.etPetName
    private val mbSubmitPet= binding.mbSubmitPet

    fun bind(context: Fragment, name: String?) {
        tvNameInputTitle.text = context.getString(R.string.txt_name_input_title, name)

        mbSubmitPet.setOnClickListener {
            onPetSubmitClick(etPetName.text.toString())
        }
    }
}