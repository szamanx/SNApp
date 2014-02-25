SNApp
=====
SNApp (SocialNetworkingApp)
@author Daniel Kuras

Application requires Java 5+, maven
Application is a working prototype although not every scenario is fully tested (unit & integration). User interaction is rendered on terminal console and application logs are saved into log file. Also there is no proper error handling, data validation, javadoc/comments.

To run:
>mvn clean compile exec:java

to run tests (unit & integration):
>mvn clean verify

To achieve NFR like flexibility, scalability, serviceability,  testability and reusability application architecture has been designed using layers and MVC architecture patterns and command, method factory, strategy and builder design patterns.

Application has 4 pre-installed uses: Alice, Bob, Charlie & Daniel with no followers and messages sent.

