
import XCTest
@testable import ElectionResults

class JSONResultsRepositoryTests: XCTestCase {

    var repository: JSONResultsRepository!

    override func setUpWithError() throws {
        repository = JSONResultsRepository()
    }

    func testLatestResults() async throws {
        let expectation = XCTestExpectation()
        repository.latestResults { result in
            if case let .success(response) = result {
                let first = response.electionResults.first!
                XCTAssertFalse(response.isComplete)
                XCTAssertEqual(response.electionResults.count, 3)
                XCTAssertEqual(first.candidateId, 1)
                XCTAssertEqual(first.party, "Adder Party")
                XCTAssertEqual(first.votes, 1056)
                expectation.fulfill()
            }
        }
        wait(for: [expectation], timeout: 0.1)
    }

    func testIsCompleteTrue() async throws {
        let expectation = XCTestExpectation()

        repository.latestResults { _ in }
        repository.latestResults { _ in }
        repository.latestResults { result in
            if case let .success(response) = result {
                XCTAssertTrue(response.isComplete)
                XCTAssertEqual(response.electionResults.count, 3)
                expectation.fulfill()
            }
        }
        wait(for: [expectation], timeout: 0.1)
    }

    func testAllCandidates() async throws {
        let expectation = XCTestExpectation()

        repository.allCandidates { result in
            if case let .success(response) = result {
                let first = response.first!
                XCTAssertEqual(response.count, 3)
                XCTAssertEqual(first.id, 1)
                XCTAssertEqual(first.name, "Baldrick")
                expectation.fulfill()
            }
        }
        wait(for: [expectation], timeout: 0.1)
    }
}
