package com.liucan.loda;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringBootVersion;
import org.springframework.boot.ansi.AnsiColor;
import org.springframework.boot.ansi.AnsiOutput;
import org.springframework.boot.ansi.AnsiStyle;
import org.springframework.core.env.Environment;

import java.io.PrintStream;

/**
 * Loda application banner which writes the 'loda' banner.
 *
 * @author liucan
 */
class LodaApplicationBanner implements Banner {

    private static final String[] BANNER = {"",
            " .----------------.  .----------------.  .----------------.  .----------------.",
            "| .--------------. || .--------------. || .--------------. || .--------------. |",
            "| |   _____      | || |     ____     | || |  ________    | || |      __      | |",
            "| |  |_   _|     | || |   .'    `.   | || | |_   ___ `.  | || |     /  \\     | |",
            "| |    | |       | || |  /  .--.  \\  | || |   | |   `. \\ | || |    / /\\ \\    | |",
            "| |    | |   _   | || |  | |    | |  | || |   | |    | | | || |   / ____ \\   | |",
            "| |   _| |__/ |  | || |  \\  `--'  /  | || |  _| |___.' / | || | _/ /    \\ \\_ | |",
            "| |  |________|  | || |   `.____.'   | || | |________.'  | || ||____|  |____|| |",
            "| |              | || |              | || |              | || |              | |",
            "| '--------------' || '--------------' || '--------------' || '--------------' |",
            " '----------------'  '----------------'  '----------------'  '----------------'"
    };

    private static final String SPRING_BOOT = " :: Spring Boot :: ";

    private static final int STRAP_LINE_SIZE = 42;

    @Override
    public void printBanner(Environment environment, Class<?> sourceClass,
                            PrintStream printStream) {
        for (String line : BANNER) {
            printStream.println(line);
        }
        String version = SpringBootVersion.getVersion();
        version = (version != null) ? " (v" + version + ")" : "";
        StringBuilder padding = new StringBuilder();
        while (padding.length() < STRAP_LINE_SIZE
                - (version.length() + SPRING_BOOT.length())) {
            padding.append(" ");
        }

        printStream.println(AnsiOutput.toString(AnsiColor.GREEN, SPRING_BOOT,
                AnsiColor.DEFAULT, padding.toString(), AnsiStyle.FAINT, version));
        printStream.println();
    }

}
