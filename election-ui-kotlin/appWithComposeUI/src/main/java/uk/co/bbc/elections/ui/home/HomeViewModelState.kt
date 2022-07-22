package uk.co.bbc.elections.ui.home

import uk.co.bbc.elections.api.Result

data class HomeViewModelState(
    val results: List<Result> = emptyList(),
    val countingComplete: Boolean = false,
    val loading: Boolean = false
) {

    fun toUiState() = HomeUiState(
        results = results.map { result ->
            ResultUiState(
                result.party,
                result.candidateId.toString(),
                result.votes.toString()
            )
        },
        loading = loading
    )
}
