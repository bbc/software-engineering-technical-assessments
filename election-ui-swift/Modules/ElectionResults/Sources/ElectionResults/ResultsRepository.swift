
protocol ResultsRepository {
    func latestResults(completion: (Result<ElectionResponse, ResultsRepositoryError>) -> Void)
    func allCandidates() async throws -> [Candidate]
}
