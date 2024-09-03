package com.example.togetherapp.data.mappers.profile

import com.example.togetherapp.data.model.course.CourseDto
import com.example.togetherapp.data.model.course.CourseTextDto
import com.example.togetherapp.data.model.note.AuthorDto
import com.example.togetherapp.data.model.note.CommentDto
import com.example.togetherapp.data.model.note.NoteContentDto
import com.example.togetherapp.data.model.note.NoteDto
import com.example.togetherapp.data.model.profile.ProfilePreviewDto
import com.example.togetherapp.data.model.profile.UserProfileDto
import com.example.togetherapp.domain.model.comnote.Author
import com.example.togetherapp.domain.model.comnote.Comment
import com.example.togetherapp.domain.model.comnote.Note
import com.example.togetherapp.domain.model.comnote.NoteContent
import com.example.togetherapp.domain.model.course.Course
import com.example.togetherapp.domain.model.course.CourseText
import com.example.togetherapp.domain.model.profile.ProfilePreview
import com.example.togetherapp.domain.model.profile.UserProfile

interface UserProfileMapper {
    fun toDomain(dto: UserProfileDto): UserProfile
    fun toDto(domain: UserProfile): UserProfileDto
    fun toDomain(dto: ProfilePreviewDto): ProfilePreview
    fun toDto(domain: ProfilePreview): ProfilePreviewDto
    fun toDomain(dto: NoteDto): Note
    fun toDto(domain: Note): NoteDto
    fun toDomain(dto: NoteContentDto): NoteContent
    fun toDto(domain: NoteContent): NoteContentDto
    fun toDomain(dto: AuthorDto): Author
    fun toDto(domain: Author): AuthorDto
    fun toDomain(dto: CommentDto): Comment
    fun toDto(domain: Comment): CommentDto
    fun toDomain(dto: CourseDto): Course
    fun toDto(domain: Course): CourseDto
    fun toDomain(dto: CourseTextDto): CourseText
    fun toDto(domain: CourseText): CourseTextDto
}