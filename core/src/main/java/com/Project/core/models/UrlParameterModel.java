package com.Project.core.models;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.OSGiService;
import org.apache.sling.models.annotations.injectorspecific.Self;

import com.Project.core.config.UrlParameterConfig;

import javax.annotation.PostConstruct;
import java.util.*;
import java.util.stream.Collectors;

@Model(
        adaptables = {SlingHttpServletRequest.class, Resource.class},
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL
)
public class UrlParameterModel {

    @Self
    private SlingHttpServletRequest request;

    @OSGiService
    private UrlParameterConfig config;

    private Map<String, String[]> filteredParams;
    private Set<String> ignoredParams;

    @PostConstruct
    protected void init() {
        filteredParams = new LinkedHashMap<>();
        ignoredParams = new LinkedHashSet<>();

      
        if (config == null || request == null) {
            return;
        }

        List<String> allowedParams = Arrays.asList(config.allowedParameters());

        request.getParameterMap().forEach((key, values) -> {
            if (allowedParams.contains(key)) {
                filteredParams.put(key, values);
            } else {
                ignoredParams.add(key);
            }
        });
    }

    public Map<String, String[]> getFilteredParams() {
        return filteredParams;
    }

    public Set<String> getIgnoredParams() {
        return ignoredParams;
    }

  
    public String getFormattedQueryParams() {
        return filteredParams.entrySet().stream()
                .map(entry -> entry.getKey() + "=" + String.join(",", entry.getValue()))
                .collect(Collectors.joining("&"));
    }
}
