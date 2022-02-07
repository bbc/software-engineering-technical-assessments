# Introduction

This directory contains a rudimentary mock of a data API for the purposes of the assessment.

## Interface

The mock API exports two named functions `fetchResultData` and `fetchCandidateMap`, and a default export of `fetchResultData`.

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
      'party': 'Adder Party',
      'candidateId': 1,
      'votes': '1056'
    },
    {
      'party': 'Independent',
      'candidateId': 2,
      'votes': '6900'
    },
    {
      'party': 'Independent',
      'candidateId': 3,
      'votes': '9900'
    }
  ]
}
```

Expected output after > 5 mock "requests":

```js
{
  metadata: {
    isComplete: true
  },
  results: [
    {
      'party': 'Adder Party',
      'candidateId': 1,
      'votes': '1056'
    },
    {
      'party': 'Independent',
      'candidateId': 2,
      'votes': '6900'
    },
    {
      'party': 'Independent',
      'candidateId': 3,
      'votes': '9900'
    }
  ]
}
```

### `fetchCandidateMap`
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