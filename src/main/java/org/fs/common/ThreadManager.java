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
package org.fs.common;

import android.os.Handler;
import android.os.Looper;

public final class ThreadManager {

  private ThreadManager() {
    throw new NullPointerException("no sugar for ya");
  }

  /**
   * Main Thread of UI
   */
  private final static Handler uiHandler = new Handler(Looper.getMainLooper());

  /**
   * <p>Executes immediately</p>
   * @param thread Runnable instance as block
   */
  public static void runOnUiThread(Runnable thread) {
    if (thread == null) {
      throw new NullPointerException("block is null");
    }
    uiHandler.post(thread);
  }

  /**
   * <p>Executes after delay time</p>
   * @param delay time to delay, if less then zero exception will be thrown
   * @param thread Runnable instance as block
   */
  public static void runOnUiThreadDelayed(long delay, Runnable thread) {
    if (thread == null) {
      throw new NullPointerException("block is null");
    }
    if (delay < 0) {
      throw new IllegalArgumentException("delay can not me smaller than 0");
    }
    uiHandler.postDelayed(thread, delay);
  }
}
