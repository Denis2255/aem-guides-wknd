package com.adobe.aem.guides.wknd.core.models;


import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

@Model(adaptables = {Resource.class},
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class CtaModel {
    @ValueMapValue
    private String targetCTA;

    @ValueMapValue
    private String linkCTA;

    @ValueMapValue
    private String titleCTA;

    public String getTargetCTA() {
        return targetCTA;
    }


    public String getLinkCTA() {
        return linkCTA;
    }


    public String getTitleCTA() {
        return titleCTA;
    }
}

