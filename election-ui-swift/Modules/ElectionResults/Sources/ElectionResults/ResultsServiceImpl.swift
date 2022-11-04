
class ResultsServiceImpl: ResultsService {

    private var resultsRepository: ResultsRepository
    
    init(resultsRepository: ResultsRepository) {
        self.resultsRepository = resultsRepository
    }
    
    func latestResults(completion: @escaping (Result<ElectionResponse, ResultsServiceError>) -> Void) {
        resultsRepository.latestResults { result in
            switch result {
            case .success(let response):
                completion(.success(response))
            case .failure:
                completion(.failure(.invalidJSON))
            }
        }
    }
}
