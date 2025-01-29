# Day 06 — Java bootcamp
### JUnit/Mockito

*Takeaways: Today you will learn the basics of module and integration testing.*

💡 [Tap here](https://new.oprosso.net/p/4cb31ec3f47a4596bc758ea1861fb624) **to leave your feedback on the project**. It's anonymous and will help our team make your educational experience better. We recommend completing the survey immediately after the project.

# Contents
1. [Chapter I](#chapter-i) \
  1.1. [Preamble](#preamble)
2. [Chapter II](#chapter-ii) \
  2.1. [General Rules](#general-rules)
3. [Chapter III](#chapter-iii) \
  3.1. [Rules of the Day](#rules-of-the-day)
4. [Chapter IV](#chapter-iv) \
  4.1. [Exercise 00 — First Tests](#exercise-00-first-tests)
5. [Chapter V](#chapter-v) \
  5.1. [Exercise 01 — Embedded DataBase](#exercise-01-embedded-database)
6. [Chapter VI](#chapter-vi) \
  6.1. [Exercise 02 — Test for JDBC Repository](#exercise-02-test-for-jdbc-repository)
7. [Chapter VII](#chapter-vii) \
  7.1. [Exercise 03 — Test for Service](#exercise-03-test-for-service)

# Chapter I
### Preamble
Модульное и интеграционное тестирование позволяет программисту убедиться в корректной работе создаваемых им программ. Эти тесты выполняются автоматически.

Таким образом, ваша цель — не только написать правильный код, но и создать код для проверки валидности вашей реализации.

Модульные тесты в Java — это классы, которые содержат несколько методов тестирования для общедоступных методов тестируемых классов. Каждый класс тестирования модуля проверяет функциональность только одного класса. Такие тесты позволяют выявить ошибки. Для выполнения тестов без конкретных зависимостей используются объекты-заглушки с временными реализациями.

В отличие от модульных тестов, интеграционные тесты позволяют тестировать пакеты разных компонентов.

Вот некоторые рекомендации по модульному и интеграционному тестированию:
1. Используйте подходящие названия для методов тестирования.
2. Рассмотрите разные ситуации.
3. Убедитесь, что тесты покрывают не менее 80% кода.
4. Каждый метод тестирования должен содержать небольшой объем кода и быстро выполняться.
5. Методы испытаний должны быть изолированы друг от друга и не иметь побочных эффектов.

# Chapter II
### General Rules
- Используйте эту страницу как единственную справочную информацию. Не слушайте слухи и домыслы о том, как приготовить свой раствор.
- Для вас есть только одна версия Java, 1.8. Убедитесь, что на вашем компьютере установлены компилятор и интерпретатор этой версии.
- Вы можете использовать IDE для написания и отладки исходного кода.
— Код чаще читают, чем пишут. Внимательно прочитайте [документ](https://www.oracle.com/technetwork/java/codeconventions-150003.pdf), где приведены правила форматирования кода. При выполнении любой задачи обязательно соблюдайте общепринятые [стандарты Oracle](https://www.oracle.com/java/technologies/javase/codeconventions-namingconventions.html).

- Комментарии не допускаются в исходном коде вашего решения. Они затрудняют чтение кода.
- Помните о разрешениях ваших файлов и каталогов.
- Для оценки ваше решение должно находиться в вашем репозитории GIT.
- Ваши решения будут оценены вашими коллегами по буткемпу.
- Вы не должны оставлять в каталоге src никаких файлов, кроме тех, которые явно указаны в инструкциях по упражнению. Во избежание несчастных случаев рекомендуется изменить ваш .gitignore.
- Если вам нужен точный результат в ваших программах, запрещается отображать предварительно рассчитанный результат вместо правильного выполнения упражнения.
- Есть вопрос? Спросите соседа справа. В противном случае попробуйте своего соседа слева.
- Ваш справочник: коллеги/Интернет/Google. И еще одна вещь. На каждый ваш вопрос на Stackoverflow есть ответ. Научитесь правильно задавать вопросы.
- Внимательно прочитайте примеры. Могут требовать вещи, не указанные в теме.
- Используйте System.out для вывода.
- И да пребудет с тобой Сила!
- Никогда не оставляйте на завтра то, что можно сделать сегодня. ;)
# Chapter III
### Rules of the Day
- Используйте фреймворк JUnit 5 во всех задачах.
- Используйте следующие зависимости и плагины для обеспечения правильной работы:
      - maven-surefire-plugin;
    - junit-jupiter-engine;
    - junit-jupiter-params;
    - junit-jupiter-api.
-Все тесты должны выполняться с помощью команды mvn clean compile test.
— Исходный код тестируемого класса должен быть полностью покрыт всеми реализованными тестами. Ниже приведен пример полной демонстрации использования IntelliJ IDEA для упражнения 00:
![test](misc/images/test.png)

# Chapter IV
### Exercise 00 — First Tests

Exercise 00: First Tests ||
---|---
Turn-in directory |	ex00
Files to turn-in |	Tests-folder

Теперь вам нужно реализовать класс NumberWorker, который содержит следующую функциональность:

```java
public boolean isPrime(int number) {
  ...
}

```
Этот метод определяет, является ли число простым, и возвращает значение true/false для всех натуральных (положительных целых) чисел. Для отрицательных чисел, а также 0 и 1 программа выдаст непроверяемое исключение. Исключение IllegalNumberException.```java
public int digitsSum(int number) {
  ...
}


Этот метод возвращает сумму цифр исходного номера.

Нам также необходимо создать класс NumberWorkerTest, реализующий логику тестирования модуля. Методы класса NumberWorkerTest проверят корректность работы методов NumberWorker для разных входных данных:
1. метод isPrimeForPrimes для проверки isPrime с простыми числами (не менее трех);
2. метод isPrimeForNotPrimes для проверки isPrime с составными числами (минимум три);
3. метод isPrimeForIncorrectNumbers для проверки isPrime при использовании неправильных чисел (минимум три);
4. метод проверки суммы цифр по набору не менее 10 чисел.

**Requirements**:
- NКласс umberWorkerTest должен содержать как минимум 4 метода для проверки функциональности NumberWorker.
— Использование @ParameterizedTest и @ValueSource является обязательным для методов 1–3.
- Использование @ParameterizedTest и @CsvFileSource является обязательным для метода 4.
- Для способа 4 необходимо подготовить файл data.csv, в котором указать не менее 10 чисел и их правильную сумму цифр. Пример содержимого файла:
1234, 10

**Project structure**:

- Tests
    - src
        - main
            - java
                 - edu.school21.numbers
                    - NumberWorker
            - resources
        - test
            - java
                - edu.school21.numbers
                    - NumberWorkerTest
            - resources
                -	data.csv
    - pom.xml

# Chapter V
### Exercise 01 — Embedded DataBase

Exercise 01: Embedded DataBase ||
---|---
Turn-in directory |	ex01
Files to turn-in |	Tests

Не используйте тяжелую СУБД (например, PostgreSQL) для реализации интеграционного тестирования компонентов, взаимодействующих с базой данных. Лучше всего создать облегченную базу данных в памяти с заранее определенными данными.  

Реализовать механизм создания DataSource для СУБД HSQL. Для этого добавьте в проект зависимости Spring-jdbc и hsqldb. Подготовьте файлы Schema.sql и data.sql, описывающие структуру таблицы продуктов и тестовые данные (не менее пяти).

Структура таблицы товаров:
- identifier,
- name,
- price.

Также создайте класс EmbeddedDataSourceTest. В этом классе реализуйте метод init(), отмеченный аннотацией @BeforeEach. В этом классе реализуйте функциональность для создания источника данных с помощью EmbeddedDataBaseBuilder (класс в библиотеке Spring-jdbc). Реализуйте простой тестовый метод для проверки возвращаемого значения метода getConnection(), созданного DataSource (это значение не должно быть нулевым).

**Project structure**:

- Tests
    - src
        - main
            - java
                - edu.school21.numbers
                    - NumberWorker
            - resources
        - test
            - java
                - edu.school21
                    - numbers
                        - NumberWorkerTest
                    - repositories
                        - EmbeddedDataSourceTest
            - resources
                -	data.csv
                -	schema.sql
                -	data.sql
    - pom.xml

# Chapter VI
### Exercise 02 — Test for JDBC Repository

Exercise 02: Test for JDBC Repository ||
---|---
Turn-in directory |	ex02
Files to turn-in |	Tests

Implement ProductsRepository/ProductsRepositoryJdbcImpl interface/class в сочетании со следующими методами:

```java
List<Product> findAll()

Optional<Product> findById(Long id)

void update(Product product)

void save(Product product)

void delete(Long id)
```
Вы реализуете класс ProductsRepositoryJdbcImplTest, который содержит методы для тестирования функциональности репозитория с использованием базы данных в памяти, упомянутой в предыдущем упражнении. В этом классе следует заранее подготовить объекты модели, которые будут использоваться для сравнения во всех тестах.

Пример объявления тестовых данных показан ниже:
```java
class ProductsRepositoryJdbcImplTest {
  final List<Product> EXPECTED_FIND_ALL_PRODUCTS = ...;
  final Product EXPECTED_FIND_BY_ID_PRODUCT = ...;
  final Product EXPECTED_UPDATED_PRODUCT = ...;
}
```
**Notes**:
1.	Каждый тест должен быть изолирован от поведения других тестов. Таким образом, перед запуском каждого теста база данных должна находиться в исходном состоянии.
2. Методы тестирования могут вызывать другие методы, которые не входят в текущий тест. Например, при тестировании метода update() можно вызвать метод findById() для проверки достоверности обновления сущности в базе данных.
**Project structure**:

- Tests
    - src
        - main
            - java
                - edu.school21
                    - numbers
                        - NumberWorker
                    - models
                        - Product
                    - repositories
                        - ProductsRepository
                        - ProductsRepositoryJdbcImpl
            - resources
        - test
            - java
                - edu.school21
                    - numbers
                        - NumberWorkerTest
                    - repositories
                        - EmbeddedDataSourceTest
                        - ProductsRepositoryJdbcImplTest
            - resources
                -	data.csv
                -	schema.sql
                -	data.sql
    - pom.xml

# Chapter VII
### Exercise 03 — Test for Service

Exercise 03: Test for Service ||
---|---
Turn-in directory |	ex03
Files to turn-in |	Tests
Важным правилом тестирования модулей является тестирование отдельного компонента системы без вызова функциональности его зависимостей. Такой подход позволяет разработчикам самостоятельно создавать и тестировать компоненты, а также откладывать реализацию определенных частей приложения.

Теперь вам нужно реализовать уровень бизнес-логики, который представлен классом UsersServiceImpl. Этот класс содержит логику аутентификации пользователя. Он также имеет зависимость от интерфейса UsersRepository (в этой задаче вам не нужно реализовывать этот интерфейс).

Интерфейс UsersRepository (который вы описали) должен содержать следующие методы:
```java
User findByLogin(String login);
void update(User user);
```
Предполагается, что метод findByLogin возвращает объект пользователя, найденный при входе в систему, или выдает исключение EntityNotFoundException, если пользователь с указанным именем входа не найден. Метод Update выдает аналогичное исключение при обновлении пользователя, которого нет в базе данных.

Сущность «Пользователь» содержит следующие поля:
- Идентификатор,
- Авторизоваться,
- Пароль,
- Статус успешной аутентификации (true — аутентифицирован, false — не аутентифицирован).

В свою очередь, класс UsersServiceImpl вызывает эти методы внутри функции аутентификации:
```java
boolean authenticate(String login, String password)
```

Этот метод:
1. Проверяет, прошел ли пользователь аутентификацию в системе с использованием этого логина. Если аутентификация была выполнена, необходимо создать исключение AlwaysAuthenticatedException.
2. Извлекает пользователя с этими учетными данными из UsersRepository.
3. Если полученный пароль пользователя соответствует указанному паролю, метод устанавливает для пользователя статус успешной аутентификации, обновляет его информацию в базе данных и возвращает true. Если пароли не совпадают, метод возвращает false.

Ваша цель:
1. Создайте интерфейс UsersRepository.
2. Создайте класс UsersServiceImpl и метод аутентификации.
3. Создайте тест модуля для класса UsersServiceImpl.

Поскольку ваша цель — проверить корректность работы метода аутентификации независимо от компонента UsersRepository, вам следует использовать макетный объект и заглушки методов findByLogin и update (см. библиотеку Mockito).

Метод Authenticate будет проверен в трех случаях:
1. Правильный логин/пароль (проверьте вызов метода обновления с помощью оператора проверки библиотеки Mockito);
2. Неправильный логин;
3. Неправильный пароль.
**Project structure**:

- Tests
    - src
        - main
            - java
                - edu.school21
                    - exceptions
                        - AlreadyAuthenticatedException
                    - numbers
                        - NumberWorker
                    - models
                        - Product
                        - User
                    - services
                        - UsersServiceImpl
                    - repositories
                        - ProductsRepository
                        - ProductsRepositoryJdbcImpl
                        - UsersRepository
            - resources
        - test
            - java
                - edu.school21
                    - services
                        - UsersServiceImplTest
                    - numbers
                        - NumberWorkerTest
                    - repositories
                        - EmbeddedDataSourceTest
                        - ProductsRepositoryJdbcImplTest
            - resources
                -	data.csv
                -	schema.sql
                -	data.sql
    - pom.xml
