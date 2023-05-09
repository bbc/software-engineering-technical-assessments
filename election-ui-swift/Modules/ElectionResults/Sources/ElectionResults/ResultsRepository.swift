
protocol ResultsRepository {
    func latestResults(completion: (Result<ElectionResponse, ResultsRepositoryError>) -> Void)
    func allCandidates(completion: (Result<[Candidate], ResultsRepositoryError>) -> Void)
}
