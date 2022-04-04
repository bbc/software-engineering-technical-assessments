import UIKit

extension UIView {
    
    // MARK: Public methods
    func embedView(_ view: UIView) {
        view.translatesAutoresizingMaskIntoConstraints = false
        addSubview(view)
        addEmbedConstraints(view: view)
    }

    // MARK: Private methods
    private func addEmbedConstraints(view: UIView) {
        let constraints = [view.leadingAnchor.constraint(equalTo: leadingAnchor),
                           view.trailingAnchor.constraint(equalTo: trailingAnchor),
                           view.topAnchor.constraint(equalTo: topAnchor),
                           view.bottomAnchor.constraint(equalTo: bottomAnchor)]
        
        NSLayoutConstraint.activate(constraints)
    }
}
