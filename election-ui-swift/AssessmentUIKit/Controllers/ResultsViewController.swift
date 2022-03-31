import UIKit

class ResultsViewController: UICollectionViewController {
    
    private let viewModel: ResultsViewModel
    private let reuseIdentifier = "Cell"
    private let headerReuseIdentifier = "Header"
    
    required init?(coder: NSCoder) {
        
        viewModel = ViewModelFactory().results()
        super.init(coder: coder)
        
        title = viewModel.title
        viewModel.viewDelegate = self
    }
    
    override func viewWillAppear(_ animated: Bool) {
        super.viewWillAppear(animated)
        
        viewModel.load()
    }
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        if let flowLayout = collectionView?.collectionViewLayout as? UICollectionViewFlowLayout {
            flowLayout.estimatedItemSize = .zero
            flowLayout.headerReferenceSize = CGSize(width: collectionView.frame.size.width, height: 80)
        }
        configureRefreshButton()
        collectionView.register(UINib(nibName: String(describing: ResultsCollectionViewCell.self), bundle: nil), forCellWithReuseIdentifier: reuseIdentifier)
        collectionView.register(ResultsHeader.self, forSupplementaryViewOfKind: UICollectionView.elementKindSectionHeader, withReuseIdentifier: headerReuseIdentifier)
    }
    
    override func numberOfSections(in collectionView: UICollectionView) -> Int {
        return 1
    }
    
    override func collectionView(_ collectionView: UICollectionView, numberOfItemsInSection section: Int) -> Int {
        return viewModel.items.count
    }
    
    override func collectionView(_ collectionView: UICollectionView, cellForItemAt indexPath: IndexPath) -> UICollectionViewCell {
        let cell = collectionView.dequeueReusableCell(withReuseIdentifier: reuseIdentifier, for: indexPath) as! ResultsCollectionViewCell
        cell.viewModel = viewModel.items[indexPath.item]
        return cell
    }
    
    override func collectionView(_ collectionView: UICollectionView, viewForSupplementaryElementOfKind kind: String, at indexPath: IndexPath) -> UICollectionReusableView {
        let headerView = collectionView.dequeueReusableSupplementaryView(ofKind: UICollectionView.elementKindSectionHeader, withReuseIdentifier: headerReuseIdentifier, for: indexPath)

         headerView.frame.size.height = 80

         return headerView
    }
    
    override func viewWillTransition(to size: CGSize, with coordinator: UIViewControllerTransitionCoordinator) {
        super.viewWillTransition(to: size, with: coordinator)
        collectionView.collectionViewLayout.invalidateLayout()
    }
    
    @objc func resultsTapped() {
        viewModel.load()
    }
    
    private func configureRefreshButton() {
        let config = UIImage.SymbolConfiguration(scale: .large)
        let image = UIImage(systemName: "arrow.clockwise.circle.fill", withConfiguration: config)
        let button = UIBarButtonItem(image: image, style: .plain, target: self, action: #selector(resultsTapped))
        self.navigationItem.rightBarButtonItem = button
    }
    
}

extension ResultsViewController: UICollectionViewDelegateFlowLayout {
    func collectionView(_ collectionView: UICollectionView, layout collectionViewLayout: UICollectionViewLayout, sizeForItemAt indexPath: IndexPath) -> CGSize {
        return CGSize(width: view.frame.width, height: 50)
    }
}

extension ResultsViewController: ResultsViewModelViewDelegate {
    func bind() {
        collectionView.reloadData()
    }
}
