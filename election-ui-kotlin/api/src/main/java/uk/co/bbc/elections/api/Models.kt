package uk.co.bbc.elections.api

/**
 * Data object containing a complete results summary.
 *
 * @property isComplete Boolean value to indicate the current state of the election.
 *                      When `isComplete` is set to true, no more votes will be added
 *                      to the tallies and a winner can be called.
 *
 * @property results    A list of election candidates and their current standings.
 */
data class Results(
    val isComplete: Boolean,
    val results: List<Result>
)

/**
 * Data object encapsulating an individual candidate's current result.
 *
 * @property candidateId The integer identifier for this candidate.
 *                       This value is a unique internal identifier for this specific candidate.
 *
 * @property party       The name of the party this candidate is standing for,
 *                       or 'Independent' if the candidate is not a member of a political party.
 *
 * @property votes       The current count of votes for this candidate.
 */
data class Result(
    val candidateId: Int,
    val party: String,
    val votes: Int
)

/**
 * Error descriptor used when fetching election results.
 */
sealed class ResultsRepositoryException : Exception() {
    /**
     * Represents an error decoding the JSON data retrieved by the repository.
     * The JSON data may be malformed, or doesn't match the expected structure.
     */
    object InvalidJSON : ResultsRepositoryException()
}

/**
 * Data object encapsulating candidate metadata
 *
 * @property id   The integer identifier for this candidate.
 *                This value is a unique internal identifier for this specific candidate.
 *
 * @property name The candidate's full given name in human-readable form.
 */
data class Candidate(
    val id: Int,
    val name: String
)