package com.enzhongwen.hanyu3.database

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SavedViewModel(application: Application): AndroidViewModel(application) {

    val readAllData: LiveData<MutableList<Saved>>
    private val repository: SavedRepository

    init {
        val savedDao = SavedDatabase.getDatabase(application).savedDao()
        repository = SavedRepository(savedDao)
        readAllData = repository.readAllData
    }

    fun save(saved: Saved) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.save(saved)
        }

    }

    fun deleteAll() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAll()
        }
    }

    fun deleteItem(delete: Saved) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteItem(delete)
        }
    }
}