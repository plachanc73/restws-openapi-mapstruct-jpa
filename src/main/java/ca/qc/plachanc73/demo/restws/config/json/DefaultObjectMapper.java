package ca.qc.plachanc73.demo.restws.config.json;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.SerializationFeature;
import lombok.Getter;

public class DefaultObjectMapper extends ObjectMapper {

    public static final String DEFAULT_DATETIME_PATTERN = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
    @Getter
    private static final DefaultObjectMapper instance = new DefaultObjectMapper();

    protected DefaultObjectMapper() {
        super();
        registerModule(new JavaTimeModule(DEFAULT_DATETIME_PATTERN));
        configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        configure(SerializationFeature.WRITE_DATE_TIMESTAMPS_AS_NANOSECONDS, false);
        configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        setPropertyNamingStrategy(new PropertyNamingStrategies.LowerCamelCaseStrategy());
        setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
    }

}


