
import XCTest
@testable import ElectionResults

class StubResultsRepository: ResultsRepository {

    var invokedLatestResults = false
    var invokedLatestResultsCount = 0
    var stubbedLatestResultsResult: ElectionResponse!

    func latestResults() async throws -> ElectionResponse {
        invokedLatestResults = true
        invokedLatestResultsCount += 1
        return stubbedLatestResultsResult
    }
    
    var invokedAllCandidates = false
    var invokedAllCandidatesCount = 0
    var stubbedAllCandidatesResult:  [Candidate]!
    
    func allCandidates() async throws -> [Candidate] {
        invokedAllCandidates = true
        invokedAllCandidatesCount += 1
        return stubbedAllCandidatesResult
    }
}

class ResultsServiceTests: XCTestCase {
    
    var service: ResultsServiceImpl!
    var stubResultsRepository: StubResultsRepository!

    override func setUpWithError() throws {
        stubResultsRepository = StubResultsRepository()
        service = ResultsServiceImpl(resultsRepository: stubResultsRepository)
    }

    func testLatestResults() async throws {
        let mockResult = ElectionResult(candidateId: 1, party: "party", votes: 1)
        let mockResults = ElectionResponse(isComplete: false, electionResults: [mockResult])
        stubResultsRepository.stubbedLatestResultsResult = mockResults
        let results = try await service.latestResults()
        XCTAssertFalse(results.isComplete)
        XCTAssertEqual(results.electionResults.count, 1)
    }
}
