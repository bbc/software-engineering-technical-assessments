package uk.co.bbc.elections.api.internal

import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class JSONResultsRepositoryTest {

    private lateinit var repository: JSONResultsRepository

    @Before
    fun setUp() {
        repository = JSONResultsRepository()
    }

    @Test
    fun latestResults() = runTest {
        val results = repository.latestResults()
        val first = results.results.first()

        Assert.assertFalse(results.isComplete)
        Assert.assertEquals(3, results.results.size)

        Assert.assertEquals(1, first.candidateId)
        Assert.assertEquals("Adder Party", first.party)
        Assert.assertEquals(1056, first.votes)
    }

    @Test
    fun isComplete() = runTest {
        repository.latestResults()
        repository.latestResults()
        val results = repository.latestResults()

        Assert.assertTrue(results.isComplete)
        Assert.assertEquals(3, results.results.size)
    }

    @Test
    fun allCandidates() = runTest {
        val all = repository.allCandidates()
        val first = all.first()

        Assert.assertEquals(3, all.size)

        Assert.assertEquals(1, first.id)
        Assert.assertEquals("Baldrick", first.name)
    }
}