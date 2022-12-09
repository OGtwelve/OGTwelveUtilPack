# The use of tools

<h2>How to use my global time converter in spring boot application:</h2>
<h4>1. maven dependency (currently only central Maven repository have this dependency, haven't add to Aliyun repository)</h4>

```xml
<dependency>
    <groupId>cn.com.ogtwelve</groupId>
    <artifactId>OGTwelveUtilPack</artifactId>
    <version></version> <!--See whats the newest version is;-->
</dependency>
```
<h4>2. just add one scan at the starter:</h4>

```java
@SpringBootApplication(scanBasePackages = "cn.com.ogtwelve.utils")
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
	
<h4> v1.0.9: Add a autoconfiguration for the time converter;</h4>

<h4> v1.1.0: patch up for autoconfig bugs in version 1.0.9;</h4>
	
# At last
<h4>❤️Please fire away all the ideas u have for this project;</h4>
<h4>🙇‍♂️Thank u guys for the stars and forks, welcome to take part in this project;</h4>
<h4>💪Wish each and every one of u guys can be better than ur former self;</h4>
	
