package com.example.data.database.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.data.database.entities.TypeLocal

@Dao
interface TypeLocalDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTypeLocal(typeLocal: TypeLocal)

    @Query("DELETE FROM types WHERE poke_id LIKE :pokemonId")
    suspend fun deleteTypeLocal(pokemonId: Int)

    @Query("SELECT * FROM types")
    suspend fun getAllTypeLocal(): List<TypeLocal>

    @Query("SELECT * FROM types WHERE poke_id LIKE :pokemonId")
    suspend fun getTypeLocal(pokemonId: Int): TypeLocal

}
