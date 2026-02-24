package ui.menu;

import ui.inputReader.InputReader;
import ui.processor.Processor;

import java.util.Map;

public class RecordingMenu {
    private final InputReader inputReader;
    private final Map<String, Processor> processors;


    public RecordingMenu(InputReader inputReader) {
        this.inputReader = inputReader;

    }

    public void showMenu() {
        while (true) {
            System.out.println("1) Показати всі записи");
            System.out.println("2) Показати всі записи лікаря");
            System.out.println("3) Показати всі записи пацієнта");
            System.out.println("0) Повернутись до меню");

            String string = inputReader.readString("Ваш вибір: ");

            if(string == "0") {
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