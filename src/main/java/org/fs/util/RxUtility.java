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

import io.reactivex.CompletableTransformer;
import io.reactivex.FlowableTransformer;
import io.reactivex.MaybeTransformer;
import io.reactivex.ObservableTransformer;
import io.reactivex.SingleTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import org.fs.common.ViewType;

public final class RxUtility {

  public static CompletableTransformer toAsyncCompletable() {
    return source -> source
      .subscribeOn(Schedulers.io())
      .observeOn(AndroidSchedulers.mainThread());
  }

  public static CompletableTransformer toAsyncCompletable(final ViewType view) {
    return source -> source
      .compose(toAsyncCompletable())
      .doOnSubscribe(disposable -> {
        if (view.isAvailable()) {
          view.showProgress();
        }
      }).doOnComplete(() -> {
        if (view.isAvailable()) {
          view.hideProgress();
        }
      });
  }

  public static <T> FlowableTransformer<T, T> toAsyncFlowable() {
    return source -> source
      .subscribeOn(Schedulers.io())
      .observeOn(AndroidSchedulers.mainThread());
  }

  public static <T> FlowableTransformer<T, T> toAsyncFlowable(final ViewType view) {
    return source -> source
      .compose(toAsyncFlowable())
      .doOnSubscribe(disposable -> {
        if (view.isAvailable()) {
          view.showProgress();
        }
      }).doOnComplete(() -> {
        if (view.isAvailable()) {
          view.hideProgress();
        }
      }).doAfterNext((data) -> {
        if (view.isAvailable()) {
          view.hideProgress();
        }
      });
  }

  public static <T> SingleTransformer<T, T> toAsyncSingle() {
    return source -> source
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread());
  }

  public static <T> SingleTransformer<T, T> toAsyncSingle(final ViewType view) {
    return source -> source
      .compose(toAsyncSingle())
      .doOnSubscribe(disposable -> {
        if (view.isAvailable()) {
          view.showProgress();
        }
      }).doOnSuccess((data) -> {
        if (view.isAvailable()) {
          view.hideProgress();
        }
      });
  }

  public static <T> MaybeTransformer<T, T> toAsyncMaybe() {
    return source -> source
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread());
  }

  public static <T> MaybeTransformer<T, T> toAsyncMaybe(final ViewType view) {
    return source -> source
      .compose(toAsyncMaybe())
      .doOnSubscribe(disposable -> {
        if (view.isAvailable()) {
          view.showProgress();
        }
      }).doOnComplete(() -> {
        if (view.isAvailable()) {
          view.hideProgress();
        }
      });
  }

  public static <T> ObservableTransformer<T, T> toAsyncObservable() {
    return source -> source
      .subscribeOn(Schedulers.io())
      .observeOn(AndroidSchedulers.mainThread());
  }

  public static <T> ObservableTransformer<T, T> toAsyncObservable(final ViewType view) {
    return source -> source
      .compose(toAsyncObservable())
      .doOnSubscribe(disposable -> {
        if (view.isAvailable()) {
          view.showProgress();
        }
      }).doOnNext((data) -> {
        if (view.isAvailable()) {
          view.hideProgress();
        }
      });
  }

  private RxUtility() {
    throw new RuntimeException("You can not have instance of this type");
  }
}