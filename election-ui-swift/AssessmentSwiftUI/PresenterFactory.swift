

import Foundation
import ElectionResults

class PresenterFactory {
    static func results() -> ResultsViewPresenter {
        let service = ResultsServiceFactory.resultsService()
        return ElectionResultsViewPresenter(resultsService: service)
    }
}
