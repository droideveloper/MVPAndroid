package org.fs.net.converter;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;

import org.fs.util.PreconditionUtility;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Converter;

/**
 * Created by Fatih on 31/05/16.
 * as org.fs.net.converter.GsonResponseBodyConverter
 */
public class GsonResponseBodyConverter<T> implements Converter<ResponseBody, T> {

    private final Gson           mGson;
    private final TypeAdapter<T> mTypeAdapter;

    public GsonResponseBodyConverter(final TypeAdapter<T> mTypeAdapter, final Gson mGson) {
        this.mGson = mGson;
        this.mTypeAdapter = mTypeAdapter;
    }

    @Override
    public T convert(ResponseBody value) throws IOException {
        PreconditionUtility.checkNotNull(value, "response error");
        try {
            JsonReader reader = mGson.newJsonReader(value.charStream());
            return mTypeAdapter.read(reader);
        } finally {
            value.close();
        }
    }
}
