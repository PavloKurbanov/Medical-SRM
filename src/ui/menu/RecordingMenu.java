package ui.menu;

import service.AppointmentService;
import service.DoctorService;
import service.PatientService;
import ui.inputReader.InputReader;
import ui.processor.Processor;

import java.util.Map;

public class RecordingMenu {
    private final InputReader inputReader;
    private final Map<String, Processor> processors;


    public RecordingMenu(InputReader inputReader, AppointmentService appointmentService, DoctorService doctorService, PatientService patientService) {
        this.inputReader = inputReader;
        RecordingMenuBuilder recordingMenuBuilder = new RecordingMenuBuilder(inputReader, appointmentService, doctorService, patientService);
        this.processors = recordingMenuBuilder.showMenu();

    }

    public void showMenu() {
        while (true) {
            System.out.println("1) Показати всі записи");
            System.out.println("2) Показати всі записи лікаря");
            System.out.println("3) Показати всі записи пацієнта");
            System.out.println("0) Повернутись до меню");

            String string = inputReader.readString("Ваш вибір: ");

            if(string.equals("0")) {
                return;
            } else {
                Processor processor = processors.get(string);
                if(processor != null) {
                    processor.process();
                }  else {
                    System.err.println("Оберіть дію зі списку!");
                }
            }
        }
    }
}