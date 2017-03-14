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
package org.fs.util;

import java.util.Locale;
import org.fs.exception.AndroidException;

public final class Arrays {

  private Arrays() {
    throw new AndroidException(
        String.format(Locale.ENGLISH, "%s", "no instance allowed, sorry.")
    );
  }

  public static <T> boolean isNullOrEmpty(T[] array) {
    return array == null || array.length <= 0;
  }

  public static <T> T first(T[] array) {
    return isNullOrEmpty(array) ? null : array[0];
  }

  public static <T> T last(T[] array) {
    return isNullOrEmpty(array) ? null : array[array.length - 1];
  }

  public static <T> int size(T[] array) {
    return isNullOrEmpty(array) ? 0 : array.length;
  }
}
