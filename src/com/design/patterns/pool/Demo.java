package com.design.patterns.pool;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Demo {

  public static void main(String[] args) throws Exception {
    Demo dmo = new Demo();
    String[] postalCodes = {"L5N1P9", "L5N1P2", "L5N1P3", "L5N1P4", "L5N1P5", "L5N1P6", "L5N1P7",
        "L5N1P8", "L5N1PA", "L5N1PB", "L5N1PC", "L5N1PD"};
    ExecutorService es = Executors.newFixedThreadPool(3);
    try {
      LocationPointsPool locationPointsPool = new LocationPointsPool(1, 2, 3);

      List<Callable<String>> taskList = new ArrayList<>();
      taskList.add(new LocationsTask(locationPointsPool, postalCodes[0]));
      taskList.add(new LocationsTask(locationPointsPool, postalCodes[1]));
      taskList.add(new LocationsTask(locationPointsPool, postalCodes[2]));
      taskList.add(new LocationsTask(locationPointsPool, postalCodes[3]));
      taskList.add(new LocationsTask(locationPointsPool, postalCodes[4]));
      taskList.add(new LocationsTask(locationPointsPool, postalCodes[5]));
      taskList.add(new LocationsTask(locationPointsPool, postalCodes[6]));
      taskList.add(new LocationsTask(locationPointsPool, postalCodes[7]));
      taskList.add(new LocationsTask(locationPointsPool, postalCodes[8]));
      taskList.add(new LocationsTask(locationPointsPool, postalCodes[9]));

      List<Future<String>> furtureTasks = es.invokeAll(taskList);

      for (int i = 0; i < 10; i++) {
        furtureTasks.get(i).get();

      }

      Thread.sleep(8000);

      furtureTasks = es.invokeAll(taskList);

      for (int i = 0; i < 10; i++) {
        furtureTasks.get(i).get();

      }
    } finally {
      es.shutdown();
    }

  }


}
