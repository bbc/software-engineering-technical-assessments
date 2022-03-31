
class ResultsServiceImpl: ResultsService {

    private var resultsRepository: ResultsRepository
    
    init(resultsRepository: ResultsRepository) {
        self.resultsRepository = resultsRepository
    }
    
    func latestResults() async throws -> Results {
        let results = try await resultsRepository.latestResults()
        return results
    }
}
