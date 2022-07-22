package uk.co.bbc.elections.ui.home

data class HomeUiState(
    val results: List<ResultUiState>,
    val loading: Boolean
)
