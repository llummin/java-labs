class Source {// Класс источника события
  Event event;

  Source(Event event) {
    this.event = event;
  } // Конструктор

  void genEv() {
    event.handleEvent();
  } // Генерировать событие
}

