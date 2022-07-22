package uk.co.bbc.elections.data

import uk.co.bbc.elections.api.ResultsService

interface ServiceContainer {
    val electionResultsService: ResultsService
}
