package com.akkulov.OTHER.immutable_class;

import java.util.HashMap;
import java.util.Map;

/**
 * Неизменяемый (immutable) класс должен быть помечен, как 'final'.
 */
final class Student {

  // поля класса должны быть также помечены как 'private final'
  private final String name;
  private final int regNo;
  private final Map<String, String> metadata;

  // Параметризованный конструктор должен инициализировать все поля, выполняющие глубокое копирование,
  // чтобы элементы данных нельзя было изменить с помощью ссылки на объект
  public Student(String name, int regNo, Map<String, String> metadata) {

    this.name = name;
    this.regNo = regNo;

    // глубокое копирование приходящей хэшмапы или любой другой коллекции путем
    // создания копии этой коллекции в конструкторе
    Map<String, String> tempMap = new HashMap<>();

    // копируем все элементы приходящей коллекции в нашу
    metadata.entrySet().forEach(entry -> tempMap.put(entry.getKey(), entry.getValue()));

    this.metadata = tempMap;
  }

  // Method 1
  public String getName() {
    return name;
  }

  // Method 2
  public int getRegNo() {
    return regNo;
  }

  // Обратите внимание, что в таком классе не должно быть никаких сеттеров


  // Method 3
  // Глубокое копирование объектов должно выполняться в методах получения,
  // чтобы возвращать копию внутреннего объекта, а не возвращать фактическую ссылку на объект.
  public Map<String, String> getMetadata() {

    // мапа на ответ в виде копии нашей внутренней мапы в классе
    Map<String, String> tempMap = new HashMap<>();

    this.metadata.entrySet().forEach(entry -> tempMap.put(entry.getKey(), entry.getValue()));

    return tempMap;
  }
}

class ImmutableClassExample {

  // Main driver method
  public static void main(String[] args) {

    // Creating Map object with reference to HashMap
    Map<String, String> map = new HashMap<>();

    // Adding elements to Map object
    // using put() method
    map.put("1", "first");
    map.put("2", "second");

    Student student = new Student("Omar", 10000, map);

    // Calling the above methods 1,2,3 of class1
    // inside main() method in class2 and
    // executing the print statement over them
    System.out.println(student.getName());
    System.out.println(student.getRegNo());
    System.out.println(student.getMetadata());

    // Раскомментирование приведенной ниже строки приводит к ошибке
    // student.regNo = 102;

    map.put("3", "third");
    // Остается неизменным из-за глубокого копирования в конструкторе
    System.out.println(student.getMetadata());
    student.getMetadata().put("4", "fourth");

    // Остается неизменным из-за глубокого копирования в getter
    System.out.println(student.getMetadata());
  }
}
