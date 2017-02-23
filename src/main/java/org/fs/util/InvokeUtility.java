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

import io.reactivex.functions.Function;
import java.util.Locale;
import java.util.concurrent.Callable;
import org.fs.exception.AndroidException;


public final class InvokeUtility {

  private InvokeUtility() {
    throw new AndroidException(
        String.format(Locale.ENGLISH, "you can not have instance of '%s'.class", InvokeUtility.class.getSimpleName())
    );
  }

  /**
   * Invoke void param func where it returns T
   *
   * @param func function
   * @param <T> type of return
   * @return T instance
   */
  public static <T> T invoke(Callable<T> func) {
    PreconditionUtility.checkNotNull(func);
    try {
      return func.call();
    } catch (Exception err) {
      throw new AndroidException("error occured executing", err);
    }
  }

  /**
   * Invoke P type param func where it returns T
   *
   * @param func function
   * @param p type of param
   * @param <T> type of return
   * @param <P>  type of param
   * @return T instance
   */
  public static <T, P> T invoke(Function<P, T> func, P p) {
    PreconditionUtility.checkNotNull(func);
    try {
      return func.apply(p);
    } catch (Exception err) {
      throw new AndroidException("error occured executing", err);
    }
  }

  /**
   * Invoke void param action where it returns void
   *
   * @param act action
   */
  public static void invoke(Runnable act) {
    act.run();
  }
}
