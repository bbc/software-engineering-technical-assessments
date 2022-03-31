

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
        Task {
            let results = try await resultsService.latestResults()
            
            await MainActor.run {
                items = results.results.map { adapt(result: $0) }
                viewDelegate?.bind()
            }
        }
    }
    
    private func adapt(result: Result) -> ResultCellViewModel {
        ResultCellViewModel(party: result.party, candidate: String(result.candidateId), votes: String(result.votes))
    }
}

struct ResultCellViewModel {
    let party: String
    let candidate: String
    let votes: String
}
