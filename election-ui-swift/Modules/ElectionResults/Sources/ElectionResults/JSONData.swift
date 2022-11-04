import Foundation

struct JSONData {
    
    static let candidateJSON = """
[
    {
        "id": 1,
        "name": "Baldrick"
    },
    {
        "id": 2,
        "name": "Lord Buckethead"
    },
    {
        "id": 3,
        "name": "Count Binface"
    }
]
"""
    
    static let resultsJSON1 = """
{
    "metadata": {
        "isComplete": false
    },
    "results": [
        {
            "party": "Adder Party",
            "candidateId": 1,
            "votes": 1056
        },
        {
            "party": "Independent",
            "candidateId": 2,
            "votes": 6900
        },
        {
            "party": "Independent",
            "candidateId": 3,
            "votes": 9900
        }
    ]
}
"""
    
    static let resultsJSON2 = """
{
    "metadata": {
        "isComplete": false
    },
    "results": [
        {
            "party": "Adder Party",
            "candidateId": 1,
            "votes": 1256
        },
        {
            "party": "Independent",
            "candidateId": 2,
            "votes": 7100
        },
        {
            "party": "Independent",
            "candidateId": 3,
            "votes": 9950
        }
    ]
}
"""
    
    static let resultsJSON3 = """
{
    "metadata": {
        "isComplete": true
    },
    "results": [
        {
            "party": "Adder Party",
            "candidateId": 1,
            "votes": 1300
        },
        {
            "party": "Independent",
            "candidateId": 2,
            "votes": 7201
        },
        {
            "party": "Independent",
            "candidateId": 3,
            "votes": 10100
        }
    ]
}
"""
}
