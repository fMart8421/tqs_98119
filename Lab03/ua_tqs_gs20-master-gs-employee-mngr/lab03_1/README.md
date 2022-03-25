# Lab03 Exercise 1

## Identify a couple of examples on the use of AssertJ expressive methods chaining  


## Identify an example in which you mock the behavior of the repository (and avoid involving a database)  

```java
@Mock( lenient = true)
    private EmployeeRepository employeeRepository;
```

This is done in Test B, the one where we want to test the "Beans". In order to test them, we would need to have a database running. In this case, we mock the behavior/responses of the repository, therefore erasing the need to use a database (in memory or real).

## What is the difference between standard @Mock and @MockBean?

@Mock is used in both JUnit unit testing and Springboot and it's used when we are trying to test our business logic 
@MockBean is used exclusively in Springboot testing and is usually used together with @WebMvcTest. This is used when we need to replace a bean with a mocked (simpler) version of it



## What is the role of the file “application-integrationtest.properties”? In which conditions will it be used?


## The sample project demonstrates three test strategies to assess an API (C, D and E) developed with SpringBoot. Which are the main/key differences?



