
protocol ResultsRepository {
    func latestResults() async throws -> Results
    func allCandidates() async throws -> [Candidate]
}
