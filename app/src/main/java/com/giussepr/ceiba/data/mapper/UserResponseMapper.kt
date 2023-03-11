package com.giussepr.ceiba.data.mapper

import com.giussepr.ceiba.data.model.UserResponseDTO
import com.giussepr.ceiba.domain.model.User

class UserResponseMapper {

    fun mapToUserDomain(userResponseDTO: UserResponseDTO): User {
        return User(
            id = userResponseDTO.id,
            name = userResponseDTO.name,
            email = userResponseDTO.email,
            phone = userResponseDTO.phone
        )
    }
}