package ui.menuRegistry;

import ui.annotation.MenuGroup;
import ui.annotation.MenuItem;
import ui.processor.Processor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MenuRegistry {
    public static Map<String, Processor> buildMenu(List<Processor> processors, MenuGroup menuGroup) {
        Map<String, Processor> menu = new HashMap<>();

        for (Processor processor : processors) {
            MenuItem annotation = processor.getClass().getAnnotation(MenuItem.class);

            if(annotation != null &&  annotation.group() == menuGroup) {
                menu.put(annotation.action(), processor);
            }
        }

        return menu;
    }
}
