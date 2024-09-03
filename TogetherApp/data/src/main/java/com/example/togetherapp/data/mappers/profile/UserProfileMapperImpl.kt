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

class UserProfileMapperImpl : UserProfileMapper {
    override fun toDomain(dto: UserProfileDto): UserProfile {
        return UserProfile(
            id = dto.id,
            name = dto.name,
            surname = dto.surname,
            avatar = dto.avatar,
            role = dto.role,
            phone = dto.phone,
            registerDate = dto.registerDate,
            courses = dto.courses.map { toDomain(it) },
            notes = dto.notes.map { toDomain(it) }
        )
    }

    override fun toDomain(dto: ProfilePreviewDto): ProfilePreview {
        return ProfilePreview(
            id = dto.id,
            name = dto.name,
            surname = dto.surname,
            avatar = dto.avatar
        )
    }

    override fun toDomain(dto: NoteDto): Note {
        return Note(
            id = dto.id,
            title = dto.title,
            content = dto.content.map { toDomain(it) },
            author = toDomain(dto.author),
            date = dto.date,
            comments = dto.comments.map { toDomain(it) }
        )
    }

    override fun toDomain(dto: NoteContentDto): NoteContent {
        return NoteContent(
            text = dto.text,
            image = dto.image
        )
    }

    override fun toDomain(dto: AuthorDto): Author {
        return Author(
            id = dto.id,
            name = dto.name,
            surname = dto.surname,
            avatar = dto.avatar,
            role = dto.role
        )
    }


    override fun toDomain(dto: CommentDto): Comment {
        return Comment(
            id = dto.id,
            author = toDomain(dto.author),
            text = dto.text
        )
    }

    override fun toDomain(dto: CourseDto): Course {
        return Course(
            id = dto.id,
            title = dto.title,
            description = dto.description,
            tags = dto.tags,
            text = dto.text.map { toDomain(it) }
        )
    }

    override fun toDomain(dto: CourseTextDto): CourseText {
        return CourseText(
            text = dto.text,
            image = dto.image
        )
    }

    override fun toDto(domain: UserProfile): UserProfileDto {
        return UserProfileDto(
            id = domain.id,
            name = domain.name,
            surname = domain.surname,
            avatar = domain.avatar,
            role = domain.role,
            phone = domain.phone,
            registerDate = domain.registerDate,
            courses = domain.courses.map { toDto(it) },
            notes = domain.notes.map { toDto(it) }
        )
    }

    override fun toDto(domain: ProfilePreview): ProfilePreviewDto {
        return ProfilePreviewDto(
            id = domain.id,
            name = domain.name,
            surname = domain.surname,
            avatar = domain.avatar
        )
    }

    override fun toDto(domain: Note): NoteDto {
        return NoteDto(
            id = domain.id,
            title = domain.title,
            content = domain.content.map { toDto(it) },
            author = toDto(domain.author),
            date = domain.date,
            comments = domain.comments.map { toDto(it) }
        )
    }

    override fun toDto(domain: NoteContent): NoteContentDto {
        return NoteContentDto(
            text = domain.text,
            image = domain.image
        )
    }

    override fun toDto(domain: Author): AuthorDto {
        return AuthorDto(
            id = domain.id,
            name = domain.name,
            surname = domain.surname,
            avatar = domain.avatar,
            role = domain.role
        )
    }

    override fun toDto(domain: Comment): CommentDto {
        return CommentDto(
            id = domain.id,
            author = toDto(domain.author),
            text = domain.text
        )
    }

    override fun toDto(domain: Course): CourseDto {
        return CourseDto(
            id = domain.id,
            title = domain.title,
            description = domain.description,
            tags = domain.tags,
            text = domain.text.map { toDto(it) }
        )
    }

    override fun toDto(domain: CourseText): CourseTextDto {
        return CourseTextDto(
            text = domain.text,
            image = domain.image
        )
    }

}