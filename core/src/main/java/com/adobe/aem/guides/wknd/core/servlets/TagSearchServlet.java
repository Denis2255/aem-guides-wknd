package com.adobe.aem.guides.wknd.core.servlets;

import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageManager;
import lombok.extern.slf4j.Slf4j;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.servlets.ServletResolverConstants;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.json.JSONArray;
import org.json.JSONObject;
import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.Servlet;
import java.util.List;
import java.util.Optional;

import static org.apache.sling.query.SlingQuery.$;

;

@Slf4j
@Component(service = Servlet.class,
        property = {
                ServletResolverConstants.SLING_SERVLET_PATHS + "=/bin/target-tag"})
public class TagSearchServlet extends SlingAllMethodsServlet {

    private final Logger logger = LoggerFactory.getLogger(TagSearchServlet.class);

    @Override
    protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) {
        String tag = request.getParameter("tag");
        String root = request.getParameter("root");
        try {
            ResourceResolver resolver = request.getResourceResolver();
            Resource r = resolver.getResource(root);
            List<Resource> resourceCollection = $(r).children("cq:Page[jcr:content/cq:tags=" + tag + "]").asList();
            JSONArray jsonArray = new JSONArray();
            for (Resource resource : resourceCollection) {
                JSONObject jsonObject = new JSONObject();
                Page page = Optional.ofNullable(resolver.adaptTo(PageManager.class))
                        .map(pm -> pm.getPage(resource.getPath())).orElse(null);
                if (page == null) {
                    logger.info("page is null");
                    return;
                }
                String pageName = page.getTitle();
                String pathName = page.getPath();
//                String pageDescription = page.getDescription();
                jsonObject.put("title", pageName);
                jsonObject.put("path", pathName);
                jsonArray.put(jsonObject);
            }
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(jsonArray.toString());
        } catch (Exception e) {
            logger.info("Error Query");
        }
    }

    @Override
    protected void doPost(SlingHttpServletRequest request, SlingHttpServletResponse response) {
        doGet(request, response);
    }
}




