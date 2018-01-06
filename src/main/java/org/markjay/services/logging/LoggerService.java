package org.markjay.services.logging;

/**
 * @author <a href="mailto:mark.jay.mk@gmail.com">mark jay</a>
 * @since 1/6/18 8:17 PM
 */
public class LoggerService {

    public void print(ColoredString ... coloredStrings) {
        StringBuilder stringBuilder = new StringBuilder();
        for (ColoredString coloredString : coloredStrings) {
            stringBuilder.append(coloredString.getColor().getColorAsString());
            stringBuilder.append(coloredString.getString());
        }
        stringBuilder.append(Color.ANSI_RESET.getColorAsString());
        System.out.println(stringBuilder.toString());
    }

}
