package com.Project.core.config;

import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;
import org.osgi.service.metatype.annotations.AttributeType;

@ObjectClassDefinition(name = "URL Parameter Configuration")
public @interface UrlParameterConfig {

    @AttributeDefinition(
            name = "Allowed URL Parameters",
            description = "Define which URL parameters should be allowed",
            type = AttributeType.STRING
    )
    String[] allowedParameters() default {"param1", "param2"};
}
