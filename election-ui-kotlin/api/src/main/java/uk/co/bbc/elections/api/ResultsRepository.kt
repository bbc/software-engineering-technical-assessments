package uk.co.bbc.elections.api

interface ResultsRepository {
    suspend fun latestResults(): Results
    suspend fun allCandidates(): List<Candidate>
}
