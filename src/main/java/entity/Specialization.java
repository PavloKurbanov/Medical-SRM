package entity;

public enum Specialization {
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

    private final String specialization;

    Specialization(String specialization) {
        this.specialization = specialization;
    }

    public String getSpecialization() {
        return specialization;
    }

    public static Specialization getSpecialization(String specialization) {
        for (Specialization value : Specialization.values()) {
            if(value.getSpecialization().equalsIgnoreCase(specialization)){
                return value;
            }
        }
        throw new IllegalArgumentException("Невідома спеціалізація: " + specialization);
    }
}