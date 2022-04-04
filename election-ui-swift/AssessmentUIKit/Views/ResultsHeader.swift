import UIKit

class ResultsHeader: UICollectionReusableView {
    private let resultsView: ResultsView = .fromNib()
    
    override init(frame: CGRect) {
        super.init(frame: frame)
        
        resultsView.backgroundColor = UIColor.primary
        resultsView.partyLabel.textColor = UIColor.white
        resultsView.candidateLabel.textColor = UIColor.white
        resultsView.votesLabel.textColor = UIColor.white
        resultsView.partyLabel.font = UIFont.boldSystemFont(ofSize: 18)
        resultsView.candidateLabel.font = UIFont.boldSystemFont(ofSize: 18)
        resultsView.votesLabel.font = UIFont.boldSystemFont(ofSize: 18)
        resultsView.partyLabel.text = "Party"
        resultsView.candidateLabel.text = "Candidate"
        resultsView.votesLabel.text = "Votes"
        embedView(resultsView)
    }
    
    required init?(coder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }
}
