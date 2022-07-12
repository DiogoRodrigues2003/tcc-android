package br.com.cwi.tcc_android.data.database

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import br.com.cwi.tcc_android.data.database.dao.PetDao
import br.com.cwi.tcc_android.data.database.entity.PetEntity

@Database(entities = [PetEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun getPetDao(): PetDao

    companion object {
        private const val DATABASE_NAME = "everything-pets-db-3"
        fun create(application: Application): AppDatabase {
            return Room.databaseBuilder(
                application,
                AppDatabase::class.java,
                DATABASE_NAME
            )
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build()
        }
    }
}