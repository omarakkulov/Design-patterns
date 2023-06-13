package com.akkulov.proxy;

/**
 * Цель: для замещения другого объекта и контроля доступа к нему.
 * Используется для обеспечения контроля доступа к определенному объекту.
 */
public class ProxyApp {

  public static void main(String[] args) {
    // при таком коде перед тем как запустить проект мы сначала скачаем его из репозитория по урлу и
    // только после этого идет запуск проекта
//    Project project = new RealProject("https://www.google.com");
//    project.run();

    // теперь применим прокси, когда, например, мы хотим загружать объект из удаленного репозитория не при
    // создании объекта, а при его запуске, у нас будет так сказать ленивая ссылка, когда мы только создадим
    // объект, мы еще не будем иметь готовый проект на локальном пк, укажим ссылку для скачивания и только
    // после запуска этого проекта он начнет скачиваться на компьютер
    Project project = new ProxyProject("https://www.google.com");
    project.run();
  }
}

// пусть есть некий проект на гитхабе, данный проект мы можем скачать и запустить.
// пол этот проект есть интерфейс снизу
interface Project {

  void run();
}

// пусть есть релизация этого проекта
class RealProject implements Project {

  /**
   * Проект имеет ссылку, по которой мы можем его скачать.
   */
  private final String url;

  public RealProject(String url) {
    this.url = url;
    // для запуска проекта здесь мы якобы вызываем метод load() сначала для скачивания проекта из удаленного репозитория,
    // а затем уже запускаем на своем локальном пк.
    load();
  }

  private void load() {
    System.out.println("Скачиваем проект из " + url);
  }

  @Override
  public void run() {
    System.out.println("Запускаем проект из " + url);
  }
}

// класс-прокси
class ProxyProject implements Project {

  private final String url;
  private RealProject realProject;

  public ProxyProject(String url) {
    this.url = url;
  }

  @Override
  public void run() {
    if (realProject == null) {
      realProject = new RealProject(url);
    }
    realProject.run();
  }
}