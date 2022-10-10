package cn.com.ogtwelve.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.datatype.joda.JodaModule;
import com.fasterxml.jackson.datatype.joda.cfg.JacksonJodaDateFormat;
import com.fasterxml.jackson.datatype.joda.deser.InstantDeserializer;
import com.fasterxml.jackson.datatype.joda.ser.DateTimeSerializer;
import com.fasterxml.jackson.datatype.joda.ser.InstantSerializer;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ConversionServiceFactoryBean;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import javax.annotation.Resource;
import java.io.IOException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;

import static java.time.format.DateTimeFormatter.ofPattern;

/**
 * The use of this global time configuration is to add (scanBasePackages = "cn.com.ogtwelve.utils") at the back of @SpringBootApplication
 * in the Application class(The class to start springboot)
 */
@Configuration
public class GlobalTimeConfig {

    /**
     * The method is a http request converter;
     * Convert from [java.time] LocalDate LocalDateTime Instant Calendar and [org.joda] LocalDate LocalDateTime Instant to any type u want;
     * @return MappingJackson2HttpMessageConverter
     */
    @Bean
    public MappingJackson2HttpMessageConverter getMappingJackson2HttpMessageConverter() {
        MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter();
        String dateTimePattern = "yyyy-MM-dd HH:mm:ss";
        String datePattern = "yyyy-MM-dd";
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            // The below part is under the [java.time] package
            JavaTimeModule javaTimeModule = new JavaTimeModule();
            javaTimeModule.addDeserializer(LocalDate.class,
                    new LocalDateDeserializer(ofPattern(datePattern)));
            javaTimeModule.addSerializer(LocalDate.class,
                    new LocalDateSerializer(ofPattern(datePattern)));
            javaTimeModule.addDeserializer(LocalDateTime.class,
                    new LocalDateTimeDeserializer(ofPattern(dateTimePattern)));
            javaTimeModule.addSerializer(LocalDateTime.class,
                    new LocalDateTimeSerializer(ofPattern(dateTimePattern)));
            // Used by Instant class and Calendar class
            javaTimeModule.addDeserializer(Instant.class,new InstantCustomDeserializer());
            javaTimeModule.addSerializer(Instant.class,new InstantCustomSerializer());
            // The below part is under the [org.joda.time] package
            JodaModule jodaModule = new JodaModule();
            jodaModule.addDeserializer(org.joda.time.LocalDate.class,
                    new com.fasterxml.jackson.datatype.joda.deser.LocalDateDeserializer(new JacksonJodaDateFormat(DateTimeFormat.forPattern(datePattern))));
            jodaModule.addSerializer(org.joda.time.LocalDate.class,
                    new com.fasterxml.jackson.datatype.joda.ser.LocalDateSerializer(new JacksonJodaDateFormat(DateTimeFormat.forPattern(datePattern))));
            jodaModule.addDeserializer(org.joda.time.LocalDateTime.class,
                    new com.fasterxml.jackson.datatype.joda.deser.LocalDateTimeDeserializer(new JacksonJodaDateFormat(DateTimeFormat.forPattern(dateTimePattern))));
            jodaModule.addSerializer(org.joda.time.LocalDateTime.class,
                    new com.fasterxml.jackson.datatype.joda.ser.LocalDateTimeSerializer(new JacksonJodaDateFormat(DateTimeFormat.forPattern(dateTimePattern))));
            jodaModule.addDeserializer(org.joda.time.Instant.class,
                    new InstantDeserializer(new JacksonJodaDateFormat(DateTimeFormat.forPattern(dateTimePattern))));
            jodaModule.addDeserializer(org.joda.time.Instant.class,
                    new InstantDeserializer(new JacksonJodaDateFormat(DateTimeFormat.forPattern(dateTimePattern)).withTimeZone(TimeZone.getDefault())));
            jodaModule.addSerializer(org.joda.time.Instant.class,
                    new InstantSerializer(new JacksonJodaDateFormat(DateTimeFormat.forPattern(dateTimePattern)).withTimeZone(TimeZone.getDefault())));
            jodaModule.addSerializer(DateTime.class, new DateTimeSerializer(new JacksonJodaDateFormat(DateTimeFormat.forPattern(dateTimePattern))));
            jodaModule.addDeserializer(DateTime.class, new JsonDeserializer<DateTime>() {
                @Override
                public DateTime deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
                    String date = jsonParser.readValueAs(String.class);
                    org.joda.time.format.DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd");
                    return DateTime.parse(date,formatter);
                }
            });
            objectMapper.registerModule(javaTimeModule);
            objectMapper.registerModule(jodaModule);
        }catch (Exception e){
            e.printStackTrace();
        }
        objectMapper.setDateFormat(GlobalTimeConverterJSONStyle.instance);
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        mappingJackson2HttpMessageConverter.setObjectMapper(objectMapper);
        List<MediaType> list = new ArrayList<MediaType>();
        // The first one is deprecated, but it is still used in the current version
        list.add(MediaType.APPLICATION_JSON_UTF8);
        // The second one is the one that still works.
        list.add(new MediaType("application", "json"));
        mappingJackson2HttpMessageConverter.setSupportedMediaTypes(list);
        return mappingJackson2HttpMessageConverter;
    }

    /**
     * The method is about to make the global time converter work;
     * @param globalTimeConverter Global time converter class
     * @return ConversionService
     */
    @Bean
    @Resource
    public ConversionService getConversionService(GlobalTimeConverter globalTimeConverter){
        ConversionServiceFactoryBean factoryBean = new ConversionServiceFactoryBean();
        Set<Converter> converters = new HashSet<>();
        converters.add(globalTimeConverter);
        factoryBean.setConverters(converters);
        return factoryBean.getObject();
    }

    /**
     * The method to serializer Instant class
     */
    static class InstantCustomSerializer extends JsonSerializer<Instant> {
        private final DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
                .withLocale(Locale.getDefault())
                .withZone(ZoneId.systemDefault());
        @Override
        public void serialize(Instant instant, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
            String str = fmt.format(instant);
            jsonGenerator.writeString(str);
        }
    }

    /**
     * The method to deserializer Instant class
     */
    static class InstantCustomDeserializer extends JsonDeserializer<Instant> {
        private final DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
                .withLocale(Locale.getDefault())
                .withZone(ZoneId.systemDefault());

        @Override
        public Instant deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
            return Instant.from(fmt.parse(jsonParser.getText()));
        }
    }

}




