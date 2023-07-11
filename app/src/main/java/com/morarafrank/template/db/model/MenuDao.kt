package com.morarafrank.template.db.model

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

typealias Menus = List<Menu>
@Dao
interface MenuDao {

    @Insert
    suspend fun addMenu(menu: Menu)

    @Update
    suspend fun updateMenu(menu: Menu)

    @Delete
    suspend fun deleteMenu(menu: Menu)

    @Query("SELECT * FROM menus ORDER BY id DESC")
    fun getAllMenus(): Flow<Menus>

    @Query("SELECT * FROM menus WHERE id = :id")
    suspend fun getMenu(id: Int): Menu


}