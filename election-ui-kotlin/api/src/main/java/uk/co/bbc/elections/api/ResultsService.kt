package uk.co.bbc.elections.api

/**
 * Provides up-to-date election results.
 * For more information on creating a `ResultsService` instance, see `ResultsServiceFactory`
 */
interface ResultsService {
    /**
     * Fetch the latest results.
     *
     *
     * @return A results object containing the latest election results data.
     *
     * @throws ResultsRepositoryException if there was an error retrieving or
     *                                    parsing the results data from the back end.
     */
    @Throws(ResultsRepositoryException::class)
    suspend fun latestResults(): Results

}

