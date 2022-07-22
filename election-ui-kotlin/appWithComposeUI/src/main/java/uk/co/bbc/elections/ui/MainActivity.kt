package uk.co.bbc.elections.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import uk.co.bbc.elections.ElectionsApplication

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            App((application as ElectionsApplication).serviceContainer)
        }
    }
}
