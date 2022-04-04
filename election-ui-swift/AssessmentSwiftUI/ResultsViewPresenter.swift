

import Foundation
import ElectionResults

protocol ResultsViewPresenter {
    var viewModel: ResultsViewModel { get }
    func refresh()
}

class ElectionResultsViewPresenter: ResultsViewPresenter {
    
    var viewModel = ResultsViewModel()
    
    private let resultsService: ResultsService
    
    init(resultsService: ResultsService) {
        self.resultsService = resultsService
        viewModel.title = "Results"
    }
    
    func refresh()  {
        Task {
            let results = try await resultsService.latestResults()
            let viewModels = results.results.map { adapt(result: $0) }
            await MainActor.run {
                viewModel.items = viewModels
            }
        }
    }
    
    private func adapt(result: Result) -> ResultItemViewModel {
        ResultItemViewModel(party: result.party, candidate: String(result.candidateId), votes: String(result.votes))
    }
}
