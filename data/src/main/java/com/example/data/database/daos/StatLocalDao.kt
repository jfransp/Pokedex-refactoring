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

    //Função que provavelmente não será usada
    @Query("SELECT * FROM stats")
    suspend fun getAllStatLocal(): List<StatLocal>

    @Query("SELECT * FROM stats WHERE poke_id LIKE :pokemonId")
    suspend fun getStatsLocal(pokemonId: Int): List<StatLocal>

}