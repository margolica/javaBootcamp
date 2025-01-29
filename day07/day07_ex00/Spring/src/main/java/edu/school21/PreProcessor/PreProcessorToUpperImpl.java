package edu.school21.PreProcessor;

public class PreProcessorToUpperImpl implements PreProcessor {

    public PreProcessorToUpperImpl() {}

    @Override
    public String conversionProcess(String input) {
        return input.toUpperCase();
    }
}
