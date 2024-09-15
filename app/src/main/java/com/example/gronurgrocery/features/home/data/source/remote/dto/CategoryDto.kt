package com.example.gronurgrocery.features.home.data.source.remote.dto

data class CategoryDto(
    val name: String
)

fun CategoryDto.toDomain(): String {
    return name
}