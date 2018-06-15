package com.bol.instantapp.manager
import com.abnamro.nl.toggle.toggler.FeatureToggler


class FeatureToggleManager constructor(val config: FeatureToggleConfig) : FeatureToggler {

    init {
        FeatureToggleBinder.bind(this, config)
    }
    override fun isEnabled(name: String): Boolean {
        return FakeFirebaseClient.isEnabled(name)
    }
}