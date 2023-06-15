package com.adobe.aem.guides.wknd.core.models;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

@Model(adaptables = {Resource.class},
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class BlockModel {
    @ValueMapValue
    private String fileReference;

    @ValueMapValue
    private String size;

    public String getFileReference() {
        return fileReference;
    }

    public String getSize() {
        return size;
    }

}
