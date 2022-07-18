package br.com.cwi.tcc_android.presentation.feature.pets

import android.content.Context
import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import br.com.cwi.tcc_android.R
import br.com.cwi.tcc_android.databinding.ItemBreedBinding
import br.com.cwi.tcc_android.domain.entity.*
import br.com.cwi.tcc_android.presentation.constant.PetTypes
import com.bumptech.glide.Glide

class BreedViewHolder(
    itemView: View,
    private val petType: String,
    private val onBreedClick: (String, String) -> Unit
) : RecyclerView.ViewHolder(itemView) {
    private val binding = ItemBreedBinding.bind(itemView)

    private val tvBreedName = binding.tvBreedName
    private val ivPetImage = binding.ivPetImage
    private val ivPetType = binding.ivPetType

    fun bind(context: Context, item: Breed) {
        tvBreedName.text = item.name
        ivPetType.setImageDrawable(getPetIcon())

        Glide.with(context)
            .load(item.image?.url)
            .placeholder(R.drawable.ic_placeholder)
            .into(ivPetImage)

        itemView.setOnClickListener {
            onBreedClick(item.id, item.name)
        }
    }

    private fun getPetIcon() = ContextCompat.getDrawable(
        itemView.context,
        if (petType == PetTypes.DOG) R.drawable.ic_dog
        else R.drawable.ic_cat
    )
}



