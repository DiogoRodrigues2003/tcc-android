package br.com.cwi.tcc_android.presentation.feature.pets

import android.content.Context
import android.view.View
import br.com.cwi.tcc_android.R
import br.com.cwi.tcc_android.databinding.ActivityAddPetBinding

class AddPetViewHolder(itemView: View, private val onPetSubmitClick: (String) -> Unit) {
    private val binding = ActivityAddPetBinding.bind(itemView)

    private val tvNameInputTitle = binding.tvNameInputTitle
    private val etPetName = binding.etPetName
    private val mbSubmitPet= binding.mbSubmitPet

    fun bind(context: Context, name: String?) {
        tvNameInputTitle.text = context.getString(R.string.txt_name_input_title, name)

        mbSubmitPet.setOnClickListener {
            onPetSubmitClick(etPetName.text.toString())
        }
    }
}