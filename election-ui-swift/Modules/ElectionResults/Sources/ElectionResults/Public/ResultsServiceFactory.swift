
import Foundation

/// Factory to create a new `ResultsService` configured for use.
public class ResultsServiceFactory {
    
    /// Create a new `ResultsService` object
    /// - Returns: the configured `ResultsService` ready for use
    public static func resultsService() -> ResultsService {
        ResultsServiceImpl(resultsRepository: JSONResultsRepository())
    }
}
