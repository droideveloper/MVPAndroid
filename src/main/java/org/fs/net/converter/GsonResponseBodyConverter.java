/*
 * Copyright (C) 2016 Fatih.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.fs.net.converter;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;

import org.fs.util.PreconditionUtility;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Converter;

public class GsonResponseBodyConverter<T> implements Converter<ResponseBody, T> {

  private final Gson           mGson;
  private final TypeAdapter<T> mTypeAdapter;

  public GsonResponseBodyConverter(final TypeAdapter<T> mTypeAdapter, final Gson mGson) {
    this.mGson = mGson;
    this.mTypeAdapter = mTypeAdapter;
  }

  @Override public T convert(ResponseBody value) throws IOException {
    PreconditionUtility.checkNotNull(value, "response error");
    try {
      JsonReader reader = mGson.newJsonReader(value.charStream());
      return mTypeAdapter.read(reader);
    } finally {
      value.close();
    }
  }
}
