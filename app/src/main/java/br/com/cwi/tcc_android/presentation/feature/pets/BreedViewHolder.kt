package br.com.cwi.tcc_android.presentation.feature.pets

import android.content.Context
import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import br.com.cwi.tcc_android.R
import br.com.cwi.tcc_android.databinding.ItemBreedBinding
import br.com.cwi.tcc_android.domain.entity.Breed
import com.bumptech.glide.Glide

class BreedViewHolder(
    itemView: View,
    private val petType: String
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
    }

    private fun getPetIcon() = ContextCompat.getDrawable(
        itemView.context,
        if (petType == "dog") R.drawable.ic_dog
        else R.drawable.ic_cat
    )
}