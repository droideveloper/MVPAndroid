/*
 * Galleon_Workplace Copyright (C) 2016 Fatih.
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
package org.fs.data;

public class ValidationResult<T> {

  public final static int VALID     = 0x01;
  public final static int INVALID   = 0x02;

  protected final T   value;
  protected final int validation;

  public ValidationResult(T value, int validation) {
    this.value = value;
    this.validation = validation;
  }

  public T value() {
    return this.value;
  }

  public int validation() {
    return this.validation;
  }

  public static <T> ValidationResult<T> invalid() {
    return new ValidationResult<>(null, INVALID);
  }
}
