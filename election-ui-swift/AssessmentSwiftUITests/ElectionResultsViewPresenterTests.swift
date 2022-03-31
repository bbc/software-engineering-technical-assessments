

import XCTest
@testable import AssessmentSwiftUI
@testable import ElectionResults
import Combine

class StubResultsService: ResultsService {

    var stubbedLatestResultsResult: Results!

    func latestResults() async throws -> Results {
        return stubbedLatestResultsResult
    }
}

class ElectionResultsViewPresenterTests: XCTestCase {
    
    var presenter: ElectionResultsViewPresenter!
    var stubResultsService: StubResultsService!
    var cancellable: AnyCancellable!
    var viewModel: ResultsViewModel {
        presenter.viewModel
    }

    override func setUpWithError() throws {
        stubResultsService = StubResultsService()
        presenter = ElectionResultsViewPresenter(resultsService: stubResultsService)
    }
    
    private func registerViewModelDidChangeHandler(objectDidChange: @escaping (ResultsViewModel) -> Void) {
        cancellable = viewModel.$items
            .delay(for: .nanoseconds(1), scheduler: RunLoop.main)
            .dropFirst()
            .sink { [weak self] _ in
                guard let self = self else { return }
                objectDidChange(self.viewModel)
            }
    }

    func testTitle() throws {
        XCTAssertEqual(viewModel.title, "Results")
    }

    func testRefreshItems() throws {
        
        let mockResult = Result(candidateId: 1, party: "party", votes: 1)
        let mockResults = Results(isComplete: false, results: [mockResult])
        stubResultsService.stubbedLatestResultsResult = mockResults
        
        let expectation = expectation(description: "should refresh")
        
        registerViewModelDidChangeHandler { viewModel in
            expectation.fulfill()
        }
        
        presenter.refresh()
        wait(for: [expectation], timeout: 0.3)
        
        let first = viewModel.items.first!
        XCTAssertEqual(first.party, mockResult.party)
        XCTAssertEqual(first.candidate, String(mockResult.candidateId))
        XCTAssertEqual(first.votes, String(mockResult.votes))
    }
}
