
import XCTest
@testable import ElectionResults

class JSONResultsRepositoryTests: XCTestCase {
    
    var repository: JSONResultsRepository!

    override func setUpWithError() throws {
        repository = JSONResultsRepository()
    }

    func testLatestResults() async throws {
        let results = try await repository.latestResults()
        let first = results.results.first!
        XCTAssertFalse(results.isComplete)
        XCTAssertEqual(results.results.count, 3)
        XCTAssertEqual(first.candidateId, 1)
        XCTAssertEqual(first.party, "Adder Party")
        XCTAssertEqual(first.votes, 1056)
    }

    func testIsCompleteTrue() async throws {
        let _ = try await repository.latestResults()
        let _ = try await repository.latestResults()
        let results = try await repository.latestResults()
        XCTAssertTrue(results.isComplete)
        XCTAssertEqual(results.results.count, 3)
    }
    
    func testAllCandidatese() async throws {
        let all = try await repository.allCandidates()
        let first = all.first!
        XCTAssertEqual(all.count, 3)
        XCTAssertEqual(first.id, 1)
        XCTAssertEqual(first.name, "Baldrick")
    }
}
