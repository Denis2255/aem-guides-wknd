package com.adobe.aem.guides.wknd.core.services;

import org.apache.sling.api.resource.LoginException;
import org.apache.sling.api.resource.ResourceResolver;

public interface ServiceResolver {
    public ResourceResolver getServiceResourceResolver() throws LoginException;
}
