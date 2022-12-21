

import SwiftUI

struct ResultsRow: View {
    
    private let viewModel: ResultItemViewModel
    
    init(viewModel: ResultItemViewModel) {
        self.viewModel = viewModel
    }
    
    var body: some View {
        HStack {
            Text(viewModel.party)
                .font(.body)
                .rowStyle()
            Text(viewModel.candidate)
                .font(.body)
                .rowStyle()
            Text(viewModel.votes)
                .font(.body)
                .rowStyle()
        }.padding(EdgeInsets(top: 8, leading: 16, bottom: 8, trailing: 16))
    }
}

struct ResultsRow_Previews: PreviewProvider {
    static var previews: some View {
        ResultsRow(viewModel: PreviewData.resultItemViewModel())
    }
}
