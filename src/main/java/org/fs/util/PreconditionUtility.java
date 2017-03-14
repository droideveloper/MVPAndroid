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

import org.fs.exception.AndroidException;

public final class PreconditionUtility {

  private PreconditionUtility() {
    throw new AndroidException("no instance for you!");
  }

  public static <T> void checkNotNull(T t, String errorMessage) {
    if(StringUtility.isNullOrEmpty(t)) {
      throw new AndroidException(errorMessage);
    }
  }

  public static <T> void checkNotNull(T t) {
    checkNotNull(t, "object is null or empty.");
  }


  public static void throwIfConditionFails(boolean condition, String errorMessage) {
    if (!condition) {
      throw new AndroidException(errorMessage);
    }
  }
}
