

import SwiftUI

struct ResultsView: View {
    
    @ObservedObject var viewModel: ResultsViewModel
    
    init(viewModel: ResultsViewModel) {
        self.viewModel = viewModel
    }
    
    var body: some View {
        VStack {
            Spacer().frame(height: 8)
            HStack {
                Text("PARTY")
                    .font(.headline)
                    .rowStyle()
                Text("CANDIDATE")
                    .font(.headline)
                    .rowStyle()
                Text("VOTES")
                    .font(.headline)
                    .rowStyle()
            }
            .padding(16)
            .foregroundColor(.white)
            .background(Color.accentColor)
            Spacer().frame(height: 8)
            ForEach(viewModel.items) { viewModel in
                ResultsRow(viewModel: viewModel)
                Spacer().frame(height: 16)
            }
            Spacer()
        }
        .toolbar {
            Button {
                viewModel.refresh()
            } label: {
                Image(systemName: "arrow.clockwise.circle.fill")
            }
        }
        .navigationTitle(viewModel.title)
        .onAppear {
            viewModel.refresh()
        }
    }
}

struct RowStyle: ViewModifier {
    func body(content: Content) -> some View {
        content
            .frame(maxWidth: .infinity, alignment: .leading)
    }
}


extension View {
    func rowStyle() -> some View {
        self.modifier(RowStyle())
    }
}

struct ResultsView_Previews: PreviewProvider {
    static var previews: some View {
        ResultsView(viewModel: ViewModelFactory.resultsViewModel())
    }
}
