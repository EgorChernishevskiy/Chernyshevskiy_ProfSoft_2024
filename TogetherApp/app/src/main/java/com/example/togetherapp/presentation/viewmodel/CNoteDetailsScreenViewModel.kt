package com.example.togetherapp.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.togetherapp.domain.usecase.comnote.AddCommentUseCase
import com.example.togetherapp.domain.usecase.comnote.GetNoteByIdUseCase
import com.example.togetherapp.presentation.event.CNoteDetailsScreenEvent
import com.example.togetherapp.presentation.state.note.CNoteDetailsScreenState
import kotlinx.coroutines.launch

class CNoteDetailsScreenViewModel(
    private val geNoteDetailsUseCase: GetNoteByIdUseCase,
    private val addCommentUseCase: AddCommentUseCase
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
        }
    }

    private fun loadCNoteDetails(courseId: String) {
        _state.value = _state.value?.copy(isLoading = true)
        viewModelScope.launch {
            try {
                val course = geNoteDetailsUseCase.execute(courseId)
                _state.value = CNoteDetailsScreenState(note = course, isLoading = false)
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