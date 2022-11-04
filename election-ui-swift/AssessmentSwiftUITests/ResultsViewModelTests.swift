import XCTest
@testable import AssessmentSwiftUI
@testable import ElectionResults
import Combine

class StubResultsService: ResultsService {
    var completion: ((Result<ElectionResults.ElectionResponse, ElectionResults.ResultsRepositoryError>) -> Void)?
    func latestResults(completion:  @escaping (Result<ElectionResults.ElectionResponse, ElectionResults.ResultsRepositoryError>) -> Void) {
        self.completion = completion
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
        
        stubResultsService.completion!(.success(mockResults))
        
        viewModel.refresh()
        
        let first = viewModel.items.first!
        XCTAssertEqual(first.party, mockResult.party)
        XCTAssertEqual(first.candidate, String(mockResult.candidateId))
        XCTAssertEqual(first.votes, String(mockResult.votes))
    }
}
