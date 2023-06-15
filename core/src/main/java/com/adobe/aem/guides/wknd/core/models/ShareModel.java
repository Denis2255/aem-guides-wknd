package com.adobe.aem.guides.wknd.core.models;

import lombok.Getter;
import lombok.Setter;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

@Getter
@Setter
@Model(adaptables = {SlingHttpServletRequest.class},
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class ShareModel {

    @ValueMapValue
    private String title;

}
