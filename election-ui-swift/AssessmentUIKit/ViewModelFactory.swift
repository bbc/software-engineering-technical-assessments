

import Foundation
import ElectionResults

class ViewModelFactory {
    
    private let service: ResultsService
    
    init() {
        service = ResultsServiceFactory.resultsService()
    }
    
    func results() -> ResultsViewModel {
        ResultsViewModelImpl(resultsService: service)
    }
}
