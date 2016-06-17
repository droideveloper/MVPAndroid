package org.fs.net.converter;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.reflect.TypeToken;

import org.fs.util.PreconditionUtility;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;

/**
 * Created by Fatih on 31/05/16.
 * as org.fs.net.converter.GsonConverterFactory
 */
public class GsonConverterFactory extends Converter.Factory {

    private final Gson mGson;

    /**
     * Create GsonConverterFactory instance that uses user specified Gson object
     * @param mGson Gson object instance that you configured with your needs
     * @return returns GsonConverterFactory that will be using your specific Gson object instance
     */
    public static GsonConverterFactory createWithGson(Gson mGson) {
        return new GsonConverterFactory(mGson);
    }

    /**
     * Create GsonConverterFactory instance that uses new Gson object
     * @return returns GsonConverterFactory that will be using new Gson object instance
     */
    public static GsonConverterFactory createWithDefaultGson() {
        return new GsonConverterFactory(new Gson());
    }

    private GsonConverterFactory(final Gson mGson) {
        PreconditionUtility.checkNotNull(mGson, "gson instance is null");
        this.mGson = mGson;
    }

    @Override
    public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations, Retrofit retrofit) {
        TypeAdapter<?> typeAdapter = typeAdapterFromType(type);
        return new GsonResponseBodyConverter<>(typeAdapter, mGson);
    }

    @Override
    public Converter<?, RequestBody> requestBodyConverter(Type type, Annotation[] parameterAnnotations, Annotation[] methodAnnotations, Retrofit retrofit) {
        TypeAdapter<?> typeAdapter = typeAdapterFromType(type);
        return new GsonRequestBodyConverter<>(typeAdapter, mGson);
    }

    private TypeAdapter<?> typeAdapterFromType(Type type) {
        return mGson.getAdapter(TypeToken.get(type));
    }
}
