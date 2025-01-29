package edu.school21.PreProcessor;

public class PreProcessorToLower implements PreProcessor {

    public PreProcessorToLower() {}

    @Override
    public String conversionProcess(String input) {
        return input.toLowerCase();
    }
}
