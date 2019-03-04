# Myapp

This project was generated with [Angular CLI](https://github.com/angular/angular-cli) version 6.1.3.

# Build & Package
## Build into WAR
1. Run `ng build --base-href=/<context-root>/ --prod` or `ng build --base-href=/<context-root>/ -c <env>`. `<context-root>` should be the same value given in the `finalName` field specified in `pom.xml`. Its value is `portal` now. `<env>` can be `dev`, `sit`, `prod` and other values in the future.
2. Run `mvn clean package`. It will give you a `<app-name>.war` package under `target` folder.
3. Deploy this war into Tomcat or other Web/Application servers.

# Terminology
- Environment
    + local: your local machine
    + dev: NCS dev server, where MSSQL & WebLogic server is installed
    + sit: GAIC SIT environment

# Coding Guideline
1. All inputs, buttons should have `id` attribute.

## Code scaffolding

Run `ng generate component component-name` to generate a new component. You can also use `ng generate directive|pipe|service|class|guard|interface|enum|module`.

## Build

1. Run `ng build --base-href=/<context-root>/ --prod` or `ng build --base-href=/<context-root>/ -c <env>`. `<context-root>` should be the same value given in the `finalName` field specified in `pom.xml`. Its value is `portal` now. `<env>` can be `dev`, `sit`, `prod` and other values in the future.

2. To spring-boot-client Run `mvn clean package -Pdev<environment>`. It will give you a `<app-name>.war` package under `target` folder.

3. Deploy this war into Tomcat or other Web/Application servers.


## Running unit tests

Run `ng test` to execute the unit tests via [Karma](https://karma-runner.github.io).

## Running end-to-end tests

Run `ng e2e` to execute the end-to-end tests via [Protractor](http://www.protractortest.org/).

## Further help

To get more help on the Angular CLI use `ng help` or go check out the [Angular CLI README]
