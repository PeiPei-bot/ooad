# Coffee Maker

> UML 실전에서는 이것만 쓴다 (로버트 C. 마틴 저) - 11장 휴리스틱과 커피 예제

## 요구사항
- 기계 장치에 대한 API (`CoffeeMakerAPI.java`)는 주어짐.
- 기계 장치들을 감시하거나 제어하는 기능 구현 (P.146 참고)

## 잘못 된 구현
- Button, Light, Boiler, Sensor 등 모든 장치마다 클래스를 만듬.
- 설계시 메소드를 명시하지 않고 다이어그램을 그림.
- 객체로서의 기능은 하지 못하고 단순히 자리만 차지하는 허깨비 클래스.
- 아무도 사용하지 않는, 잘못된 추상화.
- 겉보기에는분산된 것처럼 보이는 많은 객체 모델 속에 사실 전지 전능한 하나님 클래스 존재.

## 해결 방안
- 본질과 세부사항을 분리한다. 끓이는 장치, 벨브, 가열기, 감지기 등 온갖 사소한 세부사항은 잠시 잊고 맨 아래 놓인 근본 문제에 집중한다.
    - 어떻게 하면 커피를 끓일 수 있을까?