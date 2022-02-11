# Introduction

This directory contains a rudimentary mock of a data API for the purposes of the assessment.

## Interface

The mock API exports two named functions `fetchResultData` and `fetchCandidateData`, and a default export of `fetchResultData`.

### `fetchResultData`
- Takes no parameters.
- Mocks an asynchronous request by waiting for 500ms before resolving.
- Returns an object.

Expected initial output:
```js
{
  metadata: {
    isComplete: false
  },
  results: [
    {
      'party': 'Hippo Party',
      'candidateId': 1,
      'votes': '1056'
    },
    {
      'party': 'Giraffe Party',
      'candidateId': 2,
      'votes': '6900'
    },
    {
      'party': 'Tiger Party',
      'candidateId': 3,
      'votes': '9900'
    }
  ]
}
```

Expected output after 4 calls from the UI:

```js
{
  metadata: {
    isComplete: true
  },
  results: [
    {
      'party': 'Hippo Party',
      'candidateId': 1,
      'votes': '1556'
    },
    {
      'party': 'Giraffe Party',
      'candidateId': 2,
      'votes': '7400'
    },
    {
      'party': 'Tiger Party',
      'candidateId': 3,
      'votes': '10400'
    }
  ]
}
```

### `fetchCandidateData`
- Takes no parameters.
- Returns an array.

Expected output:

```js
[
  {
    id: 1,
    name: 'Baldrick'
  },
  {
    id: 2,
    name: 'Lord Buckethead'
  },
  {
    id:3,
    name: 'Count Binface'
  }
]
```