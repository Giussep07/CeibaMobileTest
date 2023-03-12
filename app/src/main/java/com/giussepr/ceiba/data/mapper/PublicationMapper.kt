package com.giussepr.ceiba.data.mapper

import com.giussepr.ceiba.data.model.PublicationResponseDTO
import com.giussepr.ceiba.domain.model.Publication

class PublicationMapper {

    fun mapToDomainPublication(publicationResponseDTO: PublicationResponseDTO): Publication {
        return Publication(
            id = publicationResponseDTO.id,
            title = publicationResponseDTO.title,
            body = publicationResponseDTO.body,
            userId = publicationResponseDTO.userId
        )
    }
}