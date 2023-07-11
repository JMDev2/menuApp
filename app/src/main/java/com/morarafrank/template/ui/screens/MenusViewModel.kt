package com.morarafrank.template.ui.screens

import android.graphics.Bitmap
import android.os.Environment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.morarafrank.template.db.model.Menu
import com.morarafrank.template.db.repository.MenuRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.File
import java.io.FileOutputStream
import javax.inject.Inject

@HiltViewModel
class MenusViewModel @Inject constructor(
    private val repository: MenuRepository
): ViewModel() {

    val allMenus = repository.getAllMenus()
    var menu = Menu(0, "", "", null)
        private set

     fun deleteMenu(menu: Menu) = viewModelScope.launch {
         repository.deleteMenu(menu)
     }

     fun addMenu(menu: Menu) = viewModelScope.launch {
        repository.addMenu(menu)
    }

     fun updateMenu(menuId: Int,name: String, total: String) {

       val newMenu = menu.copy(
           name = name,
           total = total
       )


    }

    fun updateMenuName(name: String) {
        menu = menu.copy(
            name = name
        )
    }

    fun updateMenuTotal(total: String) {
        menu = menu.copy(
            total = total
        )
    }

    fun getMenu(menuId: Int) = viewModelScope.launch {
        menu = repository.getMenu(menuId)
    }



    fun updateMenuImage(image: String){
        menu = menu.copy(
            image = image
        )
    }

    fun saveMenuWithImage(name: String, total: String, imagePath: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val menu = Menu(0, name = name, total = total, image = imagePath)
            repository.addMenu(menu)
        }
    }

//    fun updateMenuPath(imagePath: String){
//        viewModelScope.launch(Dispatchers.IO) {
//            menu = menu.copy(
//                image = imagePath
//            )
//        }
//    }

    fun updateMenuPath(imagePath: String) {
        val newMenu = menu.copy(image = imagePath)
        viewModelScope.launch(Dispatchers.IO) {
            menu = newMenu
            repository.updateMenu(menu)
        }
    }

}