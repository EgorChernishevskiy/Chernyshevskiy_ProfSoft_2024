package com.example.togetherapp.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.togetherapp.domain.usecase.comnote.AddCommentUseCase
import com.example.togetherapp.domain.usecase.comnote.GetNoteByIdUseCase
import com.example.togetherapp.domain.usecase.favorite.AddFavoriteNoteUseCase
import com.example.togetherapp.domain.usecase.favorite.CheckNoteFavoriteStatusUseCase
import com.example.togetherapp.domain.usecase.favorite.RemoveFavoriteNoteUseCase
import com.example.togetherapp.presentation.event.CNoteDetailsScreenEvent
import com.example.togetherapp.presentation.state.note.CNoteDetailsScreenState
import kotlinx.coroutines.launch

class CNoteDetailsScreenViewModel(
    private val geNoteDetailsUseCase: GetNoteByIdUseCase,
    private val addCommentUseCase: AddCommentUseCase,
    private val addFavoriteNoteUseCase: AddFavoriteNoteUseCase,
    private val removeFavoriteNoteUseCase: RemoveFavoriteNoteUseCase,
    private val checkNoteFavoriteStatusUseCase: CheckNoteFavoriteStatusUseCase
) : ViewModel() {

    private val _state = MutableLiveData(CNoteDetailsScreenState())
    val state: LiveData<CNoteDetailsScreenState> get() = _state

    fun handleEvent(event: CNoteDetailsScreenEvent) {
        when (event) {
            is CNoteDetailsScreenEvent.LoadCNoteDetails -> {
                loadCNoteDetails(event.noteId)
            }

            is CNoteDetailsScreenEvent.AddComment -> {
                addComment(event.noteId)
            }

            is CNoteDetailsScreenEvent.UpdateCommentText -> {
                updateCommentText(event.newText)
            }

            is CNoteDetailsScreenEvent.AddToFavorite -> {
                addToFavorite()
            }
            is CNoteDetailsScreenEvent.CheckIfFavorite -> {
                checkIfFavorite(event.noteId)
            }
            is CNoteDetailsScreenEvent.RemoveFromFavorite -> {
                removeFromFavorite()
            }
        }
    }

    private fun removeFromFavorite() {
        viewModelScope.launch {
            try {
                state.value?.note?.let { removeFavoriteNoteUseCase.execute(it.id) }
                _state.value = _state.value?.copy(isFavorite = false)
            } catch (e: Exception) {
                _state.value = _state.value?.copy(error = e.message)
            }
        }
    }

    private fun checkIfFavorite(noteId: String) {
        viewModelScope.launch {
            try {
                val isFavorite = checkNoteFavoriteStatusUseCase.execute(noteId)
                _state.value = _state.value?.copy(isFavorite = isFavorite)
            } catch (e: Exception) {
                _state.value = _state.value?.copy(error = e.message)
            }
        }
    }

    private fun addToFavorite() {
        viewModelScope.launch {
            try {
                state.value?.note?.let { addFavoriteNoteUseCase.execute(it) }
                _state.value = _state.value?.copy(isFavorite = true)
            } catch (e: Exception) {
                _state.value = _state.value?.copy(error = e.message)
            }
        }
    }

    private fun loadCNoteDetails(courseId: String) {
        _state.value = _state.value?.copy(isLoading = true)
        viewModelScope.launch {
            try {
                val course = geNoteDetailsUseCase.execute(courseId)
                val isFavorite = _state.value?.isFavorite ?: false
                _state.value = CNoteDetailsScreenState(note = course, isLoading = false, isFavorite = isFavorite)
            } catch (e: Exception) {
                _state.value = CNoteDetailsScreenState(error = e.message, isLoading = false)
            }
        }
    }

    private fun addComment(noteId: String) {
        val currentCommentText = _state.value?.commentText ?: return
        if (currentCommentText.isBlank()) return

        viewModelScope.launch {
            try {
                addCommentUseCase.execute(noteId, currentCommentText)
                _state.value = _state.value?.copy(commentText = "")
                loadCNoteDetails(noteId)
            } catch (e: Exception) {
                _state.value = _state.value?.copy(error = e.message)
            }
        }
    }

    private fun updateCommentText(newText: String) {
        _state.value = _state.value?.copy(commentText = newText)
    }
}