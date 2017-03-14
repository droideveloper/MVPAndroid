/*
 * MVP Android Copyright (C) 2016 Fatih.
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

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.Charset;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okio.Buffer;
import org.fs.util.PreconditionUtility;
import retrofit2.Converter;

public class GsonRequestBodyConverter<T> implements Converter<T, RequestBody> {

  private final static MediaType MEDIA_TYPE       = MediaType.parse("application/json; charset=UTF-8");
  private final static Charset   DEFAULT_CHARSET  = Charset.forName("UTF-8");

  private final TypeAdapter<T> typeAdapter;

  public GsonRequestBodyConverter(final TypeAdapter<T> typeAdapter) {
    this.typeAdapter = typeAdapter;
  }

  @Override public RequestBody convert(T value) throws IOException {
    PreconditionUtility.checkNotNull(value, "can not deserialize null object, value is null");
    Buffer buffer = new Buffer();
    Writer writer = new OutputStreamWriter(buffer.outputStream(), DEFAULT_CHARSET);
    JsonWriter jsonWriter = new JsonWriter(writer);
    typeAdapter.write(jsonWriter, value);
    jsonWriter.close();
    return RequestBody.create(MEDIA_TYPE, buffer.readByteString());
  }
}
