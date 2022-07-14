package br.com.cwi.tcc_android.presentation.feature.userPets

import android.content.Context
import android.net.Uri
import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import br.com.cwi.tcc_android.R
import br.com.cwi.tcc_android.data.database.entity.PetEntity
import br.com.cwi.tcc_android.databinding.ItemPetBinding
import br.com.cwi.tcc_android.presentation.constant.PetTypes
import com.bumptech.glide.Glide
import java.io.File

class UserPetsViewHolder(
    itemView: View,
    private val onDeleteClick: (PetEntity?) -> Unit
) : RecyclerView.ViewHolder(itemView) {
    private val binding = ItemPetBinding.bind(itemView)

    private val tvBreedName = binding.tvBreedName
    private val tvPetName = binding.tvPetName
    private val ivPetImage = binding.ivPetImage
    private val ivPetType = binding.ivPetType
    private val ivDeletePet = binding.ivDeletePet

    fun bind(context: Context, item: PetEntity?) {
        tvBreedName.text = item?.breedName
        tvPetName.text = item?.name
        ivPetType.setImageDrawable(getPetIcon(item?.petType))

        Glide.with(context)
            .load(item?.petPhotoUrl)
            .into(ivPetImage)

        ivDeletePet.setOnClickListener {
            onDeleteClick(item)
        }
    }

    private fun getPetIcon(petType: String?) = ContextCompat.getDrawable(
        itemView.context,
        if (petType == PetTypes.DOG) R.drawable.ic_dog
        else R.drawable.ic_cat
    )
}