import Foundation
import ElectionResults

struct ViewModelFactory {
    static func resultsViewModel() -> ResultsViewModel {
        ResultsViewModel(resultsService: ResultsServiceFactory.resultsService())
    }
}
