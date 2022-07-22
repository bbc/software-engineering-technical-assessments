package uk.co.bbc.elections

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import uk.co.bbc.elections.ui.results.ResultsFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, ResultsFragment.newInstance())
                .commitNow()
        }
    }
}