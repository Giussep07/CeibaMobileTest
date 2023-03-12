package com.giussepr.ceiba.presentation.home

import com.giussepr.ceiba.domain.model.DomainException
import com.giussepr.ceiba.domain.model.Result
import com.giussepr.ceiba.domain.model.User
import com.giussepr.ceiba.domain.usecase.GetUsersUseCase
import com.giussepr.ceiba.ui.presentation.screens.home.HomeViewModel
import com.giussepr.ceiba.utils.DispatcherProvider
import com.giussepr.ceiba.utils.TestDispatcherProvider
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class HomeViewModelTest {

    private lateinit var homeViewModel: HomeViewModel
    private lateinit var dispatcherProvider: DispatcherProvider

    private val getUsersUseCase: GetUsersUseCase = mockk()

    @Before
    fun setUp() {
        dispatcherProvider = TestDispatcherProvider()

        homeViewModel = HomeViewModel(
            getUsersUseCase = getUsersUseCase,
            dispatcherProvider = dispatcherProvider
        )

        Dispatchers.setMain(dispatcherProvider.main)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `test onLoadUsers() success`() {
        runTest {
            // Prepare
            val expected = buildList {
                add(User(id = 1, name = "name", email = "email", phone = "phone"))
                add(User(id = 2, name = "name", email = "email", phone = "phone"))
                add(User(id = 3, name = "name", email = "email", phone = "phone"))
                add(User(id = 4, name = "name", email = "email", phone = "phone"))
            }

            every { getUsersUseCase() } returns flowOf(Result.Success(expected))

            // Execute
            homeViewModel.onLoadUsers()

            // Verify
            assertEquals(homeViewModel.uiState.users, expected)
        }
    }

    @Test
    fun `test onLoadUsers() error`() {
        runTest {
            // Prepare
            val expected = "Error"

            every { getUsersUseCase() } returns flowOf(Result.Error(DomainException("Error")))

            // Execute
            homeViewModel.onLoadUsers()

            // Verify
            assertEquals(homeViewModel.uiState.errorMessage, expected)
        }
    }

    @Test
    fun `test onSearchTermChanged() has results`() {
        runTest {
            // Prepare
            val expected = buildList {
                add(User(id = 1, name = "Leanne Graham", email = "email", phone = "phone"))
                add(User(id = 2, name = "Ervin Howell", email = "email", phone = "phone"))
                add(User(id = 3, name = "Clementine Bauch", email = "email", phone = "phone"))
                add(User(id = 4, name = "Patricia Lebsack", email = "email", phone = "phone"))
            }

            every { getUsersUseCase() } returns flowOf(Result.Success(expected))
            homeViewModel.onLoadUsers()

            // Execute
            homeViewModel.onSearchTermChanged("cle")

            // Verify
            assertEquals(homeViewModel.uiState.users.size, 1)
        }
    }

    @Test
    fun `test onSearchTermChanged() no results`() {
        runTest {
            // Prepare
            val expected = buildList {
                add(User(id = 1, name = "Leanne Graham", email = "email", phone = "phone"))
                add(User(id = 2, name = "Ervin Howell", email = "email", phone = "phone"))
                add(User(id = 3, name = "Clementine Bauch", email = "email", phone = "phone"))
                add(User(id = 4, name = "Patricia Lebsack", email = "email", phone = "phone"))
            }

            every { getUsersUseCase() } returns flowOf(Result.Success(expected))
            homeViewModel.onLoadUsers()

            // Execute
            homeViewModel.onSearchTermChanged("Giussep")

            // Verify
            assertEquals(homeViewModel.uiState.users.size, 0)
        }
    }

}