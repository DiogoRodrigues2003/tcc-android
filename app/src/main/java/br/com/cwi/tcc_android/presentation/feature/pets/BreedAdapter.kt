package br.com.cwi.tcc_android.presentation.feature.pets

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.cwi.tcc_android.R
import br.com.cwi.tcc_android.domain.entity.Breed

class BreedAdapter (
    private val context: Context,
    private val list: List<Breed>,
    private val petType: String,
    private val onBreedClick: (Int, String) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
            val view = inflateView(R.layout.item_breed, parent)
            return BreedViewHolder(view, petType, onBreedClick)
    }

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {
        val item = list[position]
        (viewHolder as BreedViewHolder).bind(context, item)
    }

    override fun getItemCount() = list.size

    private fun inflateView(layout: Int, parent: ViewGroup) = LayoutInflater.from(parent.context)
        .inflate(layout, parent, false)
}