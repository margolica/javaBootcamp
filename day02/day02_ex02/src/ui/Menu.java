package ui;

import exception.PathIsNotDirectoryException;
import exception.PathIsNotFileException;

import java.io.File;
import java.nio.file.Path;
import java.util.Scanner;

public class Menu {
    public void start(String[] argc) {
        if (checkInputArgument(argc)) {
            File baseDirectory = new File((argc[0].split("="))[1]);
            if (!baseDirectory.isDirectory())
                throw new PathIsNotDirectoryException("Указанный путь не является директорией");
            else
                menuCommand(baseDirectory);
        }
    }

    private static void menuCommand(File baseDirectory) {
        while (true) {
            String[] inputCommand = new Scanner(System.in).nextLine().split(" ");
            if (inputCommand[0].equals("ls") && inputCommand.length == 1)
                list(baseDirectory);
            else if (inputCommand[0].equals("mv") && inputCommand.length == 3)
                move(inputCommand, baseDirectory);
            else if (inputCommand[0].equals("cd") && inputCommand.length == 2)
                baseDirectory = changeDirectory(inputCommand, baseDirectory);
            else if (inputCommand[0].equals("exit") && inputCommand.length == 1)
                exitProgram();
            else
                System.out.println("Введена неверная команда. Для завершения работы введите exit");
        }
    }

    private static boolean parserCommand(String[] inputCommand) {
        boolean result = false;
        if ((inputCommand[0].matches("(ls|exit)") && inputCommand.length == 1) ||
                (inputCommand[0].matches("(cd)") && inputCommand.length == 2) ||
                (inputCommand[0].matches("(mv)") && inputCommand.length == 3))
            result = true;
        return result;
    }

    public static void list(File baseDirectory) {
        File[] listFile = baseDirectory.listFiles();
        assert listFile != null;
        for (File file : listFile) System.out.format("%s %s Кб\n", file.getName(), file.length() / 1024);
    }

    public static void move(String[] inputCommand, File baseDirectory) {
        File file = new File(baseDirectory, inputCommand[1]);
        File destFolder = new File(baseDirectory, inputCommand[2]);
        if (!file.isFile() && destFolder.isDirectory())
            throw new PathIsNotFileException("Указанный путь не является файлом или директорией");
        file.renameTo(new File(destFolder, file.getName()));
    }

    public static File changeDirectory(String[] inputCommand, File baseDirectory) {
        Path pathFile = baseDirectory.toPath();
        Path resolve = pathFile.resolve(inputCommand[1]);
        if (resolve.toFile().isDirectory())
            return resolve.toFile();
        else
            return pathFile.toFile();
    }

    public static boolean checkInputArgument(String[] argc) {
        boolean result = false;
        if ((argc.length == 1 && argc[0].contains("--current-folder=")))
            result = true;
        else
            System.out.println("Введена не верная команда. Укажите --current-folder=PATH для выбора базовой директории");
        return result;
    }

    public static void exitProgram() {
        System.out.println("exit");
        System.exit(1);
    }
}
