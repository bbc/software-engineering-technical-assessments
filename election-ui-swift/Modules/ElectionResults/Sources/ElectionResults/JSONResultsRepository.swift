
import Foundation

struct ResultsDto: Codable {
    let metadata: MetadataDto
    let results: [ResultDto]
}

struct ResultDto: Codable {
    let candidateId: Int
    let party: String
    let votes: Int
}

struct MetadataDto: Codable {
    let isComplete: Bool
}

struct CandidateDto: Codable {
    let id: Int
    let name: String
}

let candidateJSON = """
[
    {
        "id": 1,
        "name": "Baldrick"
    },
    {
        "id": 2,
        "name": "Lord Buckethead"
    },
    {
        "id": 3,
        "name": "Count Binface"
    }
]
"""

let resultsJSON1 = """
{
    "metadata": {
        "isComplete": false
    },
    "results": [
        {
            "party": "Adder Party",
            "candidateId": 1,
            "votes": 1056
        },
        {
            "party": "Independent",
            "candidateId": 2,
            "votes": 6900
        },
        {
            "party": "Independent",
            "candidateId": 3,
            "votes": 9900
        }
    ]
}
"""

let resultsJSON2 = """
{
    "metadata": {
        "isComplete": false
    },
    "results": [
        {
            "party": "Adder Party",
            "candidateId": 1,
            "votes": 1256
        },
        {
            "party": "Independent",
            "candidateId": 2,
            "votes": 7100
        },
        {
            "party": "Independent",
            "candidateId": 3,
            "votes": 9950
        }
    ]
}
"""

let resultsJSON3 = """
{
    "metadata": {
        "isComplete": true
    },
    "results": [
        {
            "party": "Adder Party",
            "candidateId": 1,
            "votes": 1300
        },
        {
            "party": "Independent",
            "candidateId": 2,
            "votes": 7201
        },
        {
            "party": "Independent",
            "candidateId": 3,
            "votes": 10100
        }
    ]
}
"""

class JSONResultsRepository: ResultsRepository {
    
    private var resultsJSON = [resultsJSON1, resultsJSON2, resultsJSON3]
    private var startIndex = 0
    
    func latestResults() async throws -> Results {
        do {
            if let data = resultsJSON[startIndex].data(using: .utf8) {
                let decoder = JSONDecoder()
                let results = try decoder.decode(ResultsDto.self, from: data)
                incrementIndex()
                return adapt(dto: results)
            }
        } catch { }
        throw ResultsRepositoryError.invalidJSON
    }
    
    func allCandidates() async throws -> [Candidate] {
        do {
            if let data = candidateJSON.data(using: .utf8) {
                let decoder = JSONDecoder()
                let result = try decoder.decode([CandidateDto].self, from: data)
                return result.map { Candidate(id: $0.id, name: $0.name) }
            }
        } catch {}
        throw ResultsRepositoryError.invalidJSON
    }
    
    private func incrementIndex() {
        if startIndex < resultsJSON.count - 1 {
            startIndex = startIndex + 1
        }
    }
    
    private func adapt(dto: ResultsDto) -> Results {
        let results = dto.results.map { Result(candidateId: $0.candidateId, party: $0.party, votes: $0.votes) }
        return Results(isComplete: dto.metadata.isComplete, results: results)
    }
}
