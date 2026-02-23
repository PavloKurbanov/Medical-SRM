package menu.inputReader;

import entity.Specialization;
import util.DataTimeFormat;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class InputReader {
    private final Scanner scanner;

    public InputReader() {
        this.scanner = new Scanner(System.in);
    }

    public String readString(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }

    public Integer readInt(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                String input = scanner.nextLine();
                return Integer.parseInt(input);
            } catch (IllegalArgumentException e) {
                System.err.println("Введіть число!");
            }
        }
    }

    public LocalDateTime readDateTime(String prompt) {
        System.out.println(prompt);
        LocalDateTime dateTime = null;

        while (dateTime == null) {
            try {
                dateTime = LocalDateTime.parse(scanner.nextLine(), DataTimeFormat.FORMATTED);
            } catch (IllegalArgumentException | DateTimeException e) {
                System.err.println("ПОМИКА: " + e.getMessage());
            }
        }
        return dateTime;
    }

    public Specialization readSpecialization(String prompt) {
        Specialization[] specializations = Specialization.values();
        Specialization specialization = null;

        do {
            System.out.println(prompt);
            for (int i = 0; i < specializations.length; i++) {
                System.out.println((i + 1) + ". " + specializations[i].getSpecialization());
            }
            int specializationNumber = Integer.parseInt(scanner.nextLine());
            if (specializationNumber >= 1 && specializationNumber <= specializations.length) {
                specialization = specializations[specializationNumber - 1];
            }
        } while (specialization == null);
        return specialization;
    }
}