package com.example.togetherapp.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.togetherapp.domain.usecase.comnote.GetNotesByTopicUseCase
import com.example.togetherapp.domain.usecase.course.GetCoursesUseCase
import com.example.togetherapp.domain.usecase.comnote.GetNotesUseCase
import com.example.togetherapp.domain.usecase.course.GetChallengesByTopicUseCase
import com.example.togetherapp.domain.usecase.course.GetChallengesUseCase
import com.example.togetherapp.domain.usecase.locnote.GetAllLocalNotesUseCase
import com.example.togetherapp.domain.utils.NoteTopic
import com.example.togetherapp.presentation.event.MainScreenEvent
import com.example.togetherapp.presentation.state.MainScreenState
import kotlinx.coroutines.launch

class MainScreenViewModel(
    //private val getCoursesUseCase: GetCoursesUseCase,
    private val getNotesUseCase: GetNotesUseCase,
    private val getAllLocalNotesUseCase: GetAllLocalNotesUseCase,
    private val getNotesByTopicUseCase: GetNotesByTopicUseCase,
    private val getChallengesUseCase: GetChallengesUseCase,
    private val getChallengesByTopicUseCase: GetChallengesByTopicUseCase
) : ViewModel() {

    private val _state = MutableLiveData(MainScreenState())
    val state: LiveData<MainScreenState> get() = _state

    fun handleEvent(event: MainScreenEvent) {
        when (event) {

            is MainScreenEvent.LoadChallengesByTopic -> {
                loadChallengesByTopic(event.topic)
            }

            is MainScreenEvent.LoadChallenges -> {
                loadChallenges()
            }

            is MainScreenEvent.UpdateTopicName -> {
                _state.value = _state.value?.copy(topicName = event.topicName)
            }

            is MainScreenEvent.LoadNotesByTopic -> {
                loadNotesByTopic(event.topic)
            }

            is MainScreenEvent.OnErrorClear -> {
                _state.value = _state.value?.copy(error = null)
            }

            is MainScreenEvent.LoadCourses -> {
                //fetchCourses()
            }

            is MainScreenEvent.LoadNotes -> {
                fetchNotes()
            }

            is MainScreenEvent.LoadLocalNotes -> {
                fetchLocalNotes()
            }

            is MainScreenEvent.ShowAllCourses -> {
                _state.value = _state.value?.copy(showAllCourses = true)
                loadChallenges()
            }

            is MainScreenEvent.HideAllCourses -> {
                _state.value = _state.value?.copy(showAllCourses = false)
            }

            is MainScreenEvent.ShowAllNotes -> {
                _state.value = _state.value?.copy(showAllNotes = true)
                loadNotes()
            }

            is MainScreenEvent.ShowAllLocalNotes -> {
                _state.value = _state.value?.copy(showAllLocalNotes = true)
                loadLocalNotes()
            }

            is MainScreenEvent.HideAllLocalNotes -> {
                _state.value = _state.value?.copy(showAllLocalNotes = false)
            }

            is MainScreenEvent.HideAllNotes -> {
                _state.value = _state.value?.copy(showAllNotes = false)
            }

            is MainScreenEvent.NavigateToLogin -> {
                _state.value = _state.value?.copy(isNavigatedToLogin = true)
            }

            MainScreenEvent.OnResetState -> {
                resetState()
            }

        }
    }

    private fun loadChallenges() {
        _state.value = _state.value?.copy(isLoading = true)
        viewModelScope.launch {
            try {
                val challenges = getChallengesUseCase.execute()
                _state.value = _state.value?.copy(challenges = challenges, isLoading = false)
            } catch (e: Exception) {
                _state.value = _state.value?.copy(error = e.message, isLoading = false)
            }
        }
    }

    private fun loadChallengesByTopic(topic: NoteTopic) {
        _state.value = _state.value?.copy(isLoading = true)
        viewModelScope.launch {
            try {
                val challenges = getChallengesByTopicUseCase.execute(topic)
                _state.value = _state.value?.copy(challenges = challenges, isLoading = false)
            } catch (e: Exception) {
                _state.value = _state.value?.copy(error = e.message, isLoading = false)
            }
        }
    }

    private fun loadNotesByTopic(topic: NoteTopic) {
        _state.value = _state.value?.copy(isLoading = true)
        viewModelScope.launch {
            try {
                val notes = getNotesByTopicUseCase.execute(topic)
                _state.value = _state.value?.copy(notes = notes, isLoading = false)
            } catch (e: Exception) {
                _state.value = _state.value?.copy(error = e.message, isLoading = false)
            }
        }
    }

    private fun resetState() {
        _state.value = MainScreenState()
    }

    private fun loadLocalNotes() {
        _state.value = _state.value?.copy(isLoading = true)
        viewModelScope.launch {
            try {
                val notes = getAllLocalNotesUseCase.execute()
                _state.value = _state.value?.copy(localNotes = notes, isLoading = false)
            } catch (e: Exception) {
                _state.value = _state.value?.copy(error = e.message, isLoading = false)
            }
        }
    }

    private fun fetchLocalNotes() {
        viewModelScope.launch {
            _state.value = _state.value?.copy(isLoading = true)
            try {
                val notesList = getAllLocalNotesUseCase.execute()
                val lastLocalNote = notesList.firstOrNull()
                _state.value = _state.value?.copy(localNote = lastLocalNote)
            } catch (e: Exception) {
                _state.value = _state.value?.copy(error = e.message)
            } finally {
                _state.value = _state.value?.copy(isLoading = false)
            }
        }
    }

    private fun loadNotes() {
        _state.value = _state.value?.copy(isLoading = true)
        viewModelScope.launch {
            try {
                val notes = getNotesUseCase.execute()
                println("Загружены все заметки: ${notes.size}") // Логирование
                _state.value = _state.value?.copy(notes = notes, isLoading = false)
            } catch (e: Exception) {
                println("Ошибка при загрузке всех заметок: ${e.message}") // Логирование
                _state.value = _state.value?.copy(error = e.message, isLoading = false)
            }
        }
    }

//    private fun loadCourses() {
//        _state.value = _state.value?.copy(isLoading = true)
//        viewModelScope.launch {
//            try {
//                val courses = getCoursesUseCase.execute()
//                _state.value = _state.value?.copy(courses = courses, isLoading = false)
//            } catch (e: Exception) {
//                _state.value = _state.value?.copy(error = e.message, isLoading = false)
//            }
//        }
//    }

//    private fun fetchCourses() {
//        viewModelScope.launch {
//            _state.value = _state.value?.copy(isLoading = true)
//            try {
//                val coursesList = getCoursesUseCase.execute()
//                _state.value = _state.value?.copy(courses = coursesList.take(6))
//            } catch (e: Exception) {
//                _state.value = _state.value?.copy(error = e.message)
//            } finally {
//                _state.value = _state.value?.copy(isLoading = false)
//            }
//        }
//    }

    private fun fetchNotes() {
        viewModelScope.launch {
            _state.value = _state.value?.copy(isLoading = true)
            try {
                val notesList = getNotesUseCase.execute()
                val lastCommunityNote = notesList.firstOrNull()
                _state.value = _state.value?.copy(communityNote = lastCommunityNote)
            } catch (e: Exception) {
                _state.value = _state.value?.copy(error = e.message)
            } finally {
                _state.value = _state.value?.copy(isLoading = false)
            }
        }
    }
}
