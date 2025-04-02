package Callback_field;
import java.util.Random;

//Callback через поле и конструктор

//1. Интерфейс (контракт с методом обратного вызова)
//2. Класс-реализация (конкретная логика callback'а)
//3. Класс-исполнитель:
//  - 3.1 Поле типа интерфейса (для хранения callback'а)
//  - 3.2 Конструктор
//  - 3.3 Метод, который вызывает callback в нужный момент
//4. Использование:
//  - 4.1 Создаём объект реализации (2)
//  - 4.2 Создаём объект исполнителя (3), передавая ему callback (объект реализации 4.1)
//  - 4.3 Вызываем метод исполнителя (3.3)


//1. Интерфейс- callback (определяет метод обратного вызова)
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
    private final MessageCallback callback;  // 3.1 Поле для хранения callback'а

    WeatherSensor(MessageCallback callback) {  // Получаем callback через конструктор
        this.callback = callback;
    }

    void start() {  // 3.2 Метод, вызывающий callback
        for (int i = 0; i < 10; i++) {
            final Random random = new Random();
            final int sensorTimeToSend = random.nextInt(3); //Имитация работы датчика
            final int temperature = random.nextInt(30);
            if (sensorTimeToSend == 0) {
                this.callback.onMessageSend(temperature, i);  // Вызов callback
                // С this: явно указываем, что обращаемся к полю класса, но можно и без него
            }
        }
    }

    //4. Использование:
    static class Praktikum {
        public static void main(String[] args) {
            final MessageHandler messageHandler = new MessageHandler(); // Создаём реализацию 2
            final WeatherSensor weatherSensor = new WeatherSensor(messageHandler);  // Создаём исполнителя 3 передавая ему callback (объект реализации 4.1)

            weatherSensor.start();  // Теперь вызываем без параметров
        }
    }
}
