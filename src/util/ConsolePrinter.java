package util;

import java.util.List;

public class ConsolePrinter {
    public static <T> boolean checkIfEmpty(List<T> list, String emptyMessage) {
        if(list.isEmpty()){
            System.out.println(emptyMessage);
            return true;
        }
        return false;
    }

    public static <T> void showList(List<T> list, String header) {
        System.out.println(header);
        list.forEach(System.out::println);
    }
}
