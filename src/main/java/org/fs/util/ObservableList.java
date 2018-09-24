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

import android.support.annotation.NonNull;
import io.reactivex.functions.Predicate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class ObservableList<T> extends ArrayList<T> {

  private final List<T> itemStore = new ArrayList<>();
  private final List<PropertyChangedListener> listeners = new ArrayList<>();

  public final void registerPropertyChangedListener(PropertyChangedListener listener) {
    if (listener != null) {
      boolean alreadyRegistered = listeners.contains(listener);
      if (!alreadyRegistered) {
        listeners.add(listener);
      }
    }
  }

  public final void unregisterPropertyChangedListener(PropertyChangedListener listener) {
    if (listener != null) {
      boolean alreadyRegistered = listeners.contains(listener);
      if (alreadyRegistered) {
        listeners.remove(listener);
      }
    }
  }

  @Override public int size() {
    synchronized (itemStore) {
      return itemStore.size();
    }
  }

  @Override public boolean isEmpty() {
    synchronized (itemStore) {
      return itemStore.isEmpty();
    }
  }

  @Override public boolean contains(Object o) {
    synchronized (itemStore) {
      return itemStore.contains(o);
    }
  }

  @NonNull @Override public Iterator<T> iterator() {
    synchronized (itemStore) {
      return itemStore.iterator();
    }
  }

  @NonNull @Override public Object[] toArray() {
    synchronized (itemStore) {
      return itemStore.toArray();
    }
  }

  @NonNull @Override public <R> R[] toArray(@NonNull R[] a) {
    synchronized (itemStore) {
      return itemStore.toArray(a);
    }
  }

  @Override public boolean add(T t) {
    synchronized (itemStore) {
      boolean success = itemStore.add(t);
      int index = itemStore.indexOf(t);
      if (!listeners.isEmpty()) {
        for(int i = 0, z = listeners.size(); i < z; i++) {
          final PropertyChangedListener listener = listeners.get(i);
          if (listener != null && success) {
            listener.notifyItemsInserted(index, 1);
          }
        }
      }
      return success;
    }
  }

  @Override public boolean remove(Object o) {
    synchronized (itemStore) {
      int index = itemStore.indexOf(o);
      boolean success = itemStore.remove(o);
      if (!listeners.isEmpty()) {
        for(int i = 0, z = listeners.size(); i < z; i++) {
          final PropertyChangedListener listener = listeners.get(i);
          if (listener != null && success) {
            listener.notifyItemsRemoved(index, 1);
          }
        }
      }
      return success;
    }
  }

  @Override public boolean containsAll(Collection<?> c) {
    synchronized (itemStore) {
      return itemStore.containsAll(c);
    }
  }

  @Override public boolean addAll(Collection<? extends T> c) {
    synchronized (itemStore) {
      boolean success = itemStore.addAll(c);
      if (!listeners.isEmpty()) {
        int index = itemStore.size() - c.size();
        int size = c.size();
        for(int i = 0, z = listeners.size(); i < z; i++) {
          final PropertyChangedListener listener = listeners.get(i);
          if (listener != null && success) {
            listener.notifyItemsInserted(index, size);
          }
        }
      }
      return success;
    }
  }

  @Override public boolean addAll(int index, Collection<? extends T> c) {
    synchronized (itemStore) {
      boolean success = itemStore.addAll(c);
      if (!listeners.isEmpty()) {
        int size = c.size();
        for(int i = 0, z = listeners.size(); i < z; i++) {
          final PropertyChangedListener listener = listeners.get(i);
          if (listener != null && success) {
            listener.notifyItemsInserted(index, size);
          }
        }
      }
      return success;
    }
  }

  @Override public boolean removeAll(Collection<?> c) {
    synchronized (itemStore) {
      int index = Collections.indexOfSubList(itemStore, c);
      int size = c.size();
      boolean success = itemStore.removeAll(c);
      if (!listeners.isEmpty()) {
        for(int i = 0, z = listeners.size(); i < z; i++) {
          final PropertyChangedListener listener = listeners.get(i);
          if (listener != null && success) {
            listener.notifyItemsRemoved(index, size);
          }
        }
      }
      return success;
    }
  }

  @Override public boolean retainAll(Collection<?> c) {
    synchronized (itemStore) {
      int index = Collections.indexOfSubList(itemStore, c);
      int size = c.size();
      int total = itemStore.size();
      boolean success = itemStore.retainAll(c);
      if (!listeners.isEmpty()) {
        for(int i = 0, z = listeners.size(); i < z; i++) {
          final PropertyChangedListener listener = listeners.get(i);
          if (listener != null && success) {
            listener.notifyItemsRemoved(0, index);
            listener.notifyItemsRemoved(index + size, total - (index + size));
          }
        }
      }
      return success;
    }
  }

  @Override public void clear() {
    synchronized (itemStore) {
      int size = itemStore.size();
      itemStore.clear();
      if (!listeners.isEmpty()) {
        for(int i = 0, z = listeners.size(); i < z; i++) {
          final PropertyChangedListener listener = listeners.get(i);
          if (listener != null) {
            listener.notifyItemsRemoved(0, size);
          }
        }
      }
    }
  }

  @Override public boolean equals(Object o) {
    synchronized (itemStore) {
      return itemStore.equals(o);
    }
  }

  @Override public int hashCode() {
    synchronized (itemStore) {
      return itemStore.hashCode();
    }
  }

  @Override public T get(int index) {
    synchronized (itemStore) {
      return itemStore.get(index);
    }
  }

  @Override public T set(int index, T element) {
    synchronized (itemStore) {
      T set = itemStore.set(index, element);
      if (!listeners.isEmpty()) {
        for(int i = 0, z = listeners.size(); i < z; i++) {
          final PropertyChangedListener listener = listeners.get(i);
          if (listener != null ) {
            listener.notifyItemsChanged(index, 1);
          }
        }
      }
      return set;
    }
  }

  @Override public void add(int index, T element) {
    synchronized (itemStore) {
      itemStore.add(index, element);
      if (!listeners.isEmpty()) {
        for(int i = 0, z = listeners.size(); i < z; i++) {
          final PropertyChangedListener listener = listeners.get(i);
          if (listener != null ) {
            listener.notifyItemsInserted(index, 1);
          }
        }
      }
    }
  }

  @Override public T remove(int index) {
    synchronized (itemStore) {
      T removed = itemStore.remove(index);
      if (!listeners.isEmpty()) {
        for(int i = 0, z = listeners.size(); i < z; i++) {
          final PropertyChangedListener listener = listeners.get(i);
          if (listener != null ) {
            listener.notifyItemsRemoved(index, 1);
          }
        }
      }
      return removed;
    }
  }

  public int indexOf(Predicate<T> predicate) {
    synchronized (itemStore) {
      for (int i = 0, z = itemStore.size(); i < z; i ++) {
        final T item = itemStore.get(i);
        try {
          if (predicate.test(item)) {
            return i;
          }
        } catch (Exception error) {
          error.printStackTrace();
        }
      }
      return -1;
    }
  }

  @Override public int indexOf(Object o) {
    synchronized (itemStore) {
      return itemStore.indexOf(o);
    }
  }

  @Override public int lastIndexOf(Object o) {
    synchronized (itemStore) {
      return itemStore.lastIndexOf(o);
    }
  }

  @Override public ListIterator<T> listIterator() {
    synchronized (itemStore) {
      return itemStore.listIterator();
    }
  }

  @NonNull @Override public ListIterator<T> listIterator(int index) {
    synchronized (itemStore) {
      return itemStore.listIterator(index);
    }
  }

  @NonNull @Override public List<T> subList(int fromIndex, int toIndex) {
    synchronized (itemStore) {
      return itemStore.subList(fromIndex, toIndex);
    }
  }
}
