package com.design.patterns.pool;

import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.net.URLEncoder;

public class AccessPointLocations {

  private String processName;


  private String address = "http://ajax.googleapis.com/ajax/services/search/web?v=1.0&q=";
  private String charset = "UTF-8";

  public AccessPointLocations(String processName) {
    this.processName = processName;
  }

  public String fetchAccessPoints(String postalCode) {
    String retValue = null;

    try {

      URL url = new URL(address + URLEncoder.encode(postalCode, charset));
      Reader reader = new InputStreamReader(url.openStream(), charset);
      retValue = reader.toString();
      Thread.sleep(100);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return retValue;
  }

  public String getProcessNo() {
    return processName;
  }

}
