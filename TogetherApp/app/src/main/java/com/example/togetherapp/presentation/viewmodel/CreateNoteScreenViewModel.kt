package com.example.togetherapp.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.togetherapp.domain.model.comnote.CreatedNote
import com.example.togetherapp.domain.model.comnote.NoteContent
import com.example.togetherapp.domain.model.locnote.LocNote
import com.example.togetherapp.domain.usecase.comnote.CreateNoteUseCase
import com.example.togetherapp.domain.usecase.locnote.CreateLocalNoteUseCase
import com.example.togetherapp.presentation.event.ChatScreenEvent
import com.example.togetherapp.presentation.event.CreateNoteScreenEvent
import com.example.togetherapp.presentation.state.CreateNoteScreenState
import kotlinx.coroutines.launch

class CreateNoteScreenViewModel(
    private val createLocalNoteUseCase: CreateLocalNoteUseCase,
    private val createNoteUseCase: CreateNoteUseCase
) : ViewModel() {
    private val _state = MutableLiveData(CreateNoteScreenState())
    val state: LiveData<CreateNoteScreenState> get() = _state

    private var localNoteIndex = 0
    private var communityNoteIndex = 0

    fun handleEvent(event: CreateNoteScreenEvent) {
        when (event) {
            is CreateNoteScreenEvent.OnErrorClear -> {
                _state.value = _state.value?.copy(error = null)
            }

            is CreateNoteScreenEvent.OnCommunitySelected -> {
                _state.value = _state.value?.copy(isLocal = false)
            }

            is CreateNoteScreenEvent.OnLocalSelected -> {
                _state.value = _state.value?.copy(isLocal = true)
            }

            is CreateNoteScreenEvent.OnTitleChange -> {
                _state.value = _state.value?.copy(title = event.title)
            }

            is CreateNoteScreenEvent.OnCommunityCreated -> {
                createCommunityNote()
                _state.value = _state.value?.copy(communityNote = null)
                communityNoteIndex = 0
            }

            is CreateNoteScreenEvent.OnLocalCreated -> {
                createLocalNote()
                _state.value = _state.value?.copy(localNote = null)
                localNoteIndex = 0
            }

            is CreateNoteScreenEvent.OnAddText -> {
                addTextContent()
            }

            is CreateNoteScreenEvent.OnAddPhoto -> {
                addPhotoContent()
            }

            is CreateNoteScreenEvent.OnShowAddPhoto -> {
                _state.value = _state.value?.copy(addPhoto = true)
            }

            is CreateNoteScreenEvent.OnShowAddText -> {
                _state.value = _state.value?.copy(addText = true)
            }

            is CreateNoteScreenEvent.OnWhatToAdd -> {
                _state.value = _state.value?.copy(whatToAdd = true)
            }

            is CreateNoteScreenEvent.OnDismissWhatToAdd -> {
                _state.value = _state.value?.copy(whatToAdd = false)
            }

            is CreateNoteScreenEvent.OnAddItemChange -> {
                _state.value = _state.value?.copy(addedItem = event.addItem)
            }

            is CreateNoteScreenEvent.OnDismissAddItem -> {
                _state.value = _state.value?.copy(addedItem = "", addPhoto = false, addText = false)
            }

            is CreateNoteScreenEvent.OnResetState -> {
                resetState()
            }
        }
    }

    private fun resetState() {
        _state.value = CreateNoteScreenState()
        localNoteIndex = 0
        communityNoteIndex = 0
    }

    private fun addPhotoContent() {
        val photoContent = NoteContent(
            text = "",
            image = _state.value?.addedItem ?: ""
        )

        if (_state.value?.isLocal == true) {
            val currentLocalNote = _state.value?.localNote ?: LocNote(
                id = 0,
                title = _state.value!!.title,
                content = mutableListOf(),
                date = ""
            )
            val updatedContent = currentLocalNote.content.toMutableList()
            if (localNoteIndex > 0 && updatedContent[localNoteIndex - 1].image == "") {
                updatedContent[localNoteIndex - 1] =
                    updatedContent[localNoteIndex - 1].copy(image = photoContent.image)
            } else {
                updatedContent.add(photoContent)
                localNoteIndex++
            }
            _state.value =
                _state.value?.copy(localNote = currentLocalNote.copy(content = updatedContent))
        } else {
            val currentCommunityNote = _state.value?.communityNote ?: CreatedNote(
                title = _state.value!!.title,
                content = mutableListOf()
            )
            val updatedContent = currentCommunityNote.content.toMutableList()
            if (communityNoteIndex > 0 && updatedContent[communityNoteIndex - 1].image == null) {
                updatedContent[communityNoteIndex - 1] =
                    updatedContent[communityNoteIndex - 1].copy(image = photoContent.image)
            } else {
                updatedContent.add(photoContent)
                communityNoteIndex++
            }
            _state.value =
                _state.value?.copy(communityNote = currentCommunityNote.copy(content = updatedContent))
        }
    }

    private fun addTextContent() {
        val textContent = NoteContent(
            text = _state.value?.addedItem ?: "",
            image = ""
        )

        if (_state.value?.isLocal == true) {
            val currentLocalNote = _state.value?.localNote ?: LocNote(
                id = 0,
                title = _state.value!!.title,
                content = mutableListOf(),
                date = ""
            )
            val updatedContent = currentLocalNote.content.toMutableList()
            updatedContent.add(textContent)

            _state.value =
                _state.value?.copy(localNote = currentLocalNote.copy(content = updatedContent))

            localNoteIndex++
        } else {
            val currentCommunityNote = _state.value?.communityNote ?: CreatedNote(
                title = _state.value!!.title,
                content = mutableListOf()
            )
            val updatedContent = currentCommunityNote.content.toMutableList()
            updatedContent.add(textContent)

            _state.value =
                _state.value?.copy(communityNote = currentCommunityNote.copy(content = updatedContent))
            communityNoteIndex++
        }
    }

    private fun createLocalNote() {
        val currentNote = _state.value?.localNote ?: return
        _state.value = _state.value?.copy(isLoading = true)

        viewModelScope.launch {
            try {
                createLocalNoteUseCase.execute(currentNote)
                _state.value = _state.value?.copy(localNote = null, isLoading = false)
            } catch (e: Exception) {
                _state.value = _state.value?.copy(error = e.message, isLoading = false)
            }
        }
    }

    private fun createCommunityNote() {
        val currentNote = _state.value?.communityNote ?: return
        _state.value = _state.value?.copy(isLoading = true)

        viewModelScope.launch {
            try {
                createNoteUseCase.execute(currentNote)
                _state.value = _state.value?.copy(communityNote = null, isLoading = false)
            } catch (e: Exception) {
                _state.value = _state.value?.copy(error = e.message, isLoading = false)
            }
        }
    }

}