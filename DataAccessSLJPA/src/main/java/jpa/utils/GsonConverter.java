package jpa.utils;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.Reader;
import java.lang.reflect.Type;
import java.time.LocalDateTime;

/**
 * Default json converter for restlet
 * @author Unknow from internet
 */

public class GsonConverter {

    protected final Gson gson;

    ExclusionStrategy strategy = new ExclusionStrategy() {
        @Override
        public boolean shouldSkipClass(Class<?> clazz) {
            return false;
        }

        @Override
        public boolean shouldSkipField(FieldAttributes field) {
            return field.getName().startsWith("password");
        }
    };

    public GsonConverter() {
        final GsonBuilder builder = new GsonBuilder();
        builder.addSerializationExclusionStrategy(strategy)
                .registerTypeHierarchyAdapter(byte[].class, new GsonByteArrayToBase64())
                .registerTypeAdapter(LocalDateTime.class, new GsonLocalDateTime())
                .registerTypeAdapterFactory(HibernateProxyTypeAdapter.FACTORY)
                .setPrettyPrinting()
        ;
        this.gson = builder.create();
    }

    public <T> String toJson(T obj) {
        return gson.toJson(obj);
    }

    public <T> T fromJson(Reader reader, Type valueType) {
        return gson.fromJson(reader, valueType);
    }
}
