package com.Project.core.listeners;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.event.Event;
import org.osgi.service.event.EventHandler;
import org.osgi.service.event.EventConstants;

@Component(service = EventHandler.class, immediate = true, property = {
  
    EventConstants.EVENT_TOPIC + "=" + "org/apache/sling/api/resource/ResourceEventListener/*"
})
public class PageCreationDeletionListener implements EventHandler {

    private static final Logger LOG = LoggerFactory.getLogger(PageCreationDeletionListener.class);

    @Override
    public void handleEvent(Event event) {
        // Get the event type (creation, deletion, etc.)
        String eventType = event.getTopic();
        
        // Get the page path from the event
        String pagePath = (String) event.getProperty("path");

        // Check if event is related to page creation or deletion
        if (pagePath != null) {
            if ("org/apache/sling/api/resource/ResourceEventListener".equals(eventType)) {
                if (eventType.contains("CREATED")) {
                    LOG.error("Page created at path: " + pagePath);
                } else if (eventType.contains("DELETED")) {
                    LOG.error("Page deleted at path: " + pagePath);
                }
            }
        }
    }
}
