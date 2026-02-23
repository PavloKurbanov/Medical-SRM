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
}