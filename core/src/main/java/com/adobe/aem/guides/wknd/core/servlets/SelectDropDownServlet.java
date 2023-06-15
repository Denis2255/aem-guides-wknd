package com.adobe.aem.guides.wknd.core.servlets;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.Servlet;

import com.adobe.cq.commerce.common.ValueMapDecorator;
import com.adobe.granite.ui.components.ds.DataSource;
import com.adobe.granite.ui.components.ds.SimpleDataSource;
import com.adobe.granite.ui.components.ds.ValueMapResource;
import com.day.crx.JcrConstants;
import org.apache.commons.collections4.iterators.TransformIterator;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.ResourceMetadata;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ValueMap;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.osgi.framework.Constants;
import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

@Component(
        service = Servlet.class,
        property = {
                Constants.SERVICE_DESCRIPTION + "= Dynamic Drop Down",
                "sling.servlet.resourceTypes=" + "/bin/selectdropdownservlet"
        })
public class SelectDropDownServlet extends SlingAllMethodsServlet {

    private final Logger LOGGER = LoggerFactory.getLogger(TagSearchServlet.class);


    @Override
    protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) {
        try {
            String[] allCountries = new String[]{"small", "medium", "large"};
            ResourceResolver resourceResolver = request.getResourceResolver();
            List<KeyValue> dropDownList = new ArrayList<>();
            for (String value : allCountries) {
                dropDownList.add(new KeyValue(value, value));
            }
            @SuppressWarnings("unchecked")
            DataSource ds =
                    new SimpleDataSource(
                            new TransformIterator(
                                    dropDownList.iterator(),
                                    input -> {
                                        KeyValue keyValue = (KeyValue) input;
                                        ValueMap vm = new ValueMapDecorator(new HashMap<>());
                                        vm.put("value", keyValue.key);
                                        vm.put("text", keyValue.value);
                                        return new ValueMapResource(
                                                resourceResolver, new ResourceMetadata(),
                                                JcrConstants.NT_UNSTRUCTURED, vm);
                                    }));
            request.setAttribute(DataSource.class.getName(), ds);

        } catch (Exception e) {
            LOGGER.error("Error in Get Drop Down Values", e);
        }
    }

    private class KeyValue {

        /**
         * key property.
         */
        private String key;
        /**
         * value property.
         */
        private String value;

        /**
         * constructor instance intance.
         *
         * @param newKey   -
         * @param newValue -
         */
        private KeyValue(final String newKey, final String newValue) {
            this.key = newKey;
            this.value = newValue;
        }
    }
}