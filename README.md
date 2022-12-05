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
	&emsp;&emsp;&emsp;[&nbsp; java.util.Date , java.time.LocalDate , java.time.LocalDateTime , java.time.Instant , java.util.Calender , org.joda.time.LocalDate , org.joda.time.LocalDateTime , org.joda.time.Instant &nbsp;]</h4>

