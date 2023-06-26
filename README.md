# Software Architecture Design T7 Requirement

[![Actions Status](https://github.com/Testing-Game-SAD-2023/T7-G26/actions/workflows/ci.yml/badge.svg)](https://github.com/Testing-Game-SAD-2023/T7-G26/actions)
[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=Testing-Game-SAD-2023_T7-G26&metric=alert_status)](https://sonarcloud.io/summary/new_code?id=Testing-Game-SAD-2023_T7-G26)
[![codecov](https://codecov.io/gh/Testing-Game-SAD-2023/T7-G26/branch/master/graph/badge.svg?token=QO87WH2W7H)](https://codecov.io/gh/Testing-Game-SAD-2023/T7-G26)
[![FOSSA Status](https://app.fossa.com/api/projects/git%2Bgithub.com%2FPabloGarciaFernandez%2FSoftware-Architecture-Design-Project.svg?type=shield)](https://app.fossa.com/projects/git%2Bgithub.com%2FPabloGarciaFernandez%2FSoftware-Architecture-Design-Project?ref=badge_shield)


## Description

The application must offer the functionality of compiling and executing test cases written by the player.

This functionality will receive two text files as input (class to test and test class) and will launch the compiler and the test executor, returning information relating to the outcome of the compilation (if incorrect) or the outcome of the tests as output performed.

The outcome of the execution must be processed in such a way as to extract from them the relevant information for the purposes of the game (for example the presence of errors in compiling the test, or the outcome of the execution of the test, such as for example the coverage of the code, etc.).


## Technologies used

<p float="left">
<a href="https://www.oracle.com/java/" style="margin-right: 0.5em;"><img src="https://img.icons8.com/external-tal-revivo-shadow-tal-revivo/512/external-java-is-a-general-purpose-programming-language-that-is-class-based-logo-shadow-tal-revivo.png" height="50"></a>
<a href="https://maven.apache.org/" style="margin-right: 0.5em;"><img src="https://upload.wikimedia.org/wikipedia/commons/thumb/5/52/Apache_Maven_logo.svg/510px-Apache_Maven_logo.svg.png" height="50"></a>
<a href="https://www.jacoco.org/" style="margin-right: 0.5em;"><img src="http://intellitech.pro/wp-content/uploads/2017/05/Jacoco-icon.png" height="50"></a>
<a href="https://spring.io/" style="margin-right: 0.5em;"><img src="https://upload.wikimedia.org/wikipedia/commons/thumb/4/44/Spring_Framework_Logo_2018.svg/368px-Spring_Framework_Logo_2018.svg.png" height="50"></a>
<a href="https://junit.org/junit5/" style="margin-right: 0.5em;"><img src="https://i0.wp.com/blog.knoldus.com/wp-content/uploads/2020/06/junit5-banner.png?w=982&ssl=1" height="50"></a>
<a href="https://www.w3.org/html/" style="margin-right: 0.5em;"><img src="https://upload.wikimedia.org/wikipedia/commons/thumb/6/61/HTML5_logo_and_wordmark.svg/390px-HTML5_logo_and_wordmark.svg.png" height="50"></a>
<a href="https://github.com/actions" style="margin-right: 0.5em;"><img src="https://avatars.githubusercontent.com/u/44036562?s=200&v=4" height="50"></a>
</p>


## How to run

To start the project on local you have to run:

```bash
T7Application.java
```

Important! You need this configuration on the VM options of T7Application.java:

```bash
-javaagent:"lib/jacocoagent.jar=destfile=coverage/coverage.exec"
```

Then open your browser in:

```bash
http://localhost:8090/
```

You can change the server port in:

```bash
application.properties
```

To execute the tests you either can execute this file or use the following command:

```bash
TestSuite.java
```
```bash
mvn -B test
```
