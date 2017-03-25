/*
 * MVP Android Copyright (C) 2017 Fatih.
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

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import org.fs.common.ViewType;

public final class RxUtility {

  public static <T> ObservableTransformer<T, T> toAsync() {
    return new ObservableTransformer<T, T>() {
      @Override public ObservableSource<T> apply(Observable<T> source) {
        return source
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread());
      }
    };
  }

  public static <T> ObservableTransformer<T, T> toAsyncAndUI(final ViewType view) {
    return new ObservableTransformer<T, T>() {
      @Override public ObservableSource<T> apply(Observable<T> source) {
        return source
            .compose(RxUtility.<T>toAsync())
            .doOnSubscribe(new Consumer<Disposable>() {
              @Override public void accept(@NonNull Disposable disposable) throws Exception {
                if (view.isAvailable()) {
                  view.showProgress();
                }
              }
            }).doFinally(new Action() {
              @Override public void run() throws Exception {
                if (view.isAvailable()) {
                  view.hideProgress();
                }
              }
            });
      }
    };
  }


  private RxUtility() {
    throw new RuntimeException("You can not have instance of this type");
  }

}