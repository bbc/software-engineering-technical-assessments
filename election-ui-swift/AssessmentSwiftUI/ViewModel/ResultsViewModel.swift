import Foundation
import ElectionResults

class ResultsViewModel: ObservableObject {
    @Published var title: String = "Results"
    @Published var items: [ResultItemViewModel] = []
    
    private let resultsService: ResultsService
    
    init(resultsService: ResultsService) {
        self.resultsService = resultsService
    }
    
    func refresh() {
        resultsService.latestResults { response in
            if case let .success(successfulResponse) = response {
                let itemViewModels = successfulResponse.electionResults.map { result in
                    ResultItemViewModel(party: result.party, candidate: String(result.candidateId), votes: String(result.votes))
                }
                self.items = itemViewModels
            }
        }
    }
}
