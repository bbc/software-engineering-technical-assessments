package uk.co.bbc.elections.ui.results

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import uk.co.bbc.elections.MainDispatcherRule
import uk.co.bbc.elections.api.Result
import uk.co.bbc.elections.api.Results
import uk.co.bbc.elections.api.ResultsService
import uk.co.bbc.elections.ui.results.ResultsViewState.Loaded.ResultsItemViewData
import kotlin.time.Duration

class ResultsViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    // test doubles
    private lateinit var stubResultsService: StubResultsService

    @Before
    fun setUp() {
        stubResultsService = StubResultsService()
    }

    @Test
    fun initialState_isLoading() = runTest {
        val viewModel = ResultsViewModel(stubResultsService)

        val actualState = viewModel.viewState.first()

        Assert.assertEquals(ResultsViewState.Loading, actualState)
    }

    @Test
    fun givenServiceWithData_whenViewModelInitialised_thenViewStateMovesToLoaded() =
        runTest(mainDispatcherRule.testDispatcher) {
            stubResultsService.latestResultsStubValues = listOf(
                Results(
                    isComplete = false,
                    results = listOf(Result(1, "Party1", 123))
                )
            )

            val viewModel = ResultsViewModel(stubResultsService)

            // initial state
            val s1 = viewModel.viewState.first()

            // state after initial fetch
            advanceUntilIdle()
            val s2 = viewModel.viewState.first()

            val actualStates = listOf(s1, s2)

            val expectedLoadedState =
                ResultsViewState.Loaded(
                    listOf(
                        ResultsItemViewData("Party1", "1", 123)
                    )
                )
            val expectedStates = listOf(ResultsViewState.Loading, expectedLoadedState)

            Assert.assertEquals(expectedStates, actualStates)
        }

    @Test
    fun givenServiceWithChangingData_whenRefresh_thenViewStateChanges() =
        runTest(mainDispatcherRule.testDispatcher) {
            stubResultsService.latestResultsStubValues = listOf(
                Results(
                    isComplete = false,
                    results = listOf(Result(1, "Party1", 123))
                ),
                Results(
                    isComplete = false,
                    results = listOf(
                        Result(1, "Party1", 123),
                        Result(2, "Party2", 456)
                    )
                )
            )

            val viewModel = ResultsViewModel(stubResultsService)

            // initial state
            val s1 = viewModel.viewState.first()

            // state after initial fetch
            advanceUntilIdle()
            val s2 = viewModel.viewState.first()

            viewModel.refresh()

            // state after tapping refresh
            val s3 = viewModel.viewState.first()

            // state after refresh completes
            advanceUntilIdle()
            val s4 = viewModel.viewState.first()

            val actualStates = listOf(s1, s2, s3, s4)

            val expectedLoadedState1 =
                ResultsViewState.Loaded(
                    listOf(
                        ResultsItemViewData("Party1", "1", 123)
                    )
                )
            val expectedLoadedState2 =
                ResultsViewState.Loaded(
                    listOf(
                        ResultsItemViewData("Party1", "1", 123),
                        ResultsItemViewData("Party2", "2", 456),
                    )
                )

            val expectedStates = listOf(
                ResultsViewState.Loading,
                expectedLoadedState1,
                ResultsViewState.Loading,
                expectedLoadedState2
            )

            Assert.assertEquals(expectedStates, actualStates)
        }
}

class StubResultsService : ResultsService {
    var latestResultsInvocationCount = 0

    // sequence of stub values that latestResults() should return
    var latestResultsStubValues: List<Results> = emptyList()

    override suspend fun latestResults(): Results {
        val stubValueForInvocation =
            latestResultsStubValues.getOrNull(latestResultsInvocationCount)

        val results = if (stubValueForInvocation != null) {
            delay(10)
            stubValueForInvocation
        } else {
            // If no stub exists for the current invocation, simulate waiting forever
            delay(Duration.INFINITE)
            error("Unreachable")
        }

        latestResultsInvocationCount += 1

        return results
    }
}
