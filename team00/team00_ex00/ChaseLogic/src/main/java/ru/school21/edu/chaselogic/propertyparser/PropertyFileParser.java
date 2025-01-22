package ru.school21.edu.chaselogic.propertyparser;

import com.diogonunes.jcolor.Attribute;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.Properties;

public class PropertyFileParser {
    private final char enemyChar;
    private final char playerChar;
    private final char wallChar;
    private final char goalChar;
    private final char emptyChar;
    private final Attribute enemyColor;
    private final Attribute playerColor;
    private final Attribute wallColor;
    private final Attribute goalColor;
    private final Attribute emptyColor;

    public PropertyFileParser(String filePath) throws IOException {
        Properties properties = new Properties();
        try (InputStream input = getClass().getClassLoader().getResourceAsStream(filePath)) {
            if (input == null) {
                throw new IllegalArgumentException("Properties file not found in the classpath: " + filePath);
            }
            properties.load(input);
        }

        enemyChar = getCharProperty(properties, "enemy.char", 'X');
        playerChar = getCharProperty(properties, "player.char", 'o');
        wallChar = getCharProperty(properties, "wall.char", '#');
        goalChar = getCharProperty(properties, "goal.char", 'O');
        emptyChar = getCharProperty(properties, "empty.char", ' ');

        enemyColor = getColorProperty(properties, "enemy.color", "RED");
        playerColor = getColorProperty(properties, "player.color", "GREEN");
        wallColor = getColorProperty(properties, "wall.color", "MAGENTA");
        goalColor = getColorProperty(properties, "goal.color", "BLUE");
        emptyColor = getColorProperty(properties, "empty.color", "WHITE");
    }

    private char getCharProperty(Properties properties, String key, char defaultValue) {
        String value = properties.getProperty(key);
        return (value != null && !value.isEmpty()) ? value.charAt(0) : defaultValue;
    }

    private Attribute getColorProperty(Properties properties, String key, String defaultValue) {
        String colorName = properties.getProperty(key);
        if (colorName == null || colorName.isEmpty()) colorName = defaultValue;
        return invokeColorMethod(colorName, defaultValue);
    }

    private Attribute invokeColorMethod(String colorName, String defaultValue) {
        String methodName = colorName.toUpperCase() + "_BACK";
        try {
            Method method = Attribute.class.getDeclaredMethod(methodName);
            return (Attribute) method.invoke(null);
        } catch (Exception e) {
            if (!colorName.equals(defaultValue)) {
                return invokeColorMethod(defaultValue, defaultValue);
            }
            throw new IllegalArgumentException("Invalid color: " + colorName, e);
        }
    }

    public char getEnemyChar() {
        return enemyChar;
    }

    public char getPlayerChar() {
        return playerChar;
    }

    public char getWallChar() {
        return wallChar;
    }

    public char getGoalChar() {
        return goalChar;
    }

    public char getEmptyChar() {
        return emptyChar;
    }

    public Attribute getEnemyColor() {
        return enemyColor;
    }

    public Attribute getPlayerColor() {
        return playerColor;
    }

    public Attribute getWallColor() {
        return wallColor;
    }

    public Attribute getGoalColor() {
        return goalColor;
    }

    public Attribute getEmptyColor() {
        return emptyColor;
    }
}