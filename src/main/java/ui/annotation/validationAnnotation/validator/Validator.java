package ui.annotation.validationAnnotation.validator;

import ui.annotation.validationAnnotation.NoBlank;
import ui.annotation.validationAnnotation.NotNull;

import java.lang.reflect.Field;

public class Validator {
    public static void validator(Object o) {
        Class<?> aClass = o.getClass();
        Field[] declaredFields = aClass.getDeclaredFields();

        for (Field field : declaredFields) {
            field.setAccessible(true);

            try {
                Object object = field.get(o);

                NoBlank noBlank = field.getAnnotation(NoBlank.class);
                if (noBlank != null) {
                    if (object == null || object.toString().isBlank()) {
                        throw new IllegalArgumentException(noBlank.message());
                    }
                }

                NotNull notNull = field.getAnnotation(NotNull.class);
                if(notNull != null){
                    if(object == null){
                        throw new IllegalArgumentException(notNull.message());
                    }
                }


            } catch (IllegalAccessException e) {
                throw new RuntimeException("Помилка доступу до поля", e);
            }
        }
    }
}
