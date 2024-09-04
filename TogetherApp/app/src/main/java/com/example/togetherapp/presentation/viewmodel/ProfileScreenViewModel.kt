package com.example.togetherapp.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.togetherapp.domain.usecase.profile.GetAllUserProfilesUseCase
import com.example.togetherapp.domain.usecase.profile.GetUserProfileByIdUseCase
import com.example.togetherapp.domain.usecase.profile.GetUserProfileUseCase
import com.example.togetherapp.domain.usecase.profile.LogOutUseCase
import com.example.togetherapp.domain.usecase.profile.SetPhoneVisibilityUseCase
import com.example.togetherapp.presentation.event.ProfileScreenEvent
import com.example.togetherapp.presentation.state.ProfileScreenState
import kotlinx.coroutines.launch

class ProfileScreenViewModel(
    private val getUserProfileUseCase: GetUserProfileUseCase,
    private val getAllUserProfilesUseCase: GetAllUserProfilesUseCase,
    private val getUserProfileByIdUseCase: GetUserProfileByIdUseCase,
    private val setPhoneVisibilityUseCase: SetPhoneVisibilityUseCase,
    private val logOutUseCase: LogOutUseCase
) : ViewModel() {

    private val _state = MutableLiveData(ProfileScreenState())
    val state: LiveData<ProfileScreenState> get() = _state

    fun handleEvent(event: ProfileScreenEvent) {
        when (event) {
            is ProfileScreenEvent.HideAllCourses -> {
                _state.value = _state.value?.copy(showAllCourses = false)
            }

            is ProfileScreenEvent.HideAllNotes -> {
                _state.value = _state.value?.copy(showAllNotes = false)
            }

            is ProfileScreenEvent.HideAllUsers-> {
                _state.value = _state.value?.copy(showAllUsers = false)
            }

            is ProfileScreenEvent.OnLoadUserProfile -> {
                loadUserProfile()
                _state.value = _state.value?.copy(isMyProfile = true)
            }

            is ProfileScreenEvent.OnChangeNumberVisibility -> {
                setPhoneVisibility()
            }

            is ProfileScreenEvent.OnErrorClear -> {
                _state.value = _state.value?.copy(error = null)
            }

            is ProfileScreenEvent.ShowAllCourses -> {
                _state.value = _state.value?.copy(showAllCourses = true)
            }

            is ProfileScreenEvent.ShowAllNotes -> {
                _state.value = _state.value?.copy(showAllNotes = true)
            }

            is ProfileScreenEvent.ShowAllUsers -> {
                loadAllUsers()
                _state.value = _state.value?.copy(showAllUsers = true)
            }

            is ProfileScreenEvent.OnLoadUserProfileById -> {
                getUserProfileById(event.id)
                _state.value = _state.value?.copy(isMyProfile = false)
            }

            is ProfileScreenEvent.OnLogOut -> {
                logOutUseCase()
            }
        }
    }

    private fun getUserProfileById(userId: String) {
        _state.value = _state.value?.copy(isLoading = true)
        viewModelScope.launch {
            try {
                val user = getUserProfileByIdUseCase.execute(userId)
                Log.d("UserProfile1", "${user}")
                _state.value = _state.value?.copy(user = user, isLoading = false)
            } catch (e: Exception) {
                _state.value = _state.value?.copy(error = e.message, isLoading = false)
            }
        }
    }

    private fun loadAllUsers() {
        _state.value = _state.value?.copy(isLoading = true)
        viewModelScope.launch {
            try {
                val users = getAllUserProfilesUseCase.execute()
                _state.value = _state.value?.copy(usersList = users, isLoading = false)
            } catch (e: Exception) {
                _state.value = _state.value?.copy(error = e.message, isLoading = false)
            }
        }
    }

    private fun setPhoneVisibility() {
        _state.value = _state.value?.copy(isLoading = true)
        viewModelScope.launch {
            try {
                val user = setPhoneVisibilityUseCase.execute(false)
                _state.value = _state.value?.copy(user = user, isLoading = false)
            } catch (e: Exception) {
                _state.value = _state.value?.copy(error = e.message, isLoading = false)
            }
        }
    }

    private fun loadUserProfile() {
        _state.value = _state.value?.copy(isLoading = true)
        viewModelScope.launch {
            try {
                val user = getUserProfileUseCase.execute()
                _state.value = _state.value?.copy(user = user, isLoading = false)
            } catch (e: Exception) {
                _state.value = _state.value?.copy(error = e.message, isLoading = false)
            }
        }
    }

}