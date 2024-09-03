package com.example.togetherapp.presentation.state

import com.example.togetherapp.domain.model.comnote.Note
import com.example.togetherapp.domain.model.locnote.LocNote
import com.example.togetherapp.domain.model.profile.ProfilePreview
import com.example.togetherapp.domain.model.profile.UserProfile

data class ProfileScreenState(
    val user: UserProfile? = null,
    val usersList: List<ProfilePreview> = emptyList(),
    val communityNote: Note? = null,
    val localNote: LocNote? = null,
    val isLoading: Boolean = true,
    val isMyProfile: Boolean = true,
    val showAllCourses: Boolean = false,
    val showAllNotes: Boolean = false,
    val showAllUsers: Boolean = false,
    val error: String? = null
)