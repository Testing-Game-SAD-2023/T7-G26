# Software Architecture Design T7 Requirement

[![Actions Status](https://github.com/Testing-Game-SAD-2023/T7-G26/actions/workflows/ci.yml/badge.svg)](https://github.com/Testing-Game-SAD-2023/T7-G26/actions)
[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=Testing-Game-SAD-2023_T7-G26&metric=alert_status)](https://sonarcloud.io/summary/new_code?id=Testing-Game-SAD-2023_T7-G26)
[![codecov](https://codecov.io/gh/Testing-Game-SAD-2023/T7-G26/branch/master/graph/badge.svg?token=QO87WH2W7H)](https://codecov.io/gh/Testing-Game-SAD-2023/T7-G26)
[![FOSSA Status](https://app.fossa.com/api/projects/git%2Bgithub.com%2FPabloGarciaFernandez%2FSoftware-Architecture-Design-Project.svg?type=shield)](https://app.fossa.com/projects/git%2Bgithub.com%2FPabloGarciaFernandez%2FSoftware-Architecture-Design-Project?ref=badge_shield)
![Static Badge](https://img.shields.io/badge/release-v1.0.0-blue)

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
<a href="https://swagger.io/" style="margin-right: 0.5em;"><img src="https://pnx-assets-prod.s3.amazonaws.com/2020-07/swagger_logo_1.png" height="50"></a>
<a href="https://www.docker.com/" style="margin-right: 0.5em;"><img src="https://www.docker.com/wp-content/uploads/2022/03/horizontal-logo-monochromatic-white.png" height="50"></a>


## How to run

To start the project on local you have to run `T7Application.java` and then open your browser in:

```bash
http://localhost:8090/
```

You can change the server port in the `application.properties` file.

To execute the tests you either can execute `requirement_t7.TestSuite.java` or use the following command:
```bash
mvn -B test
```

## How to run in Docker

To run the project in Docker, follow these steps:

1. Make sure you have Docker installed on your machine. You can download and install it from the official Docker website.

2. Build the Docker image by running the following command in the terminal:

    ```bash
    docker build -t image-name .
    ```

   Replace `image-name` with the name you want to give to your Docker image.

3. Once the image is built, run the Docker container with the following command:

    ```bash
    docker run -p 8090:8090 image-name
    ```

   This command will start the container and map port 8090 of the container to port 8090 of your local machine.

4. Open your preferred web browser and navigate to:

    ```bash
    http://localhost:8090/swagger-ui/index.html
    ```

   You should now be able to access your application running in Docker.

   Note: If you need to change the server port inside the container, you can modify it in the `application.properties` file and in `Dockerfile` before building the Docker image.

5. To execute the tests, you have two options:

    - Option 1: Execute the `requirement_t7.TestSuite.java` file inside the running container.

    - Option 2: Use the following command to execute the tests using Maven:

      ```bash
      docker exec container-id mvn -B test
      ```

   Replace `container-id` with the ID of the running Docker container.

