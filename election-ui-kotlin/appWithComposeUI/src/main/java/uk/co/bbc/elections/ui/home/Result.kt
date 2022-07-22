package uk.co.bbc.elections.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun Result(result: ResultUiState) = Row(
    modifier = Modifier.background(MaterialTheme.colors.surface)
) {
    Text(
        modifier = Modifier.weight(1f),
        text = result.party,
        color = MaterialTheme.colors.onSurface
    )
    Text(
        modifier = Modifier.weight(1f),
        text = result.id,
        color = MaterialTheme.colors.onSurface
    )
    Text(
        modifier = Modifier.weight(1f),
        text = result.votes,
        color = MaterialTheme.colors.onSurface
    )
}

@Preview
@Composable
private fun ResultPreview() = Result(ResultUiState("Adder party", "1", "1056"))
