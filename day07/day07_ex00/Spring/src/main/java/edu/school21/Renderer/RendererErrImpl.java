package edu.school21.Renderer;

import edu.school21.PreProcessor.PreProcessor;

public class RendererErrImpl implements Renderer {

    private PreProcessor preProcessor;

    public RendererErrImpl(PreProcessor preProcessor) {
        this.preProcessor = preProcessor;
    }

    @Override
    public void render(String input) {
        System.err.println(preProcessor.conversionProcess(input));
    }

}
