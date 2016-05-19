# EmbeddedRConsole

EmbeddedRconsole (ERC) is a minimal Java library to embed R engine in any Java program, CLI application or web application. ERC uses [JRI](https://github.com/s-u/rJava) project to embed an R interpreter and provides classes to create embed R conosole in any enviroment:

- Java Console
- Java Swings UI application
- Web applications

ERC uses Jetty to create a web socket server that wep application can connect to provide an R console in any web application. It provides a javascript RConsole class that can be used in any client side web application to create a web console.


