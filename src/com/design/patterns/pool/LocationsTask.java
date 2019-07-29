package com.design.patterns.pool;

import java.util.concurrent.Callable;

public class LocationsTask implements Callable<String> {

  private ObjectPool<AccessPointLocations> taskPool;
  private String postalCode;

  public LocationsTask(ObjectPool<AccessPointLocations> taskPool, String postalCode) {
    this.taskPool = taskPool;
    this.postalCode = postalCode;
  }

  @Override
  public String call() throws Exception {
    System.out.println("Starting with process....." + postalCode);

    String retValue = null;
    AccessPointLocations accessPointLocations = taskPool.pullFromPool();
    retValue = accessPointLocations.fetchAccessPoints(this.postalCode);
    System.out.println("returning process with name = " + accessPointLocations.getProcessNo() + " to the pool");
    taskPool.returnToPool(accessPointLocations);
    return retValue;
  }

}
