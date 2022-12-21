import XCTest
@testable import AssessmentSwiftUI
@testable import ElectionResults
import Combine

class StubResultsService: ResultsService {
    var completionResult: (Result<ElectionResponse, ResultsServiceError>, Void)?
    func latestResults(completion: @escaping (Result<ElectionResponse, ResultsServiceError>) -> Void) {
        if let result = completionResult {
            completion(result.0)
        }
    }
}

class ResultsViewModelTests: XCTestCase {
    
    var viewModel: ResultsViewModel!
    var stubResultsService: StubResultsService!
    var cancellable: AnyCancellable!

    override func setUpWithError() throws {
        stubResultsService = StubResultsService()
        viewModel = ResultsViewModel(resultsService: stubResultsService)
    }
    
    func testTitle() throws {
        XCTAssertEqual(viewModel.title, "Results")
    }

    func testRefreshItems() throws {
        let mockResult = ElectionResult(candidateId: 1, party: "party", votes: 1)
        let mockResults = ElectionResponse(isComplete: false, electionResults: [mockResult])
        
        stubResultsService.completionResult = (.success(mockResults), ())
        
        viewModel.refresh()
        
        let first = viewModel.items.first!
        XCTAssertEqual(first.party, mockResult.party)
        XCTAssertEqual(first.candidate, String(mockResult.candidateId))
        XCTAssertEqual(first.votes, String(mockResult.votes))
    }
}
