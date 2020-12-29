# NOTES

## CONFIGURATIONS
```axon-spring-boot-starter``` comes with auto configuration, command handlers are detected through @aggregate and @commandHandler annotations.
If nothing is specified, Spring boot will try to connect Axon Server @ localhost with the default port.

### Problems
- Create the file `spring-devtools.properties` in the following folder `src/main/resources/META-INF` to solve TODO: ADD DESCRIPTION TO THE PROBLEM FACED WITHOUT THIS FILE.
- Axon Server is **not able to discover the command handler** with the auto configuration if the ```@Aggregate``` has also a ```@Profile``` annotation, same for the Query model with ```@Component``` annotation.
- Dockerizing the Spring boot application throws the following error: (problem with h2???)

```
The web application [ROOT] appears to have started a thread named [HikariPool-1 housekeeper] but has failed to stop it. This is very likely to create a memory leak. Stack trace of thread:
 java.base@12-ea/jdk.internal.misc.Unsafe.park(Native Method)
 java.base@12-ea/java.util.concurrent.locks.LockSupport.parkNanos(LockSupport.java:235)
 java.base@12-ea/java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject.awaitNanos(AbstractQueuedSynchronizer.java:2123)
 java.base@12-ea/java.util.concurrent.ScheduledThreadPoolExecutor$DelayedWorkQueue.take(ScheduledThreadPoolExecutor.java:1182)
 java.base@12-ea/java.util.concurrent.ScheduledThreadPoolExecutor$DelayedWorkQueue.take(ScheduledThreadPoolExecutor.java:899)
 java.base@12-ea/java.util.concurrent.ThreadPoolExecutor.getTask(ThreadPoolExecutor.java:1054)
 java.base@12-ea/java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1114)
 java.base@12-ea/java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:628)
 java.base@12-ea/java.lang.Thread.run(Thread.java:835)
2020-12-19 12:48:20.506  WARN 1 --- [           main] o.a.c.loader.WebappClassLoaderBase       : The web application [ROOT] appears to have started a thread named [HikariPool-1 connection adder] but has failed to stop it. This is very likely to create a memory leak. Stack trace of thread:
 java.base@12-ea/jdk.internal.misc.Unsafe.park(Native Method)
 java.base@12-ea/java.util.concurrent.locks.LockSupport.parkNanos(LockSupport.java:235)
 java.base@12-ea/java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject.awaitNanos(AbstractQueuedSynchronizer.java:2123)
 java.base@12-ea/java.util.concurrent.LinkedBlockingQueue.poll(LinkedBlockingQueue.java:458)
 java.base@12-ea/java.util.concurrent.ThreadPoolExecutor.getTask(ThreadPoolExecutor.java:1053)
 java.base@12-ea/java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1114)
 java.base@12-ea/java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:628)
 java.base@12-ea/java.lang.Thread.run(Thread.java:835)
2020-12-19 12:48:20.588  INFO 1 --- [           main] ConditionEvaluationReportLoggingListener : 

Error starting ApplicationContext. To display the conditions report re-run your application with 'debug' enabled.
2020-12-19 12:48:20.662 ERROR 1 --- [           main] o.s.boot.SpringApplication               : Application run failed

java.lang.RuntimeException: java.util.concurrent.ExecutionException: org.springframework.jdbc.datasource.init.UncategorizedScriptException: Failed to execute database script from resource [URL [jar:file:/app.jar!/BOOT-INF/classes!/data.sql]]; nested exception is java.lang.IllegalArgumentException: 'script' must not be null or empty
        at org.springframework.boot.autoconfigure.orm.jpa.DataSourceInitializedPublisher$DataSourceInitializationCompletionListener.onApplicationEvent(DataSourceInitializedPublisher.java:151) ~[spring-boot-autoconfigure-2.4.0.jar!/:2.4.0]
        at org.springframework.boot.autoconfigure.orm.jpa.DataSourceInitializedPublisher$DataSourceInitializationCompletionListener.onApplicationEvent(DataSourceInitializedPublisher.java:138) ~[spring-boot-autoconfigure-2.4.0.jar!/:2.4.0]
        at org.springframework.context.event.SimpleApplicationEventMulticaster.doInvokeListener(SimpleApplicationEventMulticaster.java:203) ~[spring-context-5.3.1.jar!/:5.3.1]
        at org.springframework.context.event.SimpleApplicationEventMulticaster.invokeListener(SimpleApplicationEventMulticaster.java:196) ~[spring-context-5.3.1.jar!/:5.3.1]
        at org.springframework.context.event.SimpleApplicationEventMulticaster.multicastEvent(SimpleApplicationEventMulticaster.java:161) ~[spring-context-5.3.1.jar!/:5.3.1]
        at org.springframework.context.support.AbstractApplicationContext.publishEvent(AbstractApplicationContext.java:426) ~[spring-context-5.3.1.jar!/:5.3.1]
        at org.springframework.context.support.AbstractApplicationContext.publishEvent(AbstractApplicationContext.java:383) ~[spring-context-5.3.1.jar!/:5.3.1]
        at org.springframework.context.support.AbstractApplicationContext.finishRefresh(AbstractApplicationContext.java:945) ~[spring-context-5.3.1.jar!/:5.3.1]
        at org.springframework.context.support.AbstractApplicationContext.refresh(AbstractApplicationContext.java:591) ~[spring-context-5.3.1.jar!/:5.3.1]
        at org.springframework.boot.web.servlet.context.ServletWebServerApplicationContext.refresh(ServletWebServerApplicationContext.java:144) ~[spring-boot-2.4.0.jar!/:2.4.0]
        at org.springframework.boot.SpringApplication.refresh(SpringApplication.java:767) ~[spring-boot-2.4.0.jar!/:2.4.0]
        at org.springframework.boot.SpringApplication.refresh(SpringApplication.java:759) ~[spring-boot-2.4.0.jar!/:2.4.0]
        at org.springframework.boot.SpringApplication.refreshContext(SpringApplication.java:426) ~[spring-boot-2.4.0.jar!/:2.4.0]
        at org.springframework.boot.SpringApplication.run(SpringApplication.java:326) ~[spring-boot-2.4.0.jar!/:2.4.0]
        at org.springframework.boot.SpringApplication.run(SpringApplication.java:1309) ~[spring-boot-2.4.0.jar!/:2.4.0]
        at org.springframework.boot.SpringApplication.run(SpringApplication.java:1298) ~[spring-boot-2.4.0.jar!/:2.4.0]
        at com.fundev.adt.AdtApplication.main(AdtApplication.java:10) ~[classes!/:0.0.1-SNAPSHOT]
        at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method) ~[na:na]
        at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62) ~[na:na]
        at java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43) ~[na:na]
        at java.base/java.lang.reflect.Method.invoke(Method.java:567) ~[na:na]
        at org.springframework.boot.loader.MainMethodRunner.run(MainMethodRunner.java:49) ~[app.jar:0.0.1-SNAPSHOT]
        at org.springframework.boot.loader.Launcher.launch(Launcher.java:107) ~[app.jar:0.0.1-SNAPSHOT]
        at org.springframework.boot.loader.Launcher.launch(Launcher.java:58) ~[app.jar:0.0.1-SNAPSHOT]
        at org.springframework.boot.loader.JarLauncher.main(JarLauncher.java:88) ~[app.jar:0.0.1-SNAPSHOT]
Caused by: java.util.concurrent.ExecutionException: org.springframework.jdbc.datasource.init.UncategorizedScriptException: Failed to execute database script from resource [URL [jar:file:/app.jar!/BOOT-INF/classes!/data.sql]]; nested exception is java.lang.IllegalArgumentException: 'script' must not be null or empty
        at java.base/java.util.concurrent.FutureTask.report(FutureTask.java:122) ~[na:na]
        at java.base/java.util.concurrent.FutureTask.get(FutureTask.java:191) ~[na:na]
        at org.springframework.boot.autoconfigure.orm.jpa.DataSourceInitializedPublisher$DataSourceInitializationCompletionListener.onApplicationEvent(DataSourceInitializedPublisher.java:148) ~[spring-boot-autoconfigure-2.4.0.jar!/:2.4.0]
        ... 24 common frames omitted

```

