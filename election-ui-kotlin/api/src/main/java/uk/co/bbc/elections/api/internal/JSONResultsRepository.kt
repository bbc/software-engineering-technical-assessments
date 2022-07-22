package uk.co.bbc.elections.api.internal

import kotlinx.coroutines.delay
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import uk.co.bbc.elections.api.Candidate
import uk.co.bbc.elections.api.Result
import uk.co.bbc.elections.api.Results
import uk.co.bbc.elections.api.ResultsRepository
import kotlin.random.Random
import kotlin.random.nextInt

/*
 * This implementation of ResultsRepository is provided as a working data source
 * and should not be referenced or modified.
 */

internal class JSONResultsRepository : ResultsRepository {

    private var resultsFetchedCount = 0

    override suspend fun latestResults(): Results {
        resultsFetchedCount++

        simulateNetworkDelay()

        return when (resultsFetchedCount) {
            1 -> RESULTS_1
            2 -> RESULTS_2
            else -> RESULTS_3
        }
    }

    override suspend fun allCandidates(): List<Candidate> {
        simulateNetworkDelay()

        return CANDIDATES
    }

    private suspend fun simulateNetworkDelay() {
        val simulatedCallTimeMs = 1000L + Random.nextLong(-500L, 500L)
        delay(simulatedCallTimeMs)
    }

    private companion object {
        private val CANDIDATES: List<Candidate> =
            Json.decodeFromString<List<CandidateDto>>(JsonStrings.candidatesJson)
                .let(::makeCandidates)

        private val RESULTS_1: Results =
            Json.decodeFromString<ResultsDto>(JsonStrings.results1Json)
                .let(::makeResults)

        private val RESULTS_2: Results =
            Json.decodeFromString<ResultsDto>(JsonStrings.results2Json)
                .let(::makeResults)

        private val RESULTS_3: Results =
            Json.decodeFromString<ResultsDto>(JsonStrings.results3Json)
                .let(::makeResults)


        private fun makeResults(dto: ResultsDto): Results {
            return Results(
                dto.metadata.isComplete,
                dto.results.map(::makeResult)
            )
        }

        private fun makeResult(dto: ResultDto): Result {
            return Result(dto.candidateId, dto.party, dto.votes)
        }

        private fun makeCandidates(dtos: List<CandidateDto>): List<Candidate> {
            return dtos.map { dto ->
                Candidate(dto.id, dto.name)
            }
        }
    }
}

