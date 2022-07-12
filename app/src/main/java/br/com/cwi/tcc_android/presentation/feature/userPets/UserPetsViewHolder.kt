package br.com.cwi.tcc_android.presentation.feature.userPets

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import br.com.cwi.tcc_android.data.database.entity.PetEntity
import br.com.cwi.tcc_android.databinding.ItemPetBinding
import com.bumptech.glide.Glide

class UserPetsViewHolder(
    itemView: View,
) : RecyclerView.ViewHolder(itemView) {
    private val binding = ItemPetBinding.bind(itemView)

    private val tvBreedName = binding.tvBreedName
    private val tvPetName = binding.tvPetName
    //private val ivPetImage = binding.ivPetImage
    //private val ivPetType = binding.ivPetType

    fun bind(context: Context, item: PetEntity?) {
        tvBreedName.text = item?.breedName
        tvPetName.text = item?.name
    }
}