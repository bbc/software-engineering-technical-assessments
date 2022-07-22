package uk.co.bbc.elections.ui.home

import kotlinx.coroutines.CompletableDeferred
import uk.co.bbc.elections.api.Results
import uk.co.bbc.elections.api.ResultsService

class StubResultsService : ResultsService {

    private var _latestResults: CompletableDeferred<Results> = CompletableDeferred()

    fun dispatchResults(results: Results = Results(false, emptyList())) {
        _latestResults.complete(results)
        _latestResults = CompletableDeferred()
    }

    override suspend fun latestResults() = _latestResults.await()
}
