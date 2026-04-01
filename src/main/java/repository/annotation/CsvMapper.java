package repository.annotation;

import java.lang.reflect.Field;
import java.util.*;

public class CsvMapper {
    public static String toCsvLine(Object object) {
        TreeMap<Integer, Object> map = new TreeMap<>();
        Class<?> aClass = object.getClass();

        CsvTable csvTable = aClass.getAnnotation(CsvTable.class);

        if (csvTable != null) {
            Field[] declaredFields = aClass.getDeclaredFields();
            try {
                for (Field field : declaredFields) {
                    CsvColum csvColum = field.getAnnotation(CsvColum.class);

                    if (csvColum != null) {
                        field.setAccessible(true);
                        Object o = field.get(object);
                        int index = csvColum.index();
                        map.put(index, o);
                    }
                }

                if (map.isEmpty()) {
                    return "";
                }

                List<String> stringList = new ArrayList<>();
                for (Object value : map.values()) {
                    stringList.add(value != null ? value.toString() : "");
                }

                return String.join(",", stringList);

            } catch (RuntimeException | IllegalAccessException e) {
                throw new RuntimeException("Помилка парсингу об'єкта", e);
            }
        }

        throw new IllegalArgumentException("Клас " + aClass.getSimpleName() + " не підтримує збереження у CSV (немає анотації @CsvTable)");
    }


    public static <T> T fromCsvLine(String line, Class<T> clazz) {
        try {
            // 1. Ріжемо рядок по комі ("1,Іван" -> ["1", "Іван"])
            String[] values = line.split(",");

            // 2. Створюємо "пустишку" нашого класу (наприклад, new Patient())
            T instance = clazz.getDeclaredConstructor().newInstance();

            // 3. Перебираємо всі поля класу
            for (Field field : clazz.getDeclaredFields()) {
                CsvColum csvColum = field.getAnnotation(CsvColum.class);

                if (csvColum != null) {
                    int index = csvColum.index();

                    // Захист, якщо у файлі менше колонок, ніж ми очікуємо
                    if (index >= values.length) continue;

                    String stringValue = values[index];
                    field.setAccessible(true);

                    // 4. Міні-бос: Конвертація типів!
                    Class<?> fieldType = field.getType();
                    Object finalValue = null;

                    if (fieldType == String.class) {
                        finalValue = stringValue;
                    } else if (fieldType == Integer.class || fieldType == int.class) {
                        finalValue = Integer.parseInt(stringValue);
                    }
                    // Якщо в майбутньому додаш LocalDateTime чи Enum, треба буде дописати сюди else if

                    // 5. Записуємо значення у нашу пустишку
                    field.set(instance, finalValue);
                }
            }
            return instance;

        } catch (Exception e) {
            throw new RuntimeException("Помилка при читанні об'єкта з CSV: " + line, e);
        }
    }
}