package com.example.data.database.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.data.database.entities.StatLocal

@Dao
interface StatLocalDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertStatLocal(statLocal: StatLocal)

    @Query("DELETE FROM stats WHERE poke_id LIKE :pokemonId")
    suspend fun deleteStatLocal(pokemonId: Int)

    @Query("SELECT * FROM stats")
    suspend fun getAllStatLocal(): List<StatLocal>

    @Query("SELECT * FROM stats WHERE poke_id LIKE :pokemonId")
    suspend fun getStatLocal(pokemonId: Int): StatLocal

}