package br.com.cwi.tcc_android.data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import br.com.cwi.tcc_android.data.database.entity.PetEntity

@Dao
interface PetDao {

    @Insert
    fun add(petEntity: PetEntity)

    @Delete
    fun remove(coffeeEntity: PetEntity)

    @Query("SELECT * FROM PetEntity")
    fun getAll(): List<PetEntity>?
}