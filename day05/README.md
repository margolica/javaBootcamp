# Day 05 – Java bootcamp
### SQL/JDBC

*Takeaways: Today you will use the key mechanisms to work with PostgreSQL DBMS via JDBC*

💡 [Tap here](https://new.oprosso.net/p/4cb31ec3f47a4596bc758ea1861fb624) **to leave your feedback on the project**. It's anonymous and will help our team make your educational experience better. We recommend completing the survey immediately after the project.

# Contents
1. [Chapter I](#chapter-i) \
  1.1. [Preamble](#preamble)
2. [Chapter II](#chapter-ii) \
  2.1. [General Rules](#general-rules)
3. [Chapter III](#chapter-iii) \
  3.1. [Rules of the Day](#rules-of-the-day)
4. [Chapter IV](#chapter-iv) \
  4.1. [Exercise 00 – Tables & Entities](#exercise-00-tables-entities)
5. [Chapter V](#chapter-v) \
  5.1. [Exercise 01 – Read/Find](#exercise-01-readfind)
6. [Chapter VI](#chapter-vi) \
  6.1. [Exercise 02 – Create/Save](#exercise-02-createsave)
7. [Chapter VII](#chapter-vii) \
  7.1. [Exercise 03 – Update](#exercise-03-update)
8. [Chapter VIII](#chapter-viii) \
  8.1. [Exercise 04 – Find All](#exercise-04-find-all)

# Chapter I
### Preamble
Как вы знаете, реляционные базы данных состоят из набора связанных таблиц. Каждая таблица содержит набор строк и столбцов. Если в таблице огромное количество строк, поиск данных с определенным значением в столбце, очевидно, может занять много времени.

Для решения этой проблемы современные СУБД используют индексный механизм. Структура данных BTree является реализацией концепции индекса.
![BTree](misc/images/btree.png)

Этот индекс может использоваться для столбца таблицы. Поскольку дерево всегда сбалансировано, поиск любого значения занимает одинаковое количество времени.

Правила, которые существенно ускоряют поиск, следующие:
1. Ключи в каждом узле упорядочены.
2. Корневой узел содержит ключи от **1** до **t-1**.
3. Любой другой узел содержит ключи от **t-1** до **2t-1**.
4. Если узел содержит ключи **k1, k2, ... kn**, у него есть n+1 производный класс.
5. Первый производный класс и все его производные классы содержат ключи, которые меньше или равны **k1**.
6. Последний производный класс и все его производные классы содержат ключи, которые больше или равны **kn**.
7. Для **2 <= i <= n**, **i**-й производный класс и все его производные классы содержат ключи в диапазоне **(ki-1, ki)**.

Поэтому для поиска значения вам нужно просто определить, к какому производному классу перейти. Это позволяет избежать просмотра всей таблицы.

Такой подход, по-видимому, имеет много специфики. Например, если в таблицу постоянно попадают новые значения, СУБД всегда будет перестраивать индекс, что замедлит работу системы.

# Chapter II
### General Rules
- Используйте эту страницу как единственную ссылку. Не прислушивайтесь к слухам и домыслам о том, как подготовить ваше решение.
- Сейчас для вас доступна только одна версия Java, 1.8. Убедитесь, что на вашем компьютере установлены компилятор и интерпретатор этой версии.
- Вы можете использовать IDE для написания и отладки исходного кода.
- Код чаще читается, чем пишется. Внимательно прочитайте [документ](https://www.oracle.com/technetwork/java/codeconventions-150003.pdf), где приведены правила форматирования кода. При выполнении каждой задачи убедитесь, что вы придерживаетесь общепринятых [стандартов Oracle](https://www.oracle.com/java/technologies/javase/codeconventions-namingconventions.html).

- Комментарии в исходном коде вашего решения недопустимы. Они затрудняют чтение кода.
- Обратите внимание на права доступа к вашим файлам и каталогам.
- Чтобы ваше решение было оценено, оно должно находиться в вашем GIT-репозитории.
- Ваши решения будут оцениваться вашими товарищами по буткемпу.
- Вам не следует оставлять в вашем каталоге "src" какие-либо файлы, отличные от тех, которые явно указаны в инструкциях по упражнениям. Рекомендуется изменить ваш файл .gitignore, чтобы избежать несчастных случаев.
- Когда вам нужно получить точный результат в ваших программах, запрещается показывать предварительно рассчитанный результат вместо правильного выполнения упражнения.
- У вас есть вопрос? Спросите своего соседа справа. В противном случае попробуйте с соседом слева.
- Ваше справочное руководство: mates / Internet / Google. И еще кое-что. В Stackoverflow есть ответ на любой вопрос, который может у вас возникнуть. Узнайте, как правильно задавать вопросы.
- Внимательно прочитайте примеры. Они могут потребовать чего-то, что не указано в теме.
- Используйте System.out для вывода.
- И да пребудет с вами Сила!
- Никогда не откладывайте на завтра то, что вы можете сделать сегодня ;)
# Chapter III
### Rules of the Day
- Используйте СУБД PostgreSQL во всех задачах.
- Подключите последнюю версию драйвера JDBC.
- Для взаимодействия с базой данных вы можете использовать классы и интерфейсы пакета java.sql (реализации соответствующих интерфейсов будут автоматически включены из архива, содержащего драйвер).
# Chapter IV
### Exercise 00 – Tables & Entities

Exercise 00: Tables & Entities ||
---|---
Turn-in directory |	ex00
Files to turn-in	| Chat-folder

В течение этой недели мы будем внедрять функциональность чата. В этом чате пользователь может создать или выбрать существующий чат-рум. В каждом чате могут обмениваться сообщениями несколько пользователей.

Ключевыми моделями предметной области, для которых должны быть реализованы как таблицы SQL, так и классы Java, являются:
- User
    -	User ID
    - Login
    -	Password
    -	List of created rooms
    -	List of chatroomIds where a user socializes
- Chatroom
    -	Chatroom id
    - Chatroom name
    - Chatroom owner
    - List of messages in a chatroom
- Message
    - Message id
    - Message author
    - Message room
    - Message text
    - Message date/time

Создайте файл schema.sql, в котором вы опишете операции создания таблиц для создания таблиц для проекта. Вам также следует создать файл data.sql с текстовыми вставками данных (не менее пяти в каждой таблице).

Важно выполнить следующее требование!

Давайте предположим, что сущность курса имеет отношение "один ко многим" с сущностью урока. Тогда их объектно-ориентированное отношение должно выглядеть следующим образом:
```java
class Course {
   private Long id;
   private List<Lesson> lessons;// there are numerous lessons in the course
   ...
}
class Lesson {
   private Long id;
   private Course course; // the lesson contains a course it is linked to
   ...
}
```
Дополнительные требования:

- Для реализации реляционных связей используйте типы связей "один ко многим" и "многие ко многим".
- Идентификаторы должны быть числовыми.
- Идентификаторы должны генерироваться СУБД.
- equals(), hashCode() и toString() должны быть корректно переопределены внутри классов Java.

Exercise project structure:
- Chat
    -	src
        -	main
            - java
              -	edu.school21.chat
                 -	models - domain knowledge models
            - resources
                -	schema.sql
                -	data.sql
    -	pom.xml

# Chapter V
### Exercise 01 – Read/Find

Exercise 01: Read/Find ||
---|---
Turn-in directory |	ex01
Files to turn-in |	Chat-folder
Объект доступа к данным (DAO, Repository) - популярный шаблон проектирования, позволяющий отделить ключевую бизнес-логику от логики обработки данных в приложении.

Давайте предположим, что у нас есть интерфейс под названием Courses Repository, который предоставляет доступ к урокам курса. Этот интерфейс может выглядеть следующим образом:
```java
public interface CoursesRepository {
  Optional<Course> findById(Long id);
  void delete(Course course);
  void save(Course course);
  void update(Course course);

  List<Course> findAll();
}
```
Вам необходимо реализовать MessagesRepository с помощью единственного метода `Необязательный<Message> findById(длинный идентификатор)` и его реализации MessagesRepositoryJdbcImpl.

Этот метод должен возвращать объект сообщения, в котором будут указаны автор и чат-комната.  В свою очередь, нет необходимости вводить дополнительные данные (список чатов, создатель чата и т.д.) для автора и чата-рума.

Реализованный код должен быть протестирован в классе Program.java. Пример работы программы следующий (выходные данные могут отличаться):
```
$ java Program
Enter a message ID
-> 5
Message : {
  id=5,
  author={id=7,login=“user”,password=“user”,createdRooms=null,rooms=null},
  room={id=8,name=“room”,creator=null,messages=null},
  text=“message”,
  dateTime=01/01/01 15:69
}
```

Exercise project structure:
- Chat
  -	src
      -	main
        - java
          -	edu.school21.chat
             - models - domain knowledge models
              -	repositories - repositories
              -	app
                  - Program.java
        - resources
          -	schema.sql
          -	data.sql
  -	pom.xml


Репозиторий сообщений Jdbc Impl должен принимать интерфейс источника данных пакета java.sql в качестве параметра конструктора.
Для реализации источника данных используйте библиотеку HikariCP — пул подключений к базе данных, которые значительно ускоряют использование хранилища.
# Chapter VI
### Exercise 02 – Create/Save

Exercise 02: Create/Save ||
---|---
Turn-in directory	| ex02
Files to turn-in |	Chat-folder

Теперь вам нужно реализовать метод сохранения(Message message) для MessagesRepository.

Таким образом, нам нужно определить следующие субличности для объекта, который мы сохраняем — автора сообщения и чат-комнату. Также важно присвоить чату и автору идентификаторы, которые существуют в базе данных.

Пример использования метода сохранения:
```java
public static void main(String args[]) {
	...
  User creator = new User(7L, “user”, “user”, new ArrayList(), new ArrayList());
  User author = creator;
  Room room = new Room(8L, “room”, creator, new ArrayList());
  Message message = new Message(null, author, room, “Hello!”, LocalDateTime.now());
  MessagesRepository messagesRepository = new MessagesRepositoryJdbcImpl(...);
  messagesRepository.save(message);
  System.out.println(message.getId()); // ex. id == 11
}
```

Таким образом, метод save должен присвоить значение ID для входящей модели после сохранения данных в базе данных.
Если у author и room нет присвоенных идентификаторов в базе данных или эти идентификаторы равны нулю, вызовите Runtimeexception NotSavedSubEntityException (реализуйте это исключение самостоятельно).

Протестируйте реализованный код в Program.java class.

# Chapter VII
### Exercise 03 – Update

Exercise 03: Update ||
---|---
Turn-in directory |	ex03
Files to turn-in |	Chat-folder
Теперь нам нужно реализовать метод update в MessageRepository. Этот метод должен полностью обновить существующую сущность в базе данных. Если новое значение поля в обновляемой сущности равно null, это значение должно быть сохранено в базе данных.

Пример использования метода update:

```java
public static void main(String args[]) {
  MessagesRepository messagesRepository = new MessagesRepositoryJdbcImpl(...);
  Optional<Message> messageOptional = messagesRepository.findById(11);
  if (messageOptinal.isPresent()) {
    Message message = messageOptional.get();
    message.setText(“Bye”);
    message.setDateTime(null);
    messagesRepository.update(message);
  }
  ...
}
```
В этом примере значение столбца, в котором хранится текст сообщения, будет изменено, тогда как время отправки сообщения будет равно нулю.
# Chapter VIII
### Exercise 04 – Find All

Exercise 04: Find All ||
---|---
Turn-in directory |	ex04
Files to turn-in |	Chat-folder
Теперь вам нужно реализовать интерфейс UsersRepository и класс UsersRepositoryJdbcImpl, используя метод `SINGLE List<User> findAll(int page, int size)`.

Этот метод должен возвращать размер—пользователей, показанных на странице, с указанием номера страницы. Такой "кусочный" поиск данных называется разбиением на страницы. Таким образом, СУБД делит весь набор данных на страницы, каждая из которых содержит записи о размере. Например, если набор содержит 20 записей с page = 3 и size = 4 , вы получаете пользователей от 12 до 15 (нумерация пользователей и страниц начинается с 0).

Наиболее сложная ситуация при преобразовании реляционных ссылок в объектно-ориентированные возникает, когда вы извлекаете набор сущностей вместе с их субличностями. В этой задаче каждый пользователь в результирующем списке должен иметь включенные зависимости — список чатов, созданных этим пользователем, а также список чатов, в которых участвует пользователь.

Каждая субличность пользователя НЕ ДОЛЖНА содержать своих зависимостей, т.е. список сообщений внутри каждой комнаты должен быть пустым.

Работа реализованного метода должна быть продемонстрирована в Program.java.
**Notes**:
- Метод findAll(int page, int size) должен быть реализован с помощью ОДНОГО запроса к базе данных. Запрещено использовать дополнительные SQL-запросы для получения информации для каждого пользователя.
- Мы рекомендуем использовать CTE PostgreSQL.
- - Пользовательский репозиторий Jdbc Impl должен принимать интерфейс источника данных пакета java.sql в качестве параметра конструктора.