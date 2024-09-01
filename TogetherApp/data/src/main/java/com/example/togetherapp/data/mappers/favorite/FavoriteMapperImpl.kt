package com.example.togetherapp.data.mappers.favorite

import com.example.togetherapp.data.database.entity.favorite.FavoriteCourseEntity
import com.example.togetherapp.data.database.entity.favorite.FavoriteLocalNoteEntity
import com.example.togetherapp.data.database.entity.favorite.FavoriteNoteEntity
import com.example.togetherapp.domain.model.course.Course
import com.example.togetherapp.domain.model.comnote.Note
import com.example.togetherapp.domain.model.locnote.LocNote

class FavoriteMapperImpl : FavoriteMapper {

    override fun mapToEntity(course: Course): FavoriteCourseEntity {
        return FavoriteCourseEntity(
            id = course.id,
            title = course.title,
            description = course.description,
            tags = course.tags,
            text = course.text
        )
    }

    override fun mapToEntity(note: Note): FavoriteNoteEntity {
        return FavoriteNoteEntity(
            id = note.id,
            title = note.title,
            content = note.content,
            author = note.author,
            date = note.date,
            comments = note.comments
        )
    }

    override fun mapToEntity(note: LocNote): FavoriteLocalNoteEntity {
        return FavoriteLocalNoteEntity(
            id = note.id,
            title = note.title,
            content = note.content,
            date = note.date
        )
    }

    override fun mapFromEntity(entity: FavoriteCourseEntity): Course {
        return Course(
            id = entity.id,
            title = entity.title,
            description = entity.description,
            tags = entity.tags,
            text = entity.text
        )
    }

    override fun mapFromEntity(entity: FavoriteNoteEntity): Note {
        return Note(
            id = entity.id,
            title = entity.title,
            content = entity.content,
            author = entity.author,
            date = entity.date,
            comments = entity.comments
        )
    }

    override fun mapFromEntity(entity: FavoriteLocalNoteEntity): LocNote {
        return LocNote(
            id = entity.id,
            title = entity.title,
            content = entity.content,
            date = entity.date
        )
    }
}