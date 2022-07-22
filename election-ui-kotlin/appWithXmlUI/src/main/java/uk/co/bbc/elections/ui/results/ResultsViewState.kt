package uk.co.bbc.elections.ui.results

import uk.co.bbc.elections.api.Results

sealed class ResultsViewState {
    object Loading : ResultsViewState()

    data class Loaded(val results: List<ResultsItemViewData>) : ResultsViewState() {
        data class ResultsItemViewData(
            val party: String,
            val candidate: String,
            val votes: Int
        )

        companion object {
            fun fromResults(results: Results): Loaded {
                val resultsItems = results.results.map {
                    ResultsItemViewData(
                        it.party,
                        it.candidateId.toString(),
                        it.votes
                    )
                }

                return Loaded(resultsItems)
            }
        }
    }
}