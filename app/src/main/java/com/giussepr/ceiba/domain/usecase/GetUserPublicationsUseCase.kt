package com.giussepr.ceiba.domain.usecase

import com.giussepr.ceiba.domain.repository.CeibaRepository
import javax.inject.Inject

class GetUserPublicationsUseCase @Inject constructor(private val repository: CeibaRepository) {

    operator fun invoke(userId: Int) = repository.getUserPublications(userId)
}