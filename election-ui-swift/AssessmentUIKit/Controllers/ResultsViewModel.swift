

import Foundation
import ElectionResults

protocol ResultsViewModelViewDelegate: AnyObject {
    func bind()
}

protocol ResultsViewModel: AnyObject {
    var title: String { get }
    var items: [ResultCellViewModel] { get }
    var viewDelegate: ResultsViewModelViewDelegate? { get set }
    func load()
}

class ResultsViewModelImpl: ResultsViewModel {
    
    weak var viewDelegate: ResultsViewModelViewDelegate?
    var title: String = "Results"
    var items: [ResultCellViewModel] = []
    
    private let resultsService: ResultsService
    
    init(resultsService: ResultsService) {
        self.resultsService = resultsService
    }
    
    func load() {
        resultsService.latestResults { response in
            if case let .success(successfulResponse) = response {
                let itemViewModels = successfulResponse.electionResults.map { result in
                    ResultCellViewModel(party: result.party, candidate: String(result.candidateId), votes: String(result.votes))
                }
                self.items = itemViewModels
                self.viewDelegate?.bind()
            }
        }
    }
}

struct ResultCellViewModel {
    let party: String
    let candidate: String
    let votes: String
}
