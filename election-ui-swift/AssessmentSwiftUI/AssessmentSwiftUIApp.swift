

import SwiftUI

@main
struct AssessmentSwiftUIApp: App {
    var body: some Scene {
        WindowGroup {
            NavigationView {
                ResultsView(presenter: PresenterFactory.results()).navigationBarTitleDisplayMode(.inline)
            }
        }
    }
}
