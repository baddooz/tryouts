package com.design.patterns.pool;


public class LocationPointsPool extends ObjectPool{




  public LocationPointsPool(int minObjects, int maxObjects) {
    super(minObjects, maxObjects);
    }

  public LocationPointsPool(int minObjects, int maxObjects,final long validationInterval) throws Exception{
    super(minObjects, maxObjects,validationInterval);
  }

  @Override
  public Object createObject() {
    System.out.println(" Creating objects.... " + processNo.get());
    AccessPointLocations acp = new AccessPointLocations("LOC - " + processNo.addAndGet(1));
    return acp;
  }

  @Override
  public void destroyObject(Object object) {
    AccessPointLocations acp = (AccessPointLocations) object;
    System.out.println("destroying ... " + acp.getProcessNo());
    acp = null;
  }
}
