package com.morarafrank.template.db.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.File

@Entity(tableName = "menus")
data class Menu(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    var name: String,
    var total: String,
    var image: String?,
)