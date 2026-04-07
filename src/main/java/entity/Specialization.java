package entity;

public enum Specialization {
    // Константи (це те, що Hibernate запише в базу як STRING)
    DENTIST("Стоматолог"),
    THERAPIST("Терапевт"),
    SURGEON("Хірург"),
    PEDIATRICIAN("Педіатр"),
    CARDIOLOGIST("Кардіолог"),
    NEUROLOGIST("Невролог"),
    DERMATOLOGIST("Дерматолог"),
    OPHTHALMOLOGIST("Офтальмолог"),
    GYNECOLOGIST("Гінеколог"),
    OTOLARYNGOLOGIST("Отоларинголог");

    private final String label;

    // Конструктор для "красивої" назви
    Specialization(String label) {
        this.label = label;
    }

    // Метод для отримання української назви (для UI)
    public String getLabel() {
        return label;
    }

    // Статичний метод для пошуку за українською назвою (якщо треба вводити з консолі)
    public static Specialization fromLabel(String label) {
        for (Specialization s : values()) {
            if (s.label.equalsIgnoreCase(label)) {
                return s;
            }
        }
        throw new IllegalArgumentException("Невідома спеціалізація: " + label);
    }
}