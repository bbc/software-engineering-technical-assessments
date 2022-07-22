package uk.co.bbc.elections.ui

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import uk.co.bbc.elections.data.ServiceContainer
import uk.co.bbc.elections.ui.home.Home
import uk.co.bbc.elections.ui.home.HomeViewModel
import uk.co.bbc.elections.ui.theme.ElectionsAssessmentTheme

@Composable
fun App(serviceContainer: ServiceContainer) {
    ElectionsAssessmentTheme {
        Home(viewModel(factory = HomeViewModel.Factory(serviceContainer)))
    }
}
