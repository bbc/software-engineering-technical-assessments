package uk.co.bbc.elections.data

import uk.co.bbc.elections.api.ResultsService
import uk.co.bbc.elections.api.ResultsServiceFactory

class ElectionsApplicationServiceContainer : ServiceContainer {

    override val electionResultsService: ResultsService by lazy {
        ResultsServiceFactory.buildResultsService()
    }
}
