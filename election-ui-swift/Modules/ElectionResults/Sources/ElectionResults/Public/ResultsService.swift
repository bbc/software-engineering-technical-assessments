
import Foundation

/// Provides up-to-date election results. For more information on creating a `ResultsService` instance, see `ResultsServiceFactory`
public protocol ResultsService {
    
    /// Fetch the latest results
    /// - Returns: A results object containing the latest election results data.
    /// - Throws: A `ResultsRepositoryError` if there was an error retrieving or parsing the results data from the back end.
    func latestResults() async throws -> Results
}

