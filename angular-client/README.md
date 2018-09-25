# Myapp

This project was generated with [Angular CLI](https://github.com/angular/angular-cli) version 6.1.3.

## Development server

Run `ng serve` for a dev server. Navigate to `http://localhost:4200/`. The app will automatically reload if you change any of the source files.

## Code scaffolding

Run `ng generate component component-name` to generate a new component. You can also use `ng generate directive|pipe|service|class|guard|interface|enum|module`.

## Build

Run `ng build -base` to build the project. The build artifacts will be stored in the `dist/` directory. Use the `--prod` flag for a production build.

1. Run `ng build --base-href=/<context-root>/ --prod` or `ng build --base-href=/<context-root>/ -c <env>`. `<context-root>` should be the same value given in the `finalName` field specified in `pom.xml`. Its value is `portal` now. `<env>` can be `dev`, `sit`, `prod` and other values in the future.

2. Run `mvn clean package`. It will give you a `<app-name>.war` package under `target` folder.

3. Deploy this war into Tomcat or other Web/Application servers.


## Running unit tests

Run `ng test` to execute the unit tests via [Karma](https://karma-runner.github.io).

## Running end-to-end tests

Run `ng e2e` to execute the end-to-end tests via [Protractor](http://www.protractortest.org/).

## Further help

To get more help on the Angular CLI use `ng help` or go check out the [Angular CLI README](https://github.com/angular/angular-cli/blob/master/README.md).
```
  @HostListener('window:beforeunload', ['$event'])
  beforeunloadHandler(event) {
      return event.returnValue  = 'Changes you made may not be saved.';
  }
```  
研究区分onbeforeunload事件是刷新还是关闭
https://blog.csdn.net/allgis/article/details/46659395

o：organization（组织-公司）
ou：organization unit（组织单元-部门）
c：countryName（国家）
dc：domainComponent（域名）
sn：surname（姓氏）
cn：common name（常用名称）
sAMAccountName:英文名(RTX账号,唯一)
userPrincipalName:登陆用户名 和 英文名一致