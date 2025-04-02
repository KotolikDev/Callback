package Callback_parametr;
import java.util.Random;

//Callback как параметр метода

//СХЕМА
//1. Интерфейс (контракт с методом обратного вызова)
//2. Класс-реализация (конкретная логика callback'а)
//3. Класс-исполнитель:
//  - 3.1 Метод, который вызывает callback в нужный момент
//  - 3.2 В метод 3.1 callback передается как параметр ()
//4. Использование:
//  - 4.1 Создаём объект реализации (2)
//  - 4.2 Создаём объект исполнителя (3)
//  - 4.3 Вызываем метод исполнителя (3.2), передавая ему callback (объект реализации 4.1)


//1. Интерфейс-callback (определяет метод обратного вызова)
interface MessageCallback {
    void onMessageSend(final int temperature, final int time);
}

//2. Реализация callback'а (конкретная логика)
class MessageHandler implements MessageCallback {
    @Override
    public void onMessageSend(final int temperature, final int time) {
        System.out.println("Сообщение получено. Время: " + time +
                ". Температура воздуха: " + temperature + ".");
    }
}

//3. Исполнитель (класс, выполняющий операцию):
class WeatherSensor {
    void start(final MessageCallback callback) { //3.2 Метод, вызывающий callback
        //3.1 Содержит поле callback (передается как параметр метода)
        for (int i = 0; i < 10; i++) {
            final Random random = new Random();
            final int sensorTimeToSend = random.nextInt(3); //Имитация работы датчика
            final int temperature = random.nextInt(30);

            if (sensorTimeToSend == 0) {
                callback.onMessageSend(temperature, i);   // Используем callback
            }
        }
    }
}

class Praktikum {
    public static void main(String[] args) {
        final MessageHandler messageHandler = new MessageHandler(); // 4.1 Создаём реализацию 2
        final WeatherSensor weatherSensor = new WeatherSensor();  // 4.2 Создаём исполнителя 3

        weatherSensor.start(messageHandler); //4.3 Передача реализации callback (объекта реализации) исполнителю
    }
}
