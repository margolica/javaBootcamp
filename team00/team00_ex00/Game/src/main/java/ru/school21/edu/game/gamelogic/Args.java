package ru.school21.edu.game.gamelogic;

import com.beust.jcommander.IParameterValidator;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;
import ru.school21.edu.game.exception.IllegalParametersException;

import java.util.ArrayList;

@Parameters(separators = "=", commandDescription = "Record changes to the repository")
public class Args {
    @Parameter
    private ArrayList<String> parameters = new ArrayList<>();

    @Parameter(names = "--enemiesCount", required = true, description = "The number of enemies on the playing field", validateWith = Validator.class)
    private int enemiesCount;

    @Parameter(names = "--wallsCount", required = true, description = "The number of obstacles on the playing field", validateWith = Validator.class)
    private int wallsCount;

    @Parameter(names = "--size", required = true, description = "The size of the playing field", validateWith = Validator.class)
    private int sizeFiled;

    @Parameter(names = "--profile", required = true, description = "The mode of operation of the application", validateWith = Validator.class)
    private String profile;

    public int getEnemiesCount() {
        return enemiesCount;
    }

    public int getWallsCount() {
        return wallsCount;
    }

    public int getSizeFiled() {
        return sizeFiled;
    }

    public void setEnemiesCount(int enemiesCount) {
        this.enemiesCount = enemiesCount;
    }

    public void setWallsCount(int wallsCount) {
        this.wallsCount = wallsCount;
    }

    public void setSizeFiled(int sizeFiled) {
        this.sizeFiled = sizeFiled;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public String getProfile() {
        return profile;
    }

    public static class Validator implements IParameterValidator {
        @Override
        public void validate(String name, String valueIO) throws com.beust.jcommander.ParameterException {
            if (isNumericParameter(name)) {
                validateNumericParameter(name, valueIO);
            } else if (name.equals("--profile")) {
                validateProfileParameter(valueIO);
            }
        }

        private boolean isNumericParameter(String name) {
            return name.equals("--enemiesCount") || name.equals("--wallsCount") || name.equals("--size");
        }

        private void validateNumericParameter(String name, String valueIO) {
            int value = parseInteger(valueIO, name);
            if ((name.equals("--enemiesCount") || name.equals("--wallsCount")) && value < 0) {
                throw new IllegalParametersException("Количество " + name + " не может быть меньше нуля.");
            }
            if (name.equals("--size") && (value < 2 || value > 80)) {
                throw new IllegalParametersException("Размер игрового поля должен быть от 2 до 80.");
            }
        }

        private int parseInteger(String valueIO, String name) {
            try {
                return Integer.parseInt(valueIO);
            } catch (NumberFormatException e) {
                throw new IllegalParametersException("Значение для параметра " + name + " должно быть целым числом.");
            }
        }

        private void validateProfileParameter(String valueIO) {
            if (!valueIO.equals("production") && !valueIO.equals("develop")) {
                throw new IllegalParametersException("Выбран неверный режим запуска программы: " + valueIO);
            }
        }
    }
}