package org.markjay.services.logging;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author <a href="mailto:mark.jay.mk@gmail.com">mark jay</a>
 * @since 1/6/18 8:18 PM
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class ColoredString {
    private String string;
    private Color color;
    public static ColoredString colored(Color color, String string) {
        return new ColoredString(string, color);
    }

    public static ColoredString noColor(String string) {
        return new ColoredString(string, Color.ANSI_RESET);
    }
}
