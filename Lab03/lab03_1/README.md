# Lab03 Exercise 1

## Identify a couple of examples on the use of AssertJ expressive methods chaining  

```java
    assertThat(allEmployees).hasSize(3).extracting(Employee::getName).contains(alex.getName(), john.getName(), bob.getName());
```

```java
    assertThat(allEmployees).hasSize(3).extracting(Employee::getName).containsOnly(alex.getName(), ron.getName(), bob.getName());
```

Here we can see a chaining of the `andExpect()` method.  

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

in this specific case, the aplication-integratedtest.properties is used to define the url for the "real" database. Not only that, it is used to define parameters for the data access, such as username and password. The other property it has, `spring.jpa.hibernate.ddl-auto=create-drop`, specifies that everytime the test deploys the service, the database is supposed to drop the tables it has and then create them (the creation is specified in the code).  
Like it was previously stated, these properties will only be used when:  

1. The tests make use of the actual database
2. The tests deploy the application instead of using mock versions of it (like in Test E)

## The sample project demonstrates three test strategies to assess an API (C, D and E) developed with SpringBoot. Which are the main/key differences?

+ E tests the application by deploying it in Tomcat. It does not use any mock in the process, making this way to test the API an "overkill"  
+ D tests the application, but this one does not actually deploy the application on the server  
+ C only tests the controller, mocking the beans that are necessary for the Controller to work
