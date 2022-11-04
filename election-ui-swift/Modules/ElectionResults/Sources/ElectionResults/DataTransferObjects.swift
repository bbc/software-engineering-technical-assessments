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
