

import XCTest
@testable import AssessmentUIKit
@testable import ElectionResults

class StubResultsService: ResultsService {
    var completion: ((Result<ElectionResults.ElectionResponse, ElectionResults.ResultsRepositoryError>) -> Void)?
    func latestResults(completion:  @escaping (Result<ElectionResults.ElectionResponse, ElectionResults.ResultsRepositoryError>) -> Void) {
        self.completion = completion
    }
}

class StubResultsViewModelViewDelegate: ResultsViewModelViewDelegate {

    var invokedBind = false
    var invokedBindCount = 0
    var bindWasCalled: (() -> Void)?

    func bind() {
        invokedBind = true
        invokedBindCount += 1
        bindWasCalled?()
    }
}

class ResultsViewModelImplTests: XCTestCase {
    
    var viewModel: ResultsViewModelImpl!
    var stubResultsService: StubResultsService!
    var stubDelegate: StubResultsViewModelViewDelegate!
    
    override func setUpWithError() throws {
        stubResultsService = StubResultsService()
        stubDelegate = StubResultsViewModelViewDelegate()
        viewModel = ResultsViewModelImpl(resultsService: stubResultsService)
        viewModel.viewDelegate = stubDelegate
    }
    
    func testTitle() throws {
        XCTAssertEqual(viewModel.title, "Results")
    }

    func testLoad() throws {
        let mockResult = ElectionResult(candidateId: 1, party: "party", votes: 1)
        let mockResults = ElectionResponse(isComplete: false, electionResults: [mockResult])

        stubDelegate.bindWasCalled = {
            let first = self.viewModel.items.first!
            XCTAssertEqual(first.party, mockResult.party)
            XCTAssertEqual(first.candidate, String(mockResult.candidateId))
            XCTAssertEqual(first.votes, String(mockResult.votes))
        }
        viewModel.load()
        stubResultsService.completion!(.success(mockResults))
    }


}
