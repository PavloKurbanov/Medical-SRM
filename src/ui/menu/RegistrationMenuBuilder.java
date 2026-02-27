package ui.menu;

import service.DoctorService;
import service.PatientService;
import ui.inputReader.InputReader;
import ui.processor.Processor;
import ui.processor.doctor.RegistrationDoctorProcessor;
import ui.processor.patient.RegistrationPatientProcessor;

import java.util.HashMap;
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
        Map<String, Processor> menu = new HashMap<>();

        Processor registerDoctor = new RegistrationDoctorProcessor(inputReader, doctorService);
        Processor registerPatient = new RegistrationPatientProcessor(inputReader, patientService);

        menu.put(registerDoctor.choice(), registerDoctor);
        menu.put(registerPatient.choice(), registerPatient);

        return menu;
    }
}