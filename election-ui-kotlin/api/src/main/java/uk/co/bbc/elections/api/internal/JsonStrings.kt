package uk.co.bbc.elections.api.internal

internal object JsonStrings {
    val candidatesJson = """
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
        """.trimIndent()

    val results1Json = """
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
        """.trimIndent()

    val results2Json = """
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
        """.trimIndent()

    val results3Json = """
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
        """.trimIndent()
}