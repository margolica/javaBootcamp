# Компиляции кода программы
``` shell
rm -rf target
mkdir target
javac -d ./target src/main/java/edu/school21/printer/*/*.java
```
# Создание архива
``` shell
jar cfm target/images-to-chars-printer.jar src/manifest.txt -C target .
cp -R ./src/main/resources target
```
# Запуск архива
``` shell
java -jar target/images-to-chars-printer.jar 0 1 target/resources/ex_01.bmp
```

rm -rf target
mkdir target
javac -d ./target src/main/java/edu/school21/printer/*/*.java
jar cfm target/images-to-chars-printer.jar src/manifest.txt -C target .
cp -R ./src/main/resources target
java -jar target/images-to-chars-printer.jar 0 1 target/resources/ex_01.bmp
