package com.diwixis.filmlibrary.domain.viewmodels

import androidx.lifecycle.ViewModel
import com.diwixis.filmlibrary.domain.usecases.MainScreenUseCase

class MovieListViewModel(private val useCase: MainScreenUseCase) : ViewModel() {
}