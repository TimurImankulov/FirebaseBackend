package com.example.firebasebackend.ui.main

import androidx.lifecycle.ViewModel
import com.example.firebasebackend.data.repository.FirebaseRepository

class MainViewModel(private val repository: FirebaseRepository) : ViewModel() {

    fun showItems() = repository.loadData()
}
