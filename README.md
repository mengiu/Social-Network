# Social Networking MG
A console-based social networking application (similar to Twitter).

This project is designed to help you start or improve your [Behaviour Driven Development](https://en.wikipedia.org/wiki/Behavior-driven_development) skills.

## What is BDD

[Behaviour Driven Development](https://en.wikipedia.org/wiki/Behavior-driven_development) is a methodology that specifies acceptance criteria using a syntax that can be managed by business and by technology. The most well known implementation for this syntax is [Gherkin](https://github.com/cucumber/cucumber/wiki/Gherkin).

The Gherkin syntax has 3 main components that are represented by the following *keywords*:

* **Feature** - maps to a feature of the software. e.g.: Login

* **Scenario** - maps to a particular scenario of the usage of the feature.

* **Step** - a step of the scenario. A step starts with one of the following keywords:

    * **Given** - used for definition of context.
    * **And** or **When** - used for events.
    * **Then** or **But** - used for assertions.
## Testing Tools/Frameworks

We will be using a few tools/frameworks to facilitate our job.

* [JUnit](https://junit.org/junit4/) - Unit Testing Framework
* [mockito](http://site.mockito.org/) - Mocking Framework for Unit Tests
* [junit-dataprovider](https://github.com/TNG/junit-dataprovider) - Data Provider Runner for JUnit
* [Cucumber-JVM](https://cucumber.io/docs/reference/jvm) - A BDD testing framework implementation for Java

Java 8

To run the application in command line :
- Go to the repository
- mvn clean install
- cd target
- java -jar socialnetwork-0.0.1-SNAPSHOT-jar-with-dependencies.jar 

To stop the application, type "exit"

Exercise

Implement a console-based social networking application (similar to Twitter) satisfying the scenarios below.

### Scenarios

**Posting**: Alice can publish messages to a personal timeline

> \> Alice -> I love the weather today  
> \> Bob -> Damn! We lost!  
> \> Bob -> Good game though.  

**Reading**: Bob can view Alice’s timeline

> \> Alice  
> \> I love the weather today (5 minutes ago)  
> \> Bob  
> \> Good game though. (1 minute ago)  
> \> Damn! We lost! (2 minutes ago)  

**Following**: Charlie can subscribe to Alice’s and Bob’s timelines, and view an aggregated list of all subscriptions

> \> Charlie -> I'm in New York today! Anyone wants to have a coffee?  
> \> Charlie follows Alice  
> \> Charlie wall  
> \> Charlie - I'm in New York today! Anyone wants to have a coffee? (2 seconds ago)  
> \> Alice - I love the weather today (5 minutes ago)  

> \> Charlie follows Bob  
> \> Charlie wall  
> \> Charlie - I'm in New York today! Anyone wants to have a coffee? (15 seconds ago)  
> \> Bob - Good game though. (1 minutes ago)  
> \> Bob - Damn! We lost! (2 minute ago)  
> \> Alice - I love the weather today (5 minutes ago)  

### General requirements

- Application must use the console for input and output;
- User submits commands to the application:
    - posting: \<user name> -> \<message>
    - reading: \<user name>
    - following: \<user name> follows \<another user>
    - wall: \<user name> wall
- Don't worry about handling any exceptions or invalid commands. Assume that the user will always type the correct commands. Just focus on the sunny day scenarios.
- "posting:", "reading:", "following:" and "wall:" are not part of the command. All commands start with the user name.
- Use whatever language and frameworks you want. Use something that you know well.
- Provide a README with instructions on how to compile and run the application.
- You must release your work with an OSI-approved open source license of your choice.
