package br.com.cwi.tcc_android.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class PetEntity(
    @PrimaryKey val id: String,
    val name: String,
    val breedName: String,
    val urlImage: String?
)