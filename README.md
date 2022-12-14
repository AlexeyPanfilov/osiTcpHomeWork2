## Задача 2 (необязательная)
В этой задаче мы напишем сервер, который будет имитировать игру в города.

Давайте напомним её правила. Игроки по очереди называют города, главное правило - следующий город должен начинаться на ту же букву, на которую заканчивался последний названный город.

В нашей игре игроками являются клиенты, подключающиеся к серверу; сервер лишь контролирует игру, сам в неё не играя.

Первый игрок (первый подключающийся после старта сервера клиент) на вход от сервера получает строку `"???"` - так клиент понимает, что он первый игрок и может назвать любой город. Назвать = отправить город в виде одной строки на сервер, сервер в ответ отправляет строку `"OK"`.

Все остальные игроки (т.е. подключающиеся не первыми) получают при подключении на вход строку - последний введённый город в игре. Клиент в ответ отправляет город в виде одной строки; в ответ сервер отправляет либо строку `"OK"`, если отправленный город начинался на букву на которую заканчивался последний город, иначе `"NOT OK"`. В последнем случае введённый игроком город не учитывается для последующих игроков.

Каждый игрок подключается один раз и вводит только один город. Мы доверяем, что слово, введённое игроком - название города. Можно считать, что все города вводятся с маленькой буквы.

В этой задаче сервер будет принимать подключения в цикле:
```java
try (ServerSocket serverSocket = new ServerSocket(8989);) { // стартуем сервер один(!) раз
          while (true) { // в цикле(!) принимаем подключения
              try (
                      Socket socket = serverSocket.accept();
                      BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                      PrintWriter out = new PrintWriter(socket.getOutputStream());
                  ) {
                  // обработка одного подключения
              }
          }
      } catch (IOException e) {
          System.out.println("Не могу стартовать сервер");
          e.printStackTrace();
      }
```
