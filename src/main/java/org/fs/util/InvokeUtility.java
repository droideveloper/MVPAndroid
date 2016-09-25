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
package org.fs.util;

import java.util.Locale;
import org.fs.exception.AndroidException;
import rx.functions.Action0;
import rx.functions.Func0;
import rx.functions.Func1;

public final class InvokeUtility {

  private InvokeUtility() {
    throw new AndroidException(
        String.format(Locale.ENGLISH, "you can not have instance of '%s'.class", InvokeUtility.class.getSimpleName())
    );
  }

  public static <T> T invoke(Func0<T> func) {
    PreconditionUtility.checkNotNull(func);
    return func.call();
  }

  public static <T, P> T invoke(Func1<P, T> func, P p) {
    PreconditionUtility.checkNotNull(func);
    return func.call(p);
  }

  public static void invoke(Action0 act) {
    act.call();
  }
}
