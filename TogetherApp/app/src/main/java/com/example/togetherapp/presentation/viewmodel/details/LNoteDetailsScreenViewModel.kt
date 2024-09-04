package com.example.togetherapp.presentation.viewmodel.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.togetherapp.domain.usecase.favorite.AddFavoriteLocalNoteUseCase
import com.example.togetherapp.domain.usecase.favorite.CheckLocalNoteFavoriteStatusUseCase
import com.example.togetherapp.domain.usecase.favorite.RemoveFavoriteLocalNoteUseCase
import com.example.togetherapp.domain.usecase.locnote.GetLocalNoteByIdUseCase
import com.example.togetherapp.presentation.event.CourseDetailsScreenEvent
import com.example.togetherapp.presentation.event.LNoteDetailsScreenEvent
import com.example.togetherapp.presentation.state.note.LNoteDetailsScreenState
import kotlinx.coroutines.launch

class LNoteDetailsScreenViewModel(
    private val getLocalNoteByIdUseCase: GetLocalNoteByIdUseCase,
    private val checkLocalNoteFavoriteStatusUseCase: CheckLocalNoteFavoriteStatusUseCase,
    private val removeFavoriteLocalNoteUseCase: RemoveFavoriteLocalNoteUseCase,
    private val addFavoriteLocalNoteUseCase: AddFavoriteLocalNoteUseCase
) : ViewModel() {

    private val _state = MutableLiveData(LNoteDetailsScreenState())
    val state: LiveData<LNoteDetailsScreenState> get() = _state

    fun handleEvent(event: LNoteDetailsScreenEvent) {
        when (event) {
            is LNoteDetailsScreenEvent.OnErrorClear -> {
                _state.value = _state.value?.copy(error = null)
            }

            is LNoteDetailsScreenEvent.LoadLNoteDetails -> {
                loadLNoteDetails(event.noteId)
            }

            is LNoteDetailsScreenEvent.AddToFavorite -> {
                addToFavorite()
            }

            is LNoteDetailsScreenEvent.CheckIfFavorite -> {
                checkIfFavorite(event.noteId)
            }

            is LNoteDetailsScreenEvent.RemoveFromFavorite -> {
                removeFromFavorite()
            }
        }
    }

    private fun removeFromFavorite() {
        viewModelScope.launch {
            try {
                state.value?.note?.let { removeFavoriteLocalNoteUseCase.execute(it.id) }
                _state.value = _state.value?.copy(isFavorite = false)
            } catch (e: Exception) {
                _state.value = _state.value?.copy(error = e.message)
            }
        }
    }

    private fun checkIfFavorite(noteId: Int) {
        viewModelScope.launch {
            try {
                val isFavorite = checkLocalNoteFavoriteStatusUseCase.execute(noteId)
                _state.value = _state.value?.copy(isFavorite = isFavorite)
            } catch (e: Exception) {
                _state.value = _state.value?.copy(error = e.message)
            }
        }
    }

    private fun addToFavorite() {
        viewModelScope.launch {
            try {
                state.value?.note?.let { addFavoriteLocalNoteUseCase.execute(it) }
                _state.value = _state.value?.copy(isFavorite = true)
            } catch (e: Exception) {
                _state.value = _state.value?.copy(error = e.message)
            }
        }
    }

    private fun loadLNoteDetails(noteId: Int) {
        _state.value = _state.value?.copy(isLoading = true)
        viewModelScope.launch {
            try {
                val note = getLocalNoteByIdUseCase.execute(noteId)
                _state.value = LNoteDetailsScreenState(note = note, isLoading = false)
            } catch (e: Exception) {
                _state.value = LNoteDetailsScreenState(error = e.message, isLoading = false)
            }
        }
    }
}