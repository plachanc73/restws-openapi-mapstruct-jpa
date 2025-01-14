package ca.qc.plachanc73.demo.restws.config.json;

import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.deser.ValueInstantiator;
import com.fasterxml.jackson.databind.deser.ValueInstantiators;
import com.fasterxml.jackson.databind.deser.std.StdValueInstantiator;
import com.fasterxml.jackson.databind.introspect.AnnotatedClass;
import com.fasterxml.jackson.databind.introspect.AnnotatedClassResolver;
import com.fasterxml.jackson.databind.introspect.AnnotatedMethod;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.jsr310.PackageVersion;
import com.fasterxml.jackson.datatype.jsr310.deser.InstantDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.ZonedDateTimeSerializer;

import java.io.Serial;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;

/**
 * This class is a copy of the com.fasterxml.jackson.datatype.jsr310.JavaTimeModule class.
 * We added custom Serializer and Deserializer for LocalDateTime to support the given Date Time Pattern
 * because the default Jackson format is not compatible with OpenApi DateTime format.
 * <p>
 * Default Serializer and Deserializer can also be added like we did for ZonedDateTime.
 *
 * @see com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
 */
public class JavaTimeModule extends SimpleModule {
    @Serial
    private static final long serialVersionUID = 1L;

    public JavaTimeModule(String dateTimePattern) {
        super(PackageVersion.VERSION);
        this.addDeserializer(ZonedDateTime.class, InstantDeserializer.ZONED_DATE_TIME);
        this.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(DateTimeFormatter.ofPattern(dateTimePattern)));
        this.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(DateTimeFormatter.ofPattern(dateTimePattern)));
        this.addSerializer(ZonedDateTime.class, ZonedDateTimeSerializer.INSTANCE);
    }

    public void setupModule(SetupContext context) {
        super.setupModule(context);
        context.addValueInstantiators(new ValueInstantiators.Base() {
            public ValueInstantiator findValueInstantiator(DeserializationConfig config, BeanDescription beanDesc, ValueInstantiator defaultInstantiator) {
                JavaType type = beanDesc.getType();
                Class<?> raw = type.getRawClass();
                if (ZoneId.class.isAssignableFrom(raw) && defaultInstantiator instanceof StdValueInstantiator inst) {
                    AnnotatedClass ac;
                    if (raw == ZoneId.class) {
                        ac = beanDesc.getClassInfo();
                    } else {
                        ac = AnnotatedClassResolver.resolve(config, config.constructType(ZoneId.class), config);
                    }

                    if (!inst.canCreateFromString()) {
                        AnnotatedMethod factory = JavaTimeModule.this._findFactory(ac, String.class);
                        if (factory != null) {
                            inst.configureFromStringCreator(factory);
                        }
                    }
                }

                return defaultInstantiator;
            }
        });
    }

    protected AnnotatedMethod _findFactory(AnnotatedClass cls, Class<?>... argTypes) {
        int argCount = argTypes.length;
        Iterator<AnnotatedMethod> var5 = cls.getFactoryMethods().iterator();

        AnnotatedMethod method;
        do {
            if (!var5.hasNext()) {
                return null;
            }

            method = var5.next();
        } while (!"of".equals(method.getName()) || method.getParameterCount() != argCount);

        for (int i = 0; i < argCount; ++i) {
            Class<?> argType = method.getParameter(i).getRawType();
            argType.isAssignableFrom(argTypes[i]);
        }

        return method;
    }
}
