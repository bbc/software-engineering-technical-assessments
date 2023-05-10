
import Foundation

class JSONResultsRepository: ResultsRepository {

    private var resultsJSON = [JSONData.resultsJSON1, JSONData.resultsJSON2, JSONData.resultsJSON3]
    private var startIndex = 0

    func latestResults(completion: (Result<ElectionResponse, ResultsRepositoryError>) -> Void) {
        do {
            if let data = resultsJSON[startIndex].data(using: .utf8) {
                let decoder = JSONDecoder()
                let results = try decoder.decode(ResultsDto.self, from: data)
                incrementIndex()
                completion(.success(adapt(dto: results)))
            }
        } catch { }
        completion(.failure(ResultsRepositoryError.invalidJSON))
    }

    func allCandidates(completion: (Result<[Candidate], ResultsRepositoryError>) -> Void) {
        do {
            if let data = JSONData.candidateJSON.data(using: .utf8) {
                let decoder = JSONDecoder()
                let result = try decoder.decode([CandidateDto].self, from: data)
                completion(.success(result.map { Candidate(id: $0.id, name: $0.name) }))
            }
        } catch { }
        completion(.failure(ResultsRepositoryError.invalidJSON))
    }

    private func incrementIndex() {
        if startIndex < resultsJSON.count - 1 {
            startIndex = startIndex + 1
        }
    }

    private func adapt(dto: ResultsDto) -> ElectionResponse {
        let results = dto.results.map { ElectionResult(candidateId: $0.candidateId, party: $0.party, votes: $0.votes) }
        return ElectionResponse(isComplete: dto.metadata.isComplete, electionResults: results)
    }
}
