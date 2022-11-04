
import Foundation

/// Error descriptor used when fetching election results
public enum ResultsServiceError: Error {
    /// Represents an error decoding the JSON data retrieved by the service. The JSON data may be malformed, or doesn't match the expected structure.
    case invalidJSON
}

/// Provides up-to-date election results. For more information on creating a `ResultsService` instance, see `ResultsServiceFactory`
public protocol ResultsService {
    
    /// Fetch the latest results
    /// - Parameter completion: block called when a result has been retrieved containing the latest election results data.
    func latestResults(completion: @escaping (Result<ElectionResponse, ResultsServiceError>) -> Void)
}

