package com.akkulov.builder;

public class Employee {

  private final String name;
  private final String surname;
  private final Integer age;

  private Employee(String name, String surname, Integer age) {
    this.name = name;
    this.surname = surname;
    this.age = age;
  }

  @Override
  public String toString() {
    return "Employee{"
        + "name='" + name + '\''
        + ", surname='" + surname + '\''
        + ", age=" + age
        + '}';
  }

  public static Builder builder() {
    return new Builder();
  }

  public static class Builder {

    private String name;
    private String surname;
    private Integer age;

    public Builder name(String name) {
      this.name = name;
      return this;
    }

    public Builder surname(String surname) {
      this.surname = surname;
      return this;
    }

    public Builder age(Integer age) {
      this.age = age;
      return this;
    }

    public Employee build() {
      return new Employee(name, surname, age);
    }
  }
}

class BuilderRunner {

  public static void main(String[] args) {
    var employee = Employee.builder()
        .age(10)
        .name("Omar")
        .surname("Akkulov")
        .build();

    System.out.println(employee);
  }
}
