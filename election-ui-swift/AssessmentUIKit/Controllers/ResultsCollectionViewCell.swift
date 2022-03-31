import UIKit

class ResultsCollectionViewCell: UICollectionViewCell {
    
    private let resultsView: ResultsView = .fromNib()
    
    var viewModel: ResultCellViewModel! {
        didSet {
            bind()
        }
    }
    
    required init?(coder: NSCoder) {
        super.init(coder: coder)
        
        contentView.embedView(resultsView)
    }
    
    func bind() {
        resultsView.partyLabel.text = viewModel.party
        resultsView.candidateLabel.text  = viewModel.candidate
        resultsView.votesLabel.text = viewModel.votes
    }
}
