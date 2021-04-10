Getting Started
---------------

This is a demo project for education/training purposes for DevOps.

[![Pipeline](https://github.com/fvilarinho/demo/actions/workflows/pipeline.yml/badge.svg)](https://github.com/fvilarinho/demo/actions/workflows/pipeline.yml)

It uses [`GitHub Actions`](https://github.com/features/actions) that contains a pipeline with 7 phases described below:

### 1. Compile and Build
All commands of this phase are defined in `build.sh` file. 
It checks if there are no compile/build errors.
The tools used are:
- [`Gradle`](https://www.gradle.org)

### 2. Code Analysis (White-box testing - SAST)
All commands of this phase are defined in `codeAnalysis.sh` file. 
It checks Bugs, Vulnerabilities, Hotspots, Code Smells, Duplications and Coverage of the code.
If these metrics don't comply with the defined Quality Gate, the pipeline won't continue.
The tools used are:
- [`Gradle`](https://www.gradle.org)
- [`Sonar`](https://sonardcloud.io)

Environments variables needed in this phase:
- `GITHUB_TOKEN`: API Key used by Sonar client to communicate with GitHub.
- `SONAR_TOKEN`: API Key used by Sonar client to store the generated analysis.

### 3. Libraries Analysis (White-box testing - SAST)
All commands of this phase are defined in `librariesAnalysis.sh` file. 
It checks for vulnerabilities in internal and external libraries used in the code.
The tools used are:
- [`Snyk`](https://snyk.io)

Environments variables needed in this phase:
- `SNYK_TOKEN`: API Key used by Snyk to store the generated analysis.

### 4. Packaging
All commands of this phase are defined in `package.sh` file.
It encapsulates all binaries in a Docker image.
Once the code and libraries were checked, it's time build the package to be used in the next phases.
The tools/services used are:
- [`Docker`](https://www.docker.com)

Environments variables needed in this phase:
- `CONTRAST_CONFIG_DATA`: Configuration data used by the Contrast Security agent. Contrast Security is a tool for DAST (Dynamic Application Security Testing) and RASP (Runtime Application Self Protection).
- `DOCKER_REGISTRY_URL`: URL of the Docker registry where the Docker image will be stored.
- `DOCKER_REGISTRY_USERNAME`: Username of the Docker registry.

### 5. Package Analysis
All commands of this phase are defined in `packageAnalysis.sh` file.
It checks for vulnerabilities in the generated package.
The tools/services used are:
- [`Snyk`](https://snyk.io)

Environments variables needed in this phase:
- `SNYK_TOKEN`: API Key used by Snyk to store the generated analysis.
- `DOCKER_REGISTRY_URL`: URL of the Docker registry where the Docker image is stored.
- `DOCKER_REGISTRY_USERNAME`: Username of the Docker registry.

### 5. Publishing
All commands of this phase are defined in `publish.sh` file.
It publishes the package in the Docker registry.
The tools/services used are:
- [`Docker`](https://www.docker.com)
- [`GitHub Packages`](https://github.com/features/packages)

Environments variables needed in this phase:
- `DOCKER_REGISTRY_URL`: URL of the Docker registry where the Docker image is stored.
- `DOCKER_REGISTRY_USERNAME`: Username of the Docker registry.
- `DOCKER_REGISTRY_PASSWORD`: Password of the Docker registry.

### 6. Deployment
All commands of this phase are defined by the GitHub action called `danielr1996/kubectl-action@1.0.0`.
It deploys the generated package in a Kubernetes cluster that will be used by the vulnerabilities scanning.
The tools/services used are:
- [`Kubernetes`](https://kubernetes.io) as Container operator/orchestrator.
- [`Linode`](https://www.linode.com) as Cloud infrastructure.

### 7. Scanner (Black-box testing - DAST) 
All commands of this phase are defined by `scanner.sh` file.
It starts a scanner pointing to the application endpoint (URL).
The tools/services used are:
- [`Contrast Security`](https://www.contrastsecurity.com).
- [`Probely`](https://probely.com).

Today, this phase is manual but it can be automated easily with API calls.

Comments
--------
### If any phase got errors or violations, the pipeline will stop.
### All environments variables must also have a secret with the same name. 
### You can define the secret in the repository settings. 
### DON'T EXPOSE OR COMMIT ANY SECRET IN THE PROJECT.

Architecture
------------
The application was developed using:
- [`Java 11`](https://www.oracle.com/br/java/technologies/javase-jdk11-downloads.html)
- [`Spring Boot 2.4.x`](https://spring.io)
- [`H2 Database`](https://www.h2database.com/html/main.html)
- [`Docker`](https://www.docker.com)

For further documentation please check the documentation of each tool/service.

Roadmap
-------
- Add integration with Slack for notifications.
- Add integration with GitHub for ticket control.
- Add integration with Probely for scanner automation.

How to install
--------------

1. You need an IDE such as Eclipse or IntelliJ.
2. You need an account in the following services:
`GitHub, Sonarcloud, Snyk, Contrast Security and Probely`.
3. You need to set the environment variables described above in you systems.
4. The API Keys for each service must be defined in the UI of each service. Please refer the service documentation.
5. Download or Fork this project from GitHub.
6. Import the project in the chosen IDE
7. Commit some changes in the code and follow the execution of the pipeline in GitHub.

Other References
----------------

- [Official Gradle documentation](https://docs.gradle.org)
- [Spring Boot Gradle Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.4.4/gradle-plugin/reference/html/)
- [Spring Web](https://docs.spring.io/spring-boot/docs/2.4.4/reference/htmlsingle/#boot-features-developing-web-applications)
- [Spring Data JPA](https://docs.spring.io/spring-boot/docs/2.4.4/reference/htmlsingle/#boot-features-jpa-and-spring-data)
- [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
- [Accessing Data with JPA](https://spring.io/guides/gs/accessing-data-jpa/)
- [My LinkedIn Profile](https://www.linkedin.com/in/fvilarinho)

All opinions and standard described here are my own.

That's it! Now enjoy and have fun!
