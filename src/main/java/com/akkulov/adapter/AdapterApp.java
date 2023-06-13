package com.akkulov.adapter;

/**
 * Шаблон проектирования Адаптер
 * Цель: преобразование интерфейса одного класса в интерфейс того класса, который необходим клиенту.
 * Для чего используется: для обеспечения совместной работы классов, интерфейсы которых не совместимы.
 */
public class AdapterApp {

  public static void main(String[] args) {
    GraphicInterface graphicInterface1 = new GraphicFromSpecificRealizationAdapter1();
    graphicInterface1.drawLine();
    graphicInterface1.drawSquare();
    System.out.println();

    GraphicInterface graphicInterface2 = new GraphicFromSpecificRealizationAdapter2();
    graphicInterface2.drawLine();
    graphicInterface2.drawSquare();
  }

}

//пусть есть интерфейс для рисования фигур и клиенту необходимо работать через данный интерфейс
interface GraphicInterface {

  void drawLine();

  void drawSquare();
}

// пусть есть реализация, которая рисует какие-то фигуры
class SpecificGraphicsRealization {

  void drawSpecificLine() {
    System.out.println("Нарисовали линию");
  }

  void drawSpecificSquare() {
    System.out.println("Нарисовали квадрат");
  }

}

// 1. Для того чтобы была реализация данного 'GraphicInterface' интерфейса, которая может рисовать фигуры,
// создадим адаптер класс с помощью наследования от существующей реализации
class GraphicFromSpecificRealizationAdapter1 extends SpecificGraphicsRealization implements GraphicInterface {

  public void drawLine() {
    drawSpecificLine();
  }

  public void drawSquare() {
    drawSpecificSquare();
  }
}


//2. Адаптер с помощью композиции
class GraphicFromSpecificRealizationAdapter2 implements GraphicInterface {

  private final SpecificGraphicsRealization specificGraphicsRealization;

  public GraphicFromSpecificRealizationAdapter2() {
    this.specificGraphicsRealization = new SpecificGraphicsRealization();
  }

  @Override
  public void drawLine() {
    specificGraphicsRealization.drawSpecificLine();
  }

  @Override
  public void drawSquare() {
    specificGraphicsRealization.drawSpecificSquare();
  }
}