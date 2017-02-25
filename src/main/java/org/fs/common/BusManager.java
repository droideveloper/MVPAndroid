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

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.subjects.PublishSubject;

public final class BusManager {

  private final static BusManager IMPL = new BusManager();

  private final PublishSubject<IEvent> rxBus = PublishSubject.create();

  <T extends IEvent> void post(T event) {
    rxBus.onNext(event);
  }

  Disposable register(Consumer<IEvent> consumer) {
    return rxBus.subscribe(consumer);
  }

  void unregister(Disposable disposable) {
    //if it's null or not unsubscribed then we are good to unregister it
    if(disposable != null && !disposable.isDisposed()) {
      disposable.dispose();
    }
  }

  public static <T extends IEvent> void send(T event) {
    IMPL.post(event);
  }

  public static Disposable add(Consumer<IEvent> consumer) {
    return IMPL.register(consumer);
  }

  public static void remove(Disposable disposable) {
    IMPL.unregister(disposable);
  }
}
