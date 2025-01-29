package edu.school21.Renderer;

import edu.school21.PreProcessor.PreProcessor;

public class RendererStandardImpl implements Renderer {

    private PreProcessor preProcessor;

    public RendererStandardImpl(PreProcessor preProcessor) {
        this.preProcessor = preProcessor;
    }

    @Override
    public void render(String input) {
        System.out.println(preProcessor.conversionProcess(input));
    }
}
