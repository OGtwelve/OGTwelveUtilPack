# The use of tools


<h1>How to use your own chatgpt api:</h1>
<h4>1. maven dependency (currently only central Maven repository have this dependency, haven't add to Aliyun repository)</h4>

```xml
<dependency>
    <groupId>cn.com.ogtwelve</groupId>
    <artifactId>OGTwelveUtilPack</artifactId>
    <version></version> <!--See whats the newest version is;-->
</dependency>
```

<h4>2. add annotation to the starter class:</h4>

```java

// how to use chatgpt using my dependency
// date time formatter and chatgpt can be used at the same time, if u want to use chatgpt, just add @EnableChatGPT
// if u want to use date time formatter, just add @EnableGlobalDateFormat
// if u want to use both, just add both of them
@SpringBootApplication
@EnableChatGPT
public class Application {
  ...
}

```
<h4>3. add your own api key to the application class:</h4>

properties file :
```properties
# if use application.properties
# proxy depends on weather u have a vpn or not , if not just delete the proxyHost and proxyPort
# but without vpn, u might not be able to use the chatgpt api or just have to wait for a long time to get the response
# model and chatModel, about these parameters, u can go to openai's website to check out the model u want to use
# last parameter is the session expiration time, if u want the session to be expired in how many *minutes*, just change the number
openai.token=
openai.proxyHost=127.0.0.1
openai.proxyPort=4780
openai.model=
openai.chatModel=
openai.sessionExpirationTime=30
```
yaml file :
```yaml
openai:
  token:
  proxyHost:
  proxyPort:
  model:
  chatModel:
  sessionExpirationTime:
```

<h4>And there you have it, after above settings u can use ur api now;</h4>

<h1>How to use my global time converter in spring boot application:</h1>
<h4>1. maven dependency (currently only central Maven repository have this dependency, haven't add to Aliyun repository)</h4>

```xml
<dependency>
    <groupId>cn.com.ogtwelve</groupId>
    <artifactId>OGTwelveUtilPack</artifactId>
    <version></version> <!--See whats the newest version is;-->
</dependency>
```
<h4>2. just add one scan or a annotation at the starter:</h4>

```java
// version 1.16 and other thats below this number
@SpringBootApplication(scanBasePackages = "cn.com.ogtwelve.utils")
```

```java
// version big than 1.18 will be using annotation to use time formatter
// better use above 1.2.0, because there won't be any waste output in the console
@SpringBootApplication
@EnableGlobalDateFormat
public class Application {
  ...
}
```

# Version infos

<h4> v1.0.0-v1.0.2: Import basic utils;</h4>

<h4> v1.0.3: Make the project's global time can be format into any style we desire;</h4>

<h4> v1.0.4-v1.0.5: The date class under org.joda.time can be transformed as well;</h4>

<h4> v1.0.6: Current can transform these date type: <br/>
	&emsp;&emsp;&emsp;[&nbsp; java.util.Date , java.time.LocalDate , java.time.LocalDateTime , java.time.Instant , java.util.Calender , org.joda.time.LocalDate , &emsp;&emsp;&emsp;org.joda.time.LocalDateTime , org.joda.time.Instant &nbsp;]</h4>
	
<h4> v1.0.7: Add serializer & deserializer to [org.joda.time.DateTime] class</h4>

<h4> v1.0.8: ProcShell.java: Add method for execute multi-line of CMD command; <br/>
	&emsp;&emsp;&emsp;Also add dependencies for slf4j and log4j</h4>
	
<h4> v1.0.9(Deprecated): Add a autoconfiguration for the time converter;</h4>

<h4> v1.1.0: Patch up for autoconfig bugs in version 1.0.9;</h4>

<h4> v1.1.1: Nothing added;</h4>

<h4> v1.1.2: Deprecate the GlobalTimeConverter and removed getConversionService method in GlobalTimeConfig</h4>
	
<h4> v1.1.3: Remove autoconfiguration settings, set the version back to 1.0.8</h4>

<h4> v1.1.4: Changed 1 or 2 lines in pom file;</h4>

<h4> v1.1.5: Nothing added;</h4>

<h4> v1.1.6: Changed few dependencies in pom file;</h4>

<h4> v1.1.7(Deprecated): Changed few dependencies in pom file;</h4>

<h4> v1.1.8: Change into using annotation to use time formatter : @EnableGlobalDateFormat</h4>

<h4> v1.1.9: Changed few dependencies in pom file;</h4>

<h4> v1.2.0: Perfect version, when not using @EnableGlobalDateFormat, it won't conflict with the project itself,<br/> and it won't have any waste output in the console; </h4>

<h4> v1.2.1 - v1.2.4: Put openAi's chatgpt into use;(Had bug)</h4>

<h4> v1.2.5: Working chatgpt dependency;</h4>

<h4> v1.2.6: Change default chat model;</h4>

	
# At last
<h4>❤️Please fire away all the ideas u have for this project;</h4>
<h4>🙇‍♂️Thank u guys for the stars and forks, welcome to take part in this project;</h4>
<h4>💪Wish each one of u guys can be better than ur former self;</h4>
	
