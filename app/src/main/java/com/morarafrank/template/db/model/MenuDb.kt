package com.morarafrank.template.db.model

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Menu::class], version = 1)
abstract class MenuDb : RoomDatabase() {

    abstract fun menuDao(): MenuDao
}
