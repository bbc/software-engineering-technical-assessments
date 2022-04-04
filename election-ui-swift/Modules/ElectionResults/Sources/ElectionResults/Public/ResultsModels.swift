
import Foundation

/// Data object containing a complete results summary
public struct Results {
    /// Boolean value to indicate the current state of the election. When `isComplete` is set to true, no more votes will be added to the tallies and a winner can be called.
    public let isComplete: Bool
    /// An array of election candidates and their current standings
    public let results: [Result]
}

/// Data object encapsulating an individual candidate's current result
public struct Result {
    /// The integer identifier for this candidate. This value is a unique internal identifier for this specific candidate.
    public let candidateId: Int
    /// The name of the party this candidate is standing for, or 'Independent' if the candidate is not a member of a politicial party
    public let party: String
    /// The current count of votes for this candidate.
    public let votes: Int
}

/// Error descriptor used when fetching election results
public enum ResultsRepositoryError: Error {
    /// Represents an error decoding the JSON data retrieved by the repository. The JSON data may be malformed, or doesn't match the expected structure.
    case invalidJSON
}

/// Data object encaspulating candidate metadata
public struct Candidate {
    /// The integer identifier for this candidate. This value is a unique internal identifier for this specific candidate.
    public let id: Int
    /// The candidate's full given name in human-readable form
    public let name: String
}
