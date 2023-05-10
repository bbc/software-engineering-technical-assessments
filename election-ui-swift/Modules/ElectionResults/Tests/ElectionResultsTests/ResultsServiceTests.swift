
import XCTest
@testable import ElectionResults

class StubResultsRepository: ResultsRepository {

    var invokedLatestResults = false
    var invokedLatestResultsCount = 0
    var stubbedLatestResultsCompletionResult: (Result<ElectionResponse, ResultsRepositoryError>, Void)?

    func latestResults(completion: (Result<ElectionResponse, ResultsRepositoryError>) -> Void) {
        invokedLatestResults = true
        invokedLatestResultsCount += 1
        if let result = stubbedLatestResultsCompletionResult {
            completion(result.0)
        }
    }

    var invokedAllCandidates = false
    var invokedAllCandidatesCount = 0

    func allCandidates(completion: (Result<[Candidate], ResultsRepositoryError>) -> Void) {
        invokedAllCandidates = true
        invokedAllCandidatesCount += 1
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
        stubResultsRepository.stubbedLatestResultsCompletionResult = (.success(mockResults), ())
        let expectation = XCTestExpectation()
        service.latestResults { result in
            if case let .success(response) = result {
                XCTAssertFalse(response.isComplete)
                XCTAssertEqual(response.electionResults.count, 1)
                expectation.fulfill()
            }
        }
        wait(for: [expectation], timeout: 0.1)
    }
}
