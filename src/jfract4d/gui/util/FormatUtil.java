package jfract4d.gui.util;

import java.util.regex.Pattern;
import javafx.scene.control.TextFormatter;

/**
 *
 * @author Antoine Gagnon
 */
public class FormatUtil {

    public static TextFormatter<Character> getIntegerTextFormatter() {
        return new TextFormatter<>(null, null, c -> Pattern.matches("\\d*", c.getText()) ? c : null);
    }
}
