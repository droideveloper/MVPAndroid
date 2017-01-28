/*
 * Core Android Copyright (C) 2016 Fatih.
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

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.fs.exception.AndroidException;

public final class Collections {

  private Collections() {
    throw new AndroidException("no sugar for ya");
  }

  /**
   * filters collection and returns matching results as new collection
   * @param target target collection to be filtered
   * @param predicate filter logic defined
   * @param <T> Type
   * @return new Collection, filtered accordingly it might be empty
   * @throws AndroidException if target is null or empty and also if predicate is null
   */
  public static <T> Collection<T> filter(Collection<T> target, IPredicate<T> predicate) {
    if(target == null || target.isEmpty()) throw new AndroidException("target is empty or null");
    if(predicate == null) throw new AndroidException("predicate is null, can't apply filter");
    Collection<T> result = new ArrayList<>();
    for (T item : target) {
      if(predicate.apply(item)) {
        result.add(item);
      }
    }
    return result;
  }

  /**
   * try to return index of a list inside another list
   *
   * @param source source for search in
   * @param search item to be searched
   * @param <T> type of items
   * @return -1 if nothing found else index
   */
  public static <T> int indexOfSubList(List<T> source, Collection<?> search) {
    return java.util.Collections.indexOfSubList(source, (List<?>) search);
  }

  /**
   * checks Collection&alt;T> is null or empty
   * @param collection collection instance
   * @param <T> type
   * @return true if empty or null false else otherwise
   */
  public static <T> boolean isNullOrEmpty(Collection<T> collection) {
    return collection == null || collection.isEmpty();
  }

  /**
   * filter logic implementation
   * @param <T> Type
   */
  public interface IPredicate<T> {
      /**
       * filtering logic you implement in here
       * @param type T type of object
       * @return true or false
       */
      boolean apply(T type);
  }
}
