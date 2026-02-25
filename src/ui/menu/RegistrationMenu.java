package ui.menu;

import service.DoctorService;
import service.PatientService;
import ui.inputReader.InputReader;
import ui.processor.Processor;

import java.util.Map;

public class RegistrationMenu {
    private final InputReader inputReader;
    private final Map<String, Processor> menu;

    public RegistrationMenu(InputReader inputReader, DoctorService doctorService, PatientService patientService) {
        this.inputReader = inputReader;
        RegistrationMenuBuilder registrationMenuBuilder = new RegistrationMenuBuilder(inputReader, doctorService, patientService);
        this.menu= registrationMenuBuilder.showMenu();
    }

    public void show(){
        while(true){
            System.out.println("1) Додати лікаря");
            System.out.println("2) Додати пацієнта");
            System.out.println("0) Повернутись до меню");

            String string = inputReader.readString("Ваш вибір: ");

            if(string.equals("0")){
                return;
            } else {
                Processor processor = menu.get(string);

                if(processor != null){
                    processor.process();
                } else {
                    System.err.println("Оберіть дію зі списку!");
                }
            }
        }
    }
}
