# Spring-Boot-Core
This is the project Core Engine to drive the project.
Integrate Spring Boot Configuration

## usage
1. run `mvn clean install` as a war.
2. import dependency to you project.
```
    <dependency>
            <groupId>com.ethan</groupId>
            <artifactId>spring-boot-core</artifactId>
            <version>1.0.0.RELEASE</version>
        </dependency>
```

### RSA 
```
keytool -genkeypair -alias xxxkey -keyalg RSA -dname "CN=Web Server,OU=Unit,O=Organization,L=Singapore,S=SG,C=SG" -keypass Password1@3 -keystore D:\sourcecode\xxxCert.jks -storetype pkcs12 -storepass Password1@3

keytool -certreq -alias xxxkey -keyalg RSA -file D:\sourcecode\xxxCert.csr -keystore D:\sourcecode\GAIC\xxxCert.jks

keytool -export -alias xxxkey -keystore D:\sourcecode\xxxCert.jks -file D:\sourcecode\xxxPublic.csr
```
> password: `storepass`
>
> alias: `xxxkey`
>
> secret: `keypass`