package uk.co.bbc.elections.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import uk.co.bbc.elections.R

@Composable
fun ResultHeader() = Row(modifier = Modifier.background(MaterialTheme.colors.primary)) {
    Text(
        modifier = Modifier.weight(1f),
        text = stringResource(id = R.string.results_header_party).uppercase()
    )
    Text(
        modifier = Modifier.weight(1f),
        text = stringResource(id = R.string.results_header_candidate).uppercase()
    )
    Text(
        modifier = Modifier.weight(1f),
        text = stringResource(id = R.string.results_header_votes).uppercase()
    )
}

@Preview
@Composable
private fun ResultHeaderPreview() = ResultHeader()
