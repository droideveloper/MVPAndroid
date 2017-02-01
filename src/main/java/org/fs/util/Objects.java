/*
 * To-Do Copyright (C) 2017 Fatih.
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
import java.io.File;
import java.util.Collection;
import java.util.Comparator;
import java.util.Collections;
import java8.util.function.Predicate;
import java8.util.stream.Collectors;
import java8.util.stream.StreamSupport;

public final class Objects {

  /**
   * Private constructor
   */
  private Objects() {
    throw new IllegalArgumentException("you can not have instance of this object.");
  }

  /**
   * Checks if object null or empty if object instance is Collection, String or File
   *
   * @param object object instance to check
   * @param <T> T type of the object
   * @return true or false
   */
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

  /**
   * Casts object to parameter we looking for
   * if it's not same type then we do nothing
   *
   * @param o object instance to cast
   * @param <T> Type of cast
   * @return T or null
   */
  @SuppressWarnings("unchecked")
  public static <T> T toObject(Object o) {
    if (o == null) return null;
    try {
      return (T) o;
    } catch (ClassCastException ignored) {
      return null;
    }
  }

  /**
   * Sort given collection depending on filter method
   * @param collection Collection to sort
   * @param filter filter for sort operation
   * @param <T> Type of Element
   * @return sorted Collection or empty if Collection is empty
   */
  public static <T> Collection<T> sort(Collection<T> collection, Comparator<T> filter) {
    if (!isNullOrEmpty(collection))  {
      StreamSupport.stream(collection)
          .sorted(filter)
          .collect(Collectors.toList());
    }
    return Collections.emptyList();
  }

  /**
   * Filters collection item on condition
   * @param collection Collection to filter
   * @param condition condition to apply
   * @param <T> Type of Element
   * @return filtered Collection or empty if Collection is empty
   */
  public static <T> Collection<T> filter(Collection<T> collection, Predicate<T> condition) {
    if (!isNullOrEmpty(collection)) {
      StreamSupport.stream(collection)
          .filter(condition)
          .collect(Collectors.toList());
    }
    return Collections.emptyList();
  }
}