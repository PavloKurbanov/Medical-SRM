package ui.processor.liveQueue;

import entity.Patient;
import service.impl.LiveQueueService;
import ui.annotation.MenuGroup;
import ui.annotation.MenuItem;
import ui.inputReader.InputReader;
import ui.processor.Processor;
@MenuItem(action = "4", description = "Жива черга")
public class LiveQueueProcessor implements Processor {
    private final LiveQueueService liveQueueService;
    private final InputReader inputReader;

    public LiveQueueProcessor(LiveQueueService liveQueueService, InputReader inputReader) {
        this.liveQueueService = liveQueueService;
        this.inputReader = inputReader;
    }

    @Override
    public void process() {
        while (true) {
            System.out.println("\n--- Травматологія (Жива черга) ---");
            System.out.println("1) Зареєструвати пацієнта");
            System.out.println("2) Викликати наступного пацієнта (кабінет лікаря)");
            System.out.println("0) Повернутись у головне меню");
            System.out.println("Очікують у черзі: " + liveQueueService.getWaitingCount());

            String action = inputReader.readString("Ваш вибір: ");

            switch (action) {
                case "1":
                    String patientName = inputReader.readString("Введіть ім'я пацієнта: ");
                    // Додаємо в кінець черги O(1)
                    liveQueueService.registerPatient(new Patient(null, patientName));
                    System.out.println("Пацієнт " + patientName + " успішно долучився до черги.");
                    break;
                case "2":
                    try {
                        // Лікар викликає першого з черги O(1)
                        Patient nextPatient = liveQueueService.callNextPatient();
                        System.out.println(">>> Лікар оглядає: " + nextPatient.getName() + " <<<");
                    } catch (IllegalStateException e) {
                        // Перехоплюємо нашу помилку, якщо черга порожня
                        System.out.println("ПОМИЛКА: " + e.getMessage());
                    }
                    break;
                case "0":
                    return;
                default:
                    System.err.println("Введіть коректний номер з меню!");
            }

        }
    }
}
