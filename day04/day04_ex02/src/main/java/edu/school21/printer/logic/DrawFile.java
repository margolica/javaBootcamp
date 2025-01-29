package edu.school21.printer.logic;

import com.diogonunes.jcolor.Attribute;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static com.diogonunes.jcolor.Ansi.colorize;

public class DrawFile
{
    static final String PATH_FILE_BMP = "./src/resources/ex_02.bmp";
    public static DrawFile instance;
    private static String pathFile = PATH_FILE_BMP;
    private String blackPixel;
    private String whitePixel;

    DrawFile(Args argc){
        blackPixel = argc.getBlackColor() + "_BACK";
        whitePixel = argc.getWhiteColor() + "_BACK";
    }

    public void draw()
    {
        try {
            drawBMP(pathFile);
        } catch (IOException e) {
            throw new RuntimeException("Ошибка чтения потока " + e + pathFile);
        } catch (NullPointerException e) {
            throw new RuntimeException("Ошибка чтения файла " + e);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException("Запрошенный цвет отсутствует в коллекции " + e);
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException("Метод не существует или не доступен " + e);
        }
    }

    private void drawBMP(String pathFile) throws IOException, NullPointerException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        File file = new File(pathFile);
        BufferedImage image = ImageIO.read(file);

        int[][] image2D = new int[image.getWidth()][image.getHeight()];

        for (int yPixel = 0; yPixel < image.getWidth(); yPixel++)
        {
            for (int xPixel = 0; xPixel < image.getHeight(); xPixel++)
            {
                int color = image.getRGB(xPixel, yPixel);
                if (color <= Color.BLACK.getRGB()) {
                    Method method = Attribute.class.getMethod(blackPixel);
                    System.out.print(colorize("\t", (Attribute) method.invoke(null)));
                } else {
                    Method method = Attribute.class.getMethod(whitePixel);
                    System.out.print(colorize("\t", (Attribute) method.invoke(null)));
                }
            }
            System.out.print("\n");
        }
    }
}
