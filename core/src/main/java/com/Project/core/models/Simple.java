package com.Project.core.models;

import org.apache.sling.models.annotations.injectorspecific.ChildResource;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;
import javax.inject.Inject;
import java.util.List;

@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class Simple {

    @ValueMapValue
    private String text;

    @ChildResource
    private List<Sample> mul;

    public String getText() {
        return text;
    }

    public List<Sample> getMul() {
        return mul;
    }

    @Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
    public static class Sample {  

        @Inject  
        private String path;

        @Inject
        private String date;

        public String getPath() {
            return path;
        }

        public String getDate() {
            return date;
        }
    }
}
