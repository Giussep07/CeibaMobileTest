package com.giussepr.ceiba.data.mapper

import com.giussepr.ceiba.data.database.entity.PublicationEntity
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

    fun mapToDomainPublication(publicationEntity: PublicationEntity): Publication {
        return Publication(
            id = publicationEntity.id,
            title = publicationEntity.title,
            body = publicationEntity.body,
            userId = publicationEntity.userId
        )
    }

    fun mapToEntityPublication(publicationResponseDTO: PublicationResponseDTO): PublicationEntity {
        return PublicationEntity(
            id = 0,
            publicationId = publicationResponseDTO.id,
            title = publicationResponseDTO.title,
            body = publicationResponseDTO.body,
            userId = publicationResponseDTO.userId
        )
    }
}