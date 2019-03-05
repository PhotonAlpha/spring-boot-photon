## Spring Boot Enterprise Action
my spring boot project in action

includes
* ### Angular 7 In Action
    * Bootstrap4
    * Angular Material 2
    * Router Hash Fix 
    * @Input & @Output control
    * Formbuilder & Formarray Customer Validation Control
    * HttpInterceptor exception catch & refresh token
    * Subscribe Message System
    * CanDeactivate & Deactivate Router Control
    * Idle session timeout 
    
* ### Spring Boot
    * Maven Multiple Module
    * Maven Profiles Filter Properties
    * Login Authorization & Refresh Token
    * Swagger2 regular REST API
    * Spring MVC control Angular 6 with REST Url
    * **Caffeine** Local Cache
    * Junit Test
    * Mybatis & Interceptor Pagination

* ### TODO
    * quartz 
    * Memcached custer
    * redis
    * netty


### RSA 
```
keytool -genkeypair -alias xxxkey -keyalg RSA -dname "CN=Web Server,OU=Unit,O=Organization,L=Singapore,S=SG,C=SG" -keypass Password1@3 -keystore D:\sourcecode\xxxCert.jks -storetype pkcs12 -storepass Password1@3

keytool -certreq -alias xxxkey -keyalg RSA -file D:\sourcecode\xxxCert.csr -keystore D:\sourcecode\xxxCert.jks

keytool -export -alias xxxkey -keystore D:\sourcecode\xxxCert.jks -file D:\sourcecode\xxxPublic.csr
```
> password: `storepass`
>
> alias: `xxxkey`
>
> secret: `keypass`

- codehelper.generator 
- Free MyBatis plugin 