package com.adobe.aem.guides.wknd.core.models;

import com.day.cq.commons.Externalizer;
import lombok.Getter;
import lombok.Setter;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.Via;
import org.apache.sling.models.annotations.injectorspecific.OSGiService;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

import javax.annotation.PostConstruct;
import javax.inject.Inject;


@Getter
@Setter
@Model(adaptables = {SlingHttpServletRequest.class},
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class BannerModel {

    @Inject
    @Via("resource")
    private BlockModel block;

    @Inject
    @Via("resource")
    private CtaModel cta;

    @ValueMapValue
    private String heading;

    @ValueMapValue
    private String text;

    @SlingObject
    private ResourceResolver resourceResolver;

    @OSGiService
    private Externalizer externalizer;

    private String publishLink;
    private String authorLink;
    private String path;
    private String size;

    private String linkCTA;
    private String targetCTA;
    private String titleCTA;

    @PostConstruct
    protected void init() {
        if (cta != null) {
            path = block.getFileReference();
            size = block.getSize();

            linkCTA = cta.getLinkCTA();
            targetCTA = cta.getTargetCTA();
            titleCTA = cta.getTitleCTA();
            if (linkCTA != null) {
                publishLink = externalizer.publishLink(resourceResolver, linkCTA);
                authorLink = externalizer.authorLink(resourceResolver, linkCTA);
            }
        }
    }
}