## Repositories
Depending on your underlying persistence storage and auditing needs, there are a number of base implementations that provide basic functionality needed by most repositories. Axon Framework makes a distinction between repositories that save the current state of the aggregate (see Standard repositories), and those that store the events of an aggregate (see Event Sourcing repositories).

Note that the Repository interface does not prescribe a delete(identifier) method. Deleting aggregates is done by invoking the AggregateLifecycle.markDeleted() method from within an aggregate. Deleting an aggregate is a state migration like any other, with the only difference that it is irreversible in many cases. You should create your own meaningful method on your aggregate which sets the aggregate's state to "deleted". This also allows you to register any events that you would like to have published.

### Aggregate repository
In CQRS, repositories only need to be able to find aggregates based on their unique identifier. Any other types of queries should be performed against the query database.

### Query repository

# PROJECT STRUCTURE
```
+ command
|__ contains command handlers
|
+ coreapi
|__ apis, invoke commands, queries
|
+ exceptions
|__ custom exceptions
|
+ query
|__ read model with repository query updates
|
+ controllers
```


# Axon vs MBassador (event bus: notes based on the implementation done in EOCNurse)
- Event Sourcing is the process where Events are not only generated as the side-effects of a Command, but also form the source of the state.

   *In EOCNurse there is no guarantee that the events stored are the source of the state because there is no transaction (Saga) after the disptached and also corrupted data.
   Accessing through this events is also onerous.*
   *We did store the events with the only pourpose of history*
   
   > Having a reliable audit trail has not only proven useful for auditability of a system, it also provides the information necessary to build new view models, do data analysis and provide a solid basis for machine learning algorithms.

## Hybrid / Polyglot environments
In practice, many microservices based systems run in a polyglot environment. Different services will run on a different technology stack.
In these environments, it is even more important to ensure Contexts Boundaries are properly guarded and provide decent anti-corruption layers, where applicable.


It is unlikely all used technology stacks follow the same messaging based approach that Axon applications do.
However, that doesn't mean the concepts need to be abandoned.
You can still benefit from many of the advantages of explicit messaging.
In such environments, **anti-corruption layers** could be implemented as components that handle commands, events and queries, and execute other types of calls (e.g. REST calls) to the external services.
This way, components that work with explicit messaging do not need to worry about polling external services for changes, or be influenced by the technical challenges caused by different types of APIs.