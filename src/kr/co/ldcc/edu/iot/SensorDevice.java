package kr.co.ldcc.edu.iot;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import comus.wp.onem2m.iwf.common.M2MException;
import comus.wp.onem2m.iwf.run.IWF;

public class SensorDevice {
  //
  Runtime rt = Runtime.getRuntime();
  Process p = null;

  private IWF vDevice;
  private String temperature;
  private String OID = "<OID>";                                                 // 디바이스 식별체계

  private void register() {
    //
    try {
      vDevice = new IWF(OID);                                                   // 1. 환경설정 (OID, conf, log)
    } catch (Exception e) {
      throw new SensorDeviceException(e.getMessage());
    }
    vDevice.register();                                                         // 2. IWF 등록
  }

  private void check() throws InterruptedException {
    //
    if (vDevice != null) {
      while (true) {
        try {
          p = rt.exec("python /home/pi/Desktop/py/dht11_example.py");
          BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
          temperature = br.readLine();
        } catch (Exception e) {
          throw new SensorDeviceException(e.getMessage());
        }
        if (temperature != null && !"".equals(temperature)) {
          vDevice.putContent("tmperature", "text/plain", "" + temperature);     // 센서 데이터 업로드
        } else {
          System.out.print(".");
          continue;
        }
        System.out.println();
        Thread.sleep(10000);
      }
    } else {
      throw new SensorDeviceException("등록을 먼저 진행해 주세요.");
    }
  }

  public static void main(String[] args) throws InterruptedException  {
    //
    SensorDevice sensorDevice = new SensorDevice();

    sensorDevice.register();                                                    // 1. 디바이스 등록
    sensorDevice.check();                                                       // 2. 센서 체크 & 업로드
  }

}
