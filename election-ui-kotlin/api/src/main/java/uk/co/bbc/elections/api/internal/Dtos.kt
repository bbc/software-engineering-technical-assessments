package uk.co.bbc.elections.api.internal

import kotlinx.serialization.Serializable

/*
 * These internal models are only for JSONResultsRepository
 * and should not be referenced or modified.
 */

@Serializable
internal data class ResultsDto(
    val metadata: MetadataDto,
    val results: List<ResultDto>
)

@Serializable
internal data class MetadataDto(
    val isComplete: Boolean
)

@Serializable
internal data class ResultDto(
    val candidateId: Int,
    val party: String,
    val votes: Int
)

@Serializable
internal data class CandidateDto(
    val id: Int,
    val name: String
)