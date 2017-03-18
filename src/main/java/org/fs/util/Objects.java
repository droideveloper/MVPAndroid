/*
 * MVP Copyright (C) 2017 Fatih.
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
package org.fs.util;

import android.text.TextUtils;
import io.reactivex.functions.Predicate;
import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public final class Objects {

  private Objects() {
    throw new IllegalArgumentException("you can not have instance of this object.");
  }

  public static <T> boolean isNullOrEmpty(T object) {
    if (object == null) return true;
    if (object instanceof String) {
      String str = (String) object;
      return TextUtils.isEmpty(str);
    }
    if (object instanceof Collection<?>) {
      Collection<?> collection = (Collection<?>) object;
      return collection.isEmpty();
    }
    if (object instanceof File) {
      File file = (File) object;
      return file.exists();
    }
    return false;
  }

  @SuppressWarnings("unchecked")
  public static <T> T toObject(Object o) {
    if (o == null) return null;
    try {
      return (T) o;
    } catch (ClassCastException ignored) {
      return null;
    }
  }

  public static <T> List<T> sort(List<T> collection, Comparator<T> filter) {
    if (!isNullOrEmpty(collection)) {
      Collections.sort(collection, filter);
      return collection;
    }
    return Collections.emptyList();
  }

  public static <T> List<T> filter(List<T> collection, Predicate<T> predicate) {
    if (!isNullOrEmpty(collection)) {
      List<T> newCollection = new ArrayList<>();
      for (int i = 0, z = collection.size(); i < z; i++) {
        final T item = collection.get(i);
        try {
          if (predicate.test(item)) {
            newCollection.add(item);
          }
        } catch (Exception error) {
          error.printStackTrace();
          break;
        }
      }
      return newCollection;
    }
    return Collections.emptyList();
  }

  public static String toUriString(File file) {
    return file.toURI().toString();
  }

  public static File findFirstExtension(File directory, final String extension) {
    if(directory.isDirectory()) {
      return Arrays.first(directory.listFiles(new FilenameFilter() {
        @Override public boolean accept(File f, String name) {
          return name.endsWith(extension);
        }
      }));
    }
    return null;
  }

  public static File findFirst(File directory, final String text) {
    if (directory.isDirectory()) {
      return Arrays.first(directory.listFiles(new FilenameFilter() {
        @Override public boolean accept(File dir, String name) {
          return name.contains(text);
        }
      }));
    }
    return null;
  }
}