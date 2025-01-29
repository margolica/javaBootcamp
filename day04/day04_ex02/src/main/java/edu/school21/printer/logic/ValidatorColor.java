package edu.school21.printer.logic;

import com.beust.jcommander.IParameterValidator;

import java.awt.*;

public class ValidatorColor implements IParameterValidator {
    @Override
    public void validate(String colorIO, String value) throws com.beust.jcommander.ParameterException {
        Color color = Color.getColor(colorIO.toLowerCase());
        if (color != null)
        {
            try {
                throw new ParameterException("Parameter " + colorIO + " not included in the color palette");
            } catch (ParameterException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
