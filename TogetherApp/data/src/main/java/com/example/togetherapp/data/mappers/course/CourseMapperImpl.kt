package com.example.togetherapp.data.mappers.course

import com.example.togetherapp.data.model.course.CourseDto
import com.example.togetherapp.data.model.course.CourseTextDto
import com.example.togetherapp.domain.model.course.Course
import com.example.togetherapp.domain.model.course.CourseText

class CourseMapperImpl : CourseMapper {
    override fun toDomain(dto: CourseDto): Course {
        return Course(
            id = dto.id,
            title = dto.title,
            description = dto.description,
            tags = dto.tags,
            text = dto.text.map { toDomain(it) }
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

    override fun toDomain(dto: CourseTextDto): CourseText {
        return CourseText(
            text = dto.text,
            image = dto.image
        )
    }

    override fun toDto(domain: CourseText): CourseTextDto {
        return CourseTextDto(
            text = domain.text,
            image = domain.image
        )
    }
}