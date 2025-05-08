package com.Project.core.models;

import java.util.List;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ChildResource;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class Task1Model {
    @ValueMapValue
    private String text;
    @ValueMapValue
    private String path;
    @ChildResource
    private List<Task1Model1> mul;
    @ValueMapValue
    private String checkbox;

    public String getTextfield() {
        return text;
    }

    
    public String getPathfield() {
        return path;
    }

    public List<Task1Model1> getMultifield() {
        return mul;
    }

    public String getCheckbox() {
        return checkbox;
    }

}
