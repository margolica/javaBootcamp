package edu.school21.printer.logic;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

@Parameters(separators = "=", commandDescription = "Record changes to the repository")
public class Args {
    @Parameter
    private List<String> parameters = new ArrayList<>();

    @Parameter(names = "--white",
            validateWith = ValidatorColor.class,
            description = "The display color of white pixels")
    private String whiteColor;

    @Parameter(names = "--black",
            validateWith = ValidatorColor.class,
            description = "The display color of black pixels")
    private String blackColor;

    public String getWhiteColor() {
        return whiteColor;
    }

    public String getBlackColor() {
        return blackColor;
    }
}

