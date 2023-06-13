package com.akkulov.factory_method;

import java.time.LocalDateTime;

/**
 * По сути шаблон возвращает конкретный экземпляр конкретного объекта.
 */
public class FactoryMethodApp {

  public static void main(String[] args) {
    WatchMaker watchMaker = createWatchMakerByWatchType("Roman");
    var watch = watchMaker.createWatch(); // этот код теперь будет неизменным, а выше строчкой можно просто менять производителя
    watch.showTime();
    // то есть мы не меняем клиентский код, а создаем подклассы для создания разного вида часов и работаем с ними
  }

  // вспомогательный метод(пропускай)
  private static WatchMaker createWatchMakerByWatchType(@SuppressWarnings("SameParameterValue") String watchType) {
    if (watchType == null) {
      throw new IllegalArgumentException();
    }

    if (watchType.equalsIgnoreCase("Electric")) {
      return new ElectricWatchMaker();
    } else if (watchType.equalsIgnoreCase("Roman")) {
      return new RomanWatchMaker();
    }

    throw new IllegalArgumentException("Not valid watch type");
  }
}

// пусть есть интерфейс часов с методом для показа времени. И есть несколько имплементаций снизу
interface Watch {

  void showTime();
}

// реализация электронных часов.
class ElectricWatch implements Watch {

  @Override
  public void showTime() {
    System.out.println(LocalDateTime.now());
  }
}

// допустим реализация часов с римским временем в консоли.
class RomanWatch implements Watch {

  @Override
  public void showTime() {
    System.out.println("VII-XV");
  }
}

// Нам нужен общий интерфейс-производитель, который будет создавать эти классы.
// Возвращаем объект 'Watch'.
interface WatchMaker {

  Watch createWatch();
}

// реализация производителя электронных часов.
class ElectricWatchMaker implements WatchMaker {

  @Override
  public Watch createWatch() {
    return new ElectricWatch();
  }
}

// реализация производителя римских часов.
class RomanWatchMaker implements WatchMaker {

  @Override
  public Watch createWatch() {
    return new RomanWatch();
  }
}


