package com.design.patterns.pool;

import java.util.Iterator;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

public abstract class ObjectPool<T> {

  private ConcurrentLinkedQueue<T> pool;

  private int maxObjects = 0;
  private int minObjects = 0;

  private ScheduledExecutorService executorService;

  protected AtomicLong processNo;


  private ObjectPool() {
  }

  public ObjectPool(final int minObjects, final int maxObjects) {
    this.minObjects = minObjects;
    this.maxObjects = maxObjects;
    processNo = new AtomicLong(0);
    initialize();
    attachShutDownHook();
  }

  public T pullFromPool() {
    T objectRequested = null;
    if (pool != null) {
      objectRequested = pool.poll();
    }
    if (objectRequested == null) {
      objectRequested = createObject();
    }
    return objectRequested;
  }

  public void returnToPool(T object) {
    if (object != null && pool != null) {
      System.out.println(
          "returning object " + ((AccessPointLocations) object).getProcessNo() + " to the pool");
      pool.offer(object);
    }
  }


  private void initialize() {
    if (minObjects > 0) {
      pool = new ConcurrentLinkedQueue<>();
      for (int counter = 0; counter < this.minObjects; counter++) {
        pool.add(createObject());
      }
    }
  }

  public abstract T createObject();

  public abstract void destroyObject(T object);

  public void attachShutDownHook() {
    Runtime.getRuntime().addShutdownHook(new Thread() {
      @Override
      public void run() {
        if (pool != null) {
          Iterator iterator = pool.iterator();
          while (iterator.hasNext()) {
            destroyObject(pool.poll());
            iterator.next();
          }
        }
      }
    });
  }

  public ObjectPool(final int minObjects, final int maxObjects, final long validationInterval)
      throws Exception {

    if (minObjects < 1 || maxObjects < minObjects) {
      throw new Exception("Invalid configuration");
    }

    this.maxObjects = maxObjects;
    this.minObjects = minObjects;
    processNo = new AtomicLong(0);

    initialize();
    executorService = Executors.newSingleThreadScheduledExecutor();
    executorService.scheduleWithFixedDelay(new Runnable() {
      @Override
      public void run() {
        System.out.print(">" + pool.size() + "<");
        int size = pool.size();

        if (size < minObjects) {
          int sizeToBeAdded = minObjects - size;
          for (int i = 0; i < sizeToBeAdded; i++) {
            pool.add(createObject());
          }
        } else if (size > maxObjects) {
          int sizeToBeRemoved = size - maxObjects;
          for (int i = 0; i < sizeToBeRemoved; i++) {
            destroyObject(pool.poll());
          }
        }
      }
    }, validationInterval, validationInterval, TimeUnit.SECONDS);
    attachShutDownHook();

  }


}
