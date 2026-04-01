package ui.menu;

import service.DoctorService;
import service.PatientService;
import ui.annotation.menuAnnotation.MenuGroup;
import ui.inputReader.InputReader;
import ui.annotation.menuAnnotation.menuRegistry.MenuRegistry;
import ui.processor.Processor;
import ui.processor.doctor.RegistrationDoctorProcessor;
import ui.processor.patient.RegistrationPatientProcessor;

import java.util.List;
import java.util.Map;

public class RegistrationMenuBuilder {
    private final InputReader inputReader;
    private final DoctorService doctorService;
    private final PatientService patientService;

    public RegistrationMenuBuilder(InputReader inputReader, DoctorService doctorService, PatientService patientService) {
        this.inputReader = inputReader;
        this.doctorService = doctorService;
        this.patientService = patientService;
    }

    public Map<String, Processor> showMenu() {

        Processor registerDoctor = new RegistrationDoctorProcessor(inputReader, doctorService);
        Processor registerPatient = new RegistrationPatientProcessor(inputReader, patientService);

        List<Processor> register = List.of(registerDoctor, registerPatient);

        return MenuRegistry.buildMenu(register, MenuGroup.REGISTRATION);
    }
}