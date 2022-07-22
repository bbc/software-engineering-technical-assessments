package uk.co.bbc.elections.ui.home

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import uk.co.bbc.elections.R

@Composable
fun Home(viewModel: HomeViewModel) {
    val uiState by viewModel.uiState.collectAsState()

    Home(uiState) { viewModel.refresh() }
}

@Composable
fun Home(uiState: HomeUiState, refresh: () -> Unit) = Scaffold(
    floatingActionButton = {
        FloatingActionButton(onClick = { if (!uiState.loading) refresh() }) {
            Icon(
                imageVector = Icons.Filled.Refresh,
                contentDescription = stringResource(id = R.string.refresh)
            )
        }
    }
) { innerPadding ->
    LazyColumn(
        Modifier
            .padding(innerPadding)
            .fillMaxSize()
    ) {
        item { ResultHeader() }
        items(uiState.results) { Result(it) }
    }
}

@Preview
@Composable
private fun HomePreview() = Home(
    HomeUiState(
        results = listOf(
            ResultUiState("Adder party", "1", "1056"),
            ResultUiState("b", "2", "100")
        ),
        loading = false
    )
) {}
