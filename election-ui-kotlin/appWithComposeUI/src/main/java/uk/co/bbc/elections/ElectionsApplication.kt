package uk.co.bbc.elections

import android.app.Application
import uk.co.bbc.elections.data.ElectionsApplicationServiceContainer
import uk.co.bbc.elections.data.ServiceContainer

class ElectionsApplication: Application() {

    val serviceContainer: ServiceContainer = ElectionsApplicationServiceContainer()
}
