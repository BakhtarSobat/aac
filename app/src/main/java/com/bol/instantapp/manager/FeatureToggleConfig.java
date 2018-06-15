package com.bol.instantapp.manager;


import com.abnamro.nl.toggle.annotation.FeatureToggleBind;
import com.abnamro.nl.toggle.annotation.FeatureToggleConfiguration;

@FeatureToggleConfiguration(strictnessPolicy = FeatureToggleConfiguration.StrictnessPolicy.MODERATE, maxNumberOfToggles = 6)
public class FeatureToggleConfig{

    @FeatureToggleBind(expirationDate = "2018-06-22", toggleName = "test1")
    public boolean newLayout = false;
}
