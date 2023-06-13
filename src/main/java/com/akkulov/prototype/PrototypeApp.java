package com.akkulov.prototype;

/**
 * Паттерн проектирования Прототип (Prototype), который рекомендуется применять в тех случаях, когда необходимо создать объект, являющийся
 * копией самого себя.
 */
public class PrototypeApp {

  public static void main(String[] args) {
    Human human = new Human("Omar", 20);

    var copy = human.copy();
    System.out.println(copy);
  }
}

interface Copyable<T> {

  T copy();
}

class Human implements Copyable<Human> {

  private final String name;
  private final int age;

  public Human(String name, int age) {
    this.name = name;
    this.age = age;
  }

  @Override
  public Human copy() {
    return new Human(name, age);
  }

  @Override
  public String toString() {
    return "Human{"
        + "name='" + name + '\''
        + ", age=" + age
        + '}';
  }
}
