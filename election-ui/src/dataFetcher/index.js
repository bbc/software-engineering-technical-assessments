const fetchResults = () => {
  return new Promise((resolve, reject) => {
    setTimeout(() => {
      resolve([
        {
          'party': 'Adder Party',
          'candidate': 'Baldrick',
          'votes': '1056'
        },
        {
          'party': 'Independent',
          'candidate': 'Lord Buckethead',
          'votes': '9900'
        },
        {
          'party': 'Independent',
          'candidate': 'Count Binface',
          'votes': '9900'
        }
      ]);
    }, 500);
  }
)};

export default fetchResults;
