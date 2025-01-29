package edu.school21.printer.logic;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class DrawFile
{
    public static DrawFile instance;
    private static String pathFile;
    private char blackPixel;
    private char whitePixel;

    DrawFile(String[] argc){
        pathFile = argc[2];
        blackPixel = argc[0].charAt(0);
        whitePixel = argc[1].charAt(0);
    }

    public void draw()
    {
        int [][] image2D = null;
        try {
            drawBMP(pathFile);
        } catch (IOException e) {
            throw new RuntimeException("Ошибка чтения потока " + e + pathFile);
        } catch (NullPointerException e) {
            throw new RuntimeException("Ошибка чтения файла " + e);
        }
    }

    private void drawBMP(String pathFile) throws IOException, NullPointerException {
        File file = new File(pathFile);
        BufferedImage image = ImageIO.read(file);

        int[][] image2D = new int[image.getWidth()][image.getHeight()];

        for (int yPixel = 0; yPixel < image.getWidth(); yPixel++)
        {
            for (int xPixel = 0; xPixel < image.getHeight(); xPixel++)
            {
                int color = image.getRGB(xPixel, yPixel);
                if (color== Color.BLACK.getRGB()) {
                    System.out.print(blackPixel);
                } else {
                    System.out.print(whitePixel);
                }
            }
            System.out.print("\n");
        }
    }
}
