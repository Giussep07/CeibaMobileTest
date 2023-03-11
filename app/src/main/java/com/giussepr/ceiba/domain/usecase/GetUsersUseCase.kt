package com.giussepr.ceiba.domain.usecase

import com.giussepr.ceiba.domain.repository.CeibaRepository

class GetUsersUseCase(private val ceibaRepository: CeibaRepository) {

    operator fun invoke() = ceibaRepository.getUsers()
}