package uk.co.bbc.elections.ui.results

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import uk.co.bbc.elections.api.ResultsService
import uk.co.bbc.elections.api.ResultsServiceFactory

class ResultsViewModel(
    private val resultsService: ResultsService = ResultsServiceFactory.buildResultsService()
) : ViewModel() {

    // Backing property for internal mutable state.
    private val _viewState = MutableStateFlow<ResultsViewState>(ResultsViewState.Loading)

    // Public immutable state, observed from fragment.
    val viewState: Flow<ResultsViewState> = _viewState

    init {
        refresh()
    }

    fun refresh() {
        viewModelScope.launch {
            _viewState.emit(ResultsViewState.Loading)

            val fetchedResults = resultsService.latestResults()
            val nextState = ResultsViewState.Loaded.fromResults(fetchedResults)

            _viewState.emit(nextState)
        }
    }
}