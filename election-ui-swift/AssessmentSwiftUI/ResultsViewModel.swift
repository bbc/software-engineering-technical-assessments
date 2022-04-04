

import Foundation

class ResultsViewModel: ObservableObject {
    @Published var title: String = ""
    @Published var items: [ResultItemViewModel] = []
}

struct ResultItemViewModel: Identifiable {
    let id = UUID()
    let party: String
    let candidate: String
    let votes: String
}
