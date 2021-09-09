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

    @Query("DELETE FROM stats WHERE name LIKE :pokemonName")
    suspend fun deleteStatLocal(pokemonName: String)

    //Função que provavelmente não será usada
    @Query("SELECT * FROM stats")
    suspend fun getAllStatLocal(): List<StatLocal>

    @Query("SELECT * FROM stats WHERE name LIKE :pokemonName")
    suspend fun getStatsLocal(pokemonName: String): List<StatLocal>

}