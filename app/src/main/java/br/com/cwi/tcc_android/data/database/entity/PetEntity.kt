package br.com.cwi.tcc_android.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class PetEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val name: String,
    val breedName: String?,
    val breedId: String?,
    val petPhotoUrl: String
)