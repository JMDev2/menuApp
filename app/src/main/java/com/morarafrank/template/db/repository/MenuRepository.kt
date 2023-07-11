package com.morarafrank.template.db.repository

import com.morarafrank.template.db.model.Menu
import com.morarafrank.template.db.model.MenuDao
import javax.inject.Inject

class MenuRepository @Inject constructor(
    private val menuDao: MenuDao
){
    fun getAllMenus() = menuDao.getAllMenus()

    suspend fun addMenu(menu: Menu) = menuDao.addMenu(menu)

    suspend fun updateMenu(menu: Menu) = menuDao.updateMenu(menu)

    suspend fun deleteMenu(menu: Menu) = menuDao.deleteMenu(menu)

    suspend fun getMenu(menuId: Int) = menuDao.getMenu(menuId)


}