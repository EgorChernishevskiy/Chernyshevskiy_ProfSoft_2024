package com.example.togetherapp.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.togetherapp.domain.usecase.locnote.GetLocalNoteByIdUseCase
import com.example.togetherapp.presentation.event.LNoteDetailsScreenEvent
import com.example.togetherapp.presentation.state.note.LNoteDetailsScreenState
import kotlinx.coroutines.launch

class LNoteDetailsScreenViewModel(
    private val getLocalNoteByIdUseCase: GetLocalNoteByIdUseCase
) : ViewModel() {

    private val _state = MutableLiveData(LNoteDetailsScreenState())
    val state: LiveData<LNoteDetailsScreenState> get() = _state

    fun handleEvent(event: LNoteDetailsScreenEvent) {
        when (event) {
            is LNoteDetailsScreenEvent.LoadLNoteDetails -> {
                loadLNoteDetails(event.noteId)
            }
        }
    }

    private fun loadLNoteDetails(noteId: String) {
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