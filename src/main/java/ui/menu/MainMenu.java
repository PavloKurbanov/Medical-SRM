package ui.menu;

import service.AppointmentService;
import service.DoctorService;
import service.PatientService;
import service.impl.LiveQueueService;
import ui.inputReader.InputReader;
import ui.processor.Processor;

import java.util.Map;

public class MainMenu {
    private final InputReader inputReader;
    private final Map<String, Processor>  processors;

    public MainMenu(InputReader inputReader, AppointmentService appointmentService, DoctorService doctorService, PatientService patientService, LiveQueueService liveQueueService) {
        this.inputReader = inputReader;
        MainMenuBuilder mainMenuBuilder = new MainMenuBuilder(inputReader, appointmentService, doctorService, patientService, liveQueueService);
        this.processors = mainMenuBuilder.showMenu();
    }

    public void start(){
        while(true){
            System.out.println("\nЛаскаво просимо до лікарні!");
            System.out.println("1) Адміністрація лікарні");
            System.out.println("2) Записатись до лікаря");
            System.out.println("3) Показати всі записи");
            System.out.println("4) Жива черга");
            System.out.println("0) Вихід");

            String string = inputReader.readString("Ваш вибір: ");

            if(string.equals("0")){
                System.out.println("На все добре!");
                return;
            }else {
                Processor processor = processors.get(string);
                if(processor != null){
                    processor.process();
                } else {
                    System.out.println("Введіть номер з пункту!");
                }
            }
        }
    }
}
