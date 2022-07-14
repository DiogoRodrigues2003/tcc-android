package br.com.cwi.tcc_android.presentation.feature.userPets

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.cwi.tcc_android.R
import br.com.cwi.tcc_android.data.database.entity.PetEntity

class UserPetsAdapter (
    private val context: Context,
    private val list: List<PetEntity>,
    private val onDeleteClick: (PetEntity?) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = inflateView(R.layout.item_pet, parent)
        return UserPetsViewHolder(view, onDeleteClick)
    }

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {
        val item = list[position]
        (viewHolder as UserPetsViewHolder).bind(context, item)
    }

    override fun getItemCount() = list.size

    private fun inflateView(layout: Int, parent: ViewGroup) = LayoutInflater.from(parent.context)
        .inflate(layout, parent, false)
}