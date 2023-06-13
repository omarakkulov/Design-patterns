package com.akkulov.singleton;

/**
 * Для гарантирования того, что у класса будет только один единственный экземпляр
 * и к нему будет предоставлена глобальная точка доступа.
 * Например, нужен когда необходим только один экзмепляр конкретного класса, который доступен
 * для всех клиентов. Пример: коннекшн в jdbc
 */
public class SingletonApp {

  public static void main(String[] args) {
    System.out.println(SingletonObject.getSingletonObject());
    System.out.println(SingletonObject.getSingletonObject());
    System.out.println(SingletonObject.getSingletonObject());
    System.out.println(SingletonObject.getSingletonObject());
  }
}

class SingletonObject {

  private static SingletonObject singletonObject;

  private SingletonObject() {
  }

  public static SingletonObject getSingletonObject() {
    if (singletonObject == null) {
      singletonObject = new SingletonObject();
    }
    return singletonObject;
  }
}
