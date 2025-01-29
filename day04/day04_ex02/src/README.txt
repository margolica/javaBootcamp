Создание директорий
mkdir lib target

Загрузка библиотек
curl -s https://repo1.maven.org/maven2/com/beust/jcommander/1.82/jcommander-1.82.jar -o lib/jcommander-1.82.jar
curl -s https://repo1.maven.org/maven2/com/diogonunes/JColor/5.5.1/JColor-5.5.1.jar -o lib/JColor-5.5.1.jar

Распаковка библиотек
cd target && jar xf ../lib/jcommander-1.82.jar && jar xf ../lib/JColor-5.5.1.jar && cd ..

Компиляция программы
javac -cp lib/JColor-5.5.1.jar:lib/jcommander-1.82.jar: `find ./src -name "*.java"` -d ./target

Копирование resources
cp -R src/main/resources ./target/.

Создание архива
jar cfm ./target/char-printer-from-image.jar src/manifest.txt -C ./target .

Изменение прав доступа
chmod 755 target/char-printer-from-image.jar

Запуск архива
java -jar ./target/char-printer-from-image.jar --white=RED --black=GREEN


rm -rf target && mkdir target && mkdir target/resources && mkdir target/resources/lib

curl -s https://repo1.maven.org/maven2/com/beust/jcommander/1.82/jcommander-1.82.jar -o src/resources/lib/jcommander-1.82.jar
curl -s https://repo1.maven.org/maven2/com/diogonunes/JColor/5.5.1/JColor-5.5.1.jar -o src/resources/lib/JColor-5.5.1.jar

cd target
jar xf ../src/resources/lib/JColor-5.5.1.jar
jar xf ../src/resources/lib/jcommander-1.82.jar
cd ./../

javac -cp src/resources/lib/JColor-5.5.1.jar:src/resources/lib/jcommander-1.82.jar: `find src/main/java/ -name "*.java"` -d ./target/

cp -R src/resources/ex_02.bmp ./target/resources/

jar cfm target/char-printer-from-image.jar src/manifest.txt -C target/ .

chmod 755 target/char-printer-from-image.jar
java -jar ./target/char-printer-from-image.jar --white=RED --black=GREEN