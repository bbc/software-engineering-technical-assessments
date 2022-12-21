import SwiftUI

@main
struct AssessmentSwiftUIApp: App {
    var body: some Scene {
        WindowGroup {
            NavigationView {
                ResultsView(viewModel: ViewModelFactory.resultsViewModel()).navigationBarTitleDisplayMode(.inline)
            }
        }
    }
}
