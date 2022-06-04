package com.enzhongwen.hanyu3.database

import androidx.annotation.DrawableRes
import androidx.annotation.RawRes
import androidx.annotation.StringRes
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "saved_table_3")
data class Saved(
    val word: String,
    val pinyin: String,
    @StringRes val definition: Int,
    @PrimaryKey(autoGenerate = false)
    val id1: Int,
    @DrawableRes val stroke1: Int,
    @DrawableRes val stroke2: Int? = null,
    @DrawableRes val stroke3: Int? = null,
    @RawRes val sound: Int,
    var saved: Boolean = false
)