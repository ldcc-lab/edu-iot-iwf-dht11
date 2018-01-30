롯데 IoT 플랫폼 - 교육용 예제 #1 (온도 센서)
=============

#### 0. 운영환경 : 라즈베리파이

#### 1. 센서 : DHT11 Sensor Module (Digital Humidity Temperature)

* 정격 전압 : +5V (3.5V ~ 5.5VDC)
* 온도 범위 : 0~50˚C ± 2˚C
* 습도 범위 : 20~90% RH ± 5%

#### 2. 실행

````
java -jar -DhomePath="/home/pi/conf" SensorDevice.jar