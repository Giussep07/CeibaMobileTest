package com.giussepr.ceiba.presentation.publications

import androidx.lifecycle.SavedStateHandle
import com.giussepr.ceiba.domain.model.DomainException
import com.giussepr.ceiba.domain.model.Publication
import com.giussepr.ceiba.domain.model.Result
import com.giussepr.ceiba.domain.usecase.GetUserPublicationsUseCase
import com.giussepr.ceiba.ui.presentation.screens.publications.PublicationsViewModel
import com.giussepr.ceiba.utils.DispatcherProvider
import com.giussepr.ceiba.utils.TestDispatcherProvider
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class PublicationsViewModelTest {

    private lateinit var viewModel: PublicationsViewModel
    private lateinit var dispatcherProvider: DispatcherProvider

    private val getUserPublicationsUseCase: GetUserPublicationsUseCase = mockk()

    @Before
    fun setUp() {
        dispatcherProvider = TestDispatcherProvider()

        val savedStateHandle = SavedStateHandle().apply {
            set("userId", 1)
            set("name", "Giussep Ricardo Cachaya")
            set("phone", "3124609512")
            set("email", "giussepr@gmail.com")
        }

        viewModel = PublicationsViewModel(
            getUserPublicationsUseCase = getUserPublicationsUseCase,
            dispatcherProvider = dispatcherProvider,
            savedStateHandle = savedStateHandle
        )

        Dispatchers.setMain(dispatcherProvider.main)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `test onLoadPublications() success`() {
        // Prepare

        val expected = buildList {
            add(Publication(id = 1, userId = 1, title = "publication 1", body = "body 1"))
            add(Publication(id = 2, userId = 1, title = "publication 2", body = "body 2"))
            add(Publication(id = 3, userId = 1, title = "publication 3", body = "body 3"))
            add(Publication(id = 4, userId = 1, title = "publication 4", body = "body 4"))
        }

        every { getUserPublicationsUseCase(1) } returns flowOf(Result.Success(expected))

        // Execute
        viewModel.onLoadPublications()

        // Assert
        assert(viewModel.uiState.publications.isNotEmpty())
        assertEquals(viewModel.uiState.publications.size, expected.size)
        assertEquals(viewModel.uiState.publications, expected)
    }

    @Test
    fun `test onLoadPublications error`() {
        // Prepare
        val expected = "Error"
        every { getUserPublicationsUseCase(1) } returns flowOf(Result.Error(DomainException("Error")))

        // Execute
        viewModel.onLoadPublications()

        // Assert
        assertEquals(viewModel.uiState.errorMessage, expected)
    }
}