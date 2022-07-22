package uk.co.bbc.elections.api

import uk.co.bbc.elections.api.internal.JSONResultsRepository

/**
 * Factory to create a new ResultsService ready for use.
 */
class ResultsServiceFactory {
    companion object {
        /**
         * Build a new ResultsService.
         *
         * @return the configured ResultsService ready for use.
         */
        fun buildResultsService(): ResultsService {
            return ResultsServiceImpl(JSONResultsRepository())
        }
    }
}