import Foundation

struct ResultItemViewModel: Identifiable {
    let id = UUID()
    let party: String
    let candidate: String
    let votes: String
}
