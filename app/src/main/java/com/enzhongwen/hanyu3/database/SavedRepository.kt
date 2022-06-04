package com.enzhongwen.hanyu3.database

import androidx.lifecycle.LiveData

class SavedRepository(private val savedDao: SavedDao) {
    val readAllData: LiveData<MutableList<Saved>> = savedDao.readAllData()

    suspend fun save(saved: Saved){
        savedDao.save(saved)
    }

    suspend fun deleteAll(){
        savedDao.deleteAll()
    }

    suspend fun deleteItem(delete: Saved){
        savedDao.deleteItem(delete)
    }

}