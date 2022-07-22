package uk.co.bbc.elections.ui.home

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runCurrent
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import uk.co.bbc.elections.MainCoroutineRule
import uk.co.bbc.elections.api.Result
import uk.co.bbc.elections.api.Results

@ExperimentalCoroutinesApi
class HomeViewModelTest {

    @get:Rule
    val coroutineRule = MainCoroutineRule()

    @Test
    fun `initial UI state is loading`() {
        // When
        val viewModel = HomeViewModel(StubResultsService())

        // Then
        assertTrue(viewModel.uiState.value.loading)
    }

    @Test
    fun `result list in UI state is initially empty`() {
        // When
        val viewModel = HomeViewModel(StubResultsService())

        // Then
        assertEquals(emptyList<ResultUiState>(), viewModel.uiState.value.results)
    }

    @Test
    fun `when service returns a response, state moves to loaded`() = runTest {
        // Given
        val stubResultsService = StubResultsService()

        // When
        val viewModel = HomeViewModel(stubResultsService)
        runCurrent()

        stubResultsService.dispatchResults()
        runCurrent()

        // Then
        assertFalse(viewModel.uiState.value.loading)
    }

    @Test
    fun `when service returns a response, ui state contains results`() = runTest {
        // Given
        val stubResultsService = StubResultsService()

        // When
        val viewModel = HomeViewModel(stubResultsService)
        runCurrent()

        stubResultsService.dispatchResults(
            Results(
                false,
                listOf(
                    Result(0, "Party0", 123),
                    Result(1, "Party1", 234)
                )
            )
        )
        runCurrent()

        // Then
        assertEquals(
            listOf(
                ResultUiState("Party0", "0", "123"),
                ResultUiState("Party1", "1", "234")
            ),
            viewModel.uiState.value.results
        )
    }

    @Test
    fun `when refresh called, move to loading state`() = runTest {
        // Given
        val stubResultsService = StubResultsService()
        val viewModel = HomeViewModel(stubResultsService)
        runCurrent()

        stubResultsService.dispatchResults()
        runCurrent()

        assertFalse(viewModel.uiState.value.loading)

        // When
        viewModel.refresh()
        runCurrent()

        // Then
        assertTrue(viewModel.uiState.value.loading)
    }
}
