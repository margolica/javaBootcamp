# Компиляции кода программы

Инструкция для компиляции кода программы для состояния, когда консоль открыта в корневой папке проекта.

1. Скомпилируем исходные фала на языке Java в Bytecode. Для этого в командной строке пропишим следующие команды:
``` shell
rm -rf target
mkdir target
javac -d ./target/ ./src/main/java/edu/school21/printer/*/*.java
```
# Запуск программы
Для запуска программы необходимо прописать следующую команду:
``` shell
java -classpath ./target edu.school21.printer.app.Main 0 1 ./src/main/resources/ex_00.bmp
```