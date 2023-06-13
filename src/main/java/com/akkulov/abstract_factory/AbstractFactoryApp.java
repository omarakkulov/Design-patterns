package com.akkulov.abstract_factory;

/**
 * Абстрактная фабрика содержит фабричный метод. Это паттерн для создания целого семейства похожих объектов.
 * Если фабричный метод - это некий кирпичик для построения, то абстрактная фабрика вернет кирпичи и их виды.
 */
public class AbstractFactoryApp {

  public static void main(String[] args) {
    var factory = getFactoryByCountryCode("Eng");
    var device = factory.getMouse();
    device.click();
  }

  private static DeviceFactory getFactoryByCountryCode(String countryCode) {
    switch (countryCode) {
      case "Eng":
        return new EngDeviceFactory();
      case "Ru":
        return new RuDeviceFactory();
      default:
        throw new IllegalArgumentException();
    }
  }
}

// пусть есть три интерфейса - мышь, клавиатура и тачпад
interface Mouse {

  void click();
}

interface Keyboard {

  void print();
}

interface Touchpad {

  void movePointer(int deltaX, int deltaY);
}

// фабрика девайсов с тремя фабричными методами
interface DeviceFactory {

  Mouse getMouse();

  Keyboard getKeyboard();

  Touchpad getTouchpad();
}

// допустим у нас есть теперь для всех девайсов несколько языковых раскладок - английская и русская
class RuMouse implements Mouse {

  @Override
  public void click() {
    System.out.println("Клик");
  }
}

class EngMouse implements Mouse {

  @Override
  public void click() {
    System.out.println("Click");
  }
}

class RuKeyboard implements Keyboard {

  @Override
  public void print() {
    System.out.println("Принт");
  }
}

class EngKeyboard implements Keyboard {

  @Override
  public void print() {
    System.out.println("Print");
  }
}

class RuTouchpad implements Touchpad {

  @Override
  public void movePointer(int deltaX, int deltaY) {
    System.out.println("Сдвинулись на " + deltaX + ", " + deltaX);
  }
}

class EngTouchpad implements Touchpad {

  @Override
  public void movePointer(int deltaX, int deltaY) {
    System.out.println("Moved to " + deltaX + ", " + deltaX);
  }
}

// и для каждой из языковых раскладок есть своя фабрика для производства девайсов.
// В каждой из этиф фабрик по три фабричных @Override метода
class RuDeviceFactory implements DeviceFactory {

  @Override
  public Mouse getMouse() {
    return new RuMouse();
  }

  @Override
  public Keyboard getKeyboard() {
    return new RuKeyboard();
  }

  @Override
  public Touchpad getTouchpad() {
    return new RuTouchpad();
  }
}

class EngDeviceFactory implements DeviceFactory {

  @Override
  public Mouse getMouse() {
    return new EngMouse();
  }

  @Override
  public Keyboard getKeyboard() {
    return new EngKeyboard();
  }

  @Override
  public Touchpad getTouchpad() {
    return new EngTouchpad();
  }
}
