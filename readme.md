<img  alt="simple-react" src="https://cloud.githubusercontent.com/assets/9964792/8509314/b83d71d2-2294-11e5-859d-6959cda7aa1c.png">

# Powerful Future Streams & Async Data Structures for Java 8

[![Join the chat at https://gitter.im/aol/simple-react](https://badges.gitter.im/Join%20Chat.svg)](https://gitter.im/aol/simple-react?utm_source=badge&utm_medium=badge&utm_campaign=pr-badge&utm_content=badge)

# What is simple-react

simple-react is a set of 3 Streams / Stream-like structures for different Java 8 use cases. They are 

1. [SimpleReactStream](http://static.javadoc.io/com.aol.simplereact/simple-react/0.99.3/com/aol/simple/react/stream/traits/SimpleReactStream.html)
2. [LazyFutureStream](http://static.javadoc.io/com.aol.simplereact/simple-react/0.99.3/com/aol/simple/react/stream/traits/LazyFutureStream.html)
3. [SequenceM](http://static.javadoc.io/com.aol.cyclops/cyclops-sequence-api/6.0.2/com/aol/cyclops/sequence/SequenceM.html) 

## SimpleReactStream
SimpleReact Stream provides a simple API for aggregate operations on CompletableFutures, as such is particularly well suited to asynchronous, multi-threaded tasks - such as simultaneously performing multiple I/O actions. Implemented under the hood as a Stream of CompletableFutures the SimpleReact Stream has three areas of focus. 
1. Those tasks that can be modelled as chain of actions for each CompletableFuture. 
2. Group operations on the Futures themselves (such as anyOf / allOf)
3. Operations on the underlying Stream of Futures (zip, slice, skip, limit, skipUntil,firstOf, combineLatest etc)

## LazyFutureStream 
Provides a more advanced Stream-api over a custom Fast Future implementation. Particularly suited for advanced operations on data captured / retrieved via blocking I/O. Tasks that can be executed independently of each data element are handled by the Futures as per SimpleReact. Streaming tasks (such as windowing) that require access to a sequential Stream of data push results from each Future Task to a wait-free queue, from which data can be sequentially Streamed before being distributed across threads to the next set of FastFuture tasks.

LazyFutureStream implements the reactive-streams api can be either a reactive-streams publisher or subscriber.

LazyFutureStream extends SequenceM which in turn extends [jOOλ Seq](http://www.jooq.org/products/jOO%CE%BB/javadoc/0.9.7/org/jooq/lambda/Seq.html) which in turn extends java.util.stream.Stream

## SequenceM
Provides the same advanced Stream-api but implemented as a pure, fast, single-threaded Stream that is suitable for typical CPU bound Stream operations. SequenceM Streams can be executed on the current thread or asynchronously on a targeted alternative thread.

# Stream type overview

| FEATURE                                          | SimpleReact         | SequenceM           | LazyFutureStream    | JDK 8 Stream (sequential) | JDK 8 Stream (parallel) |
|--------------------------------------------------|---------------------|---------------------|---------------------|---------------------------|-------------------------| 
| Multithreading                                   | Yes                 | No                  | Yes                 | No                        | Yes                      | 
| Optimized for multithreaded blocking I/O         | Yes                 | No                  | Yes                 | No                        | No                      |
| Optimized for CPU bound operations               | No                  | Yes                 | No                  | Yes                       | Yes                     | 
| Eager / Lazy                                     | Eager               | Lazy                | Lazy                | Lazy                      | Lazy                    | 
| Free-threading (target single thread not current)| Yes                 | Yes                 | Yes                 | No                        | No (except 'hack')      | 
| Target different executors per stage             | Yes                 | No                  | Yes                 | No                        | No                      | 
| Concurrency configurability                      | Highly configurable | Yes                 | Highly configurable | No                        | Limited                 | 
| Failure recovery                                 | Yes                 | Yes                 | Yes                 | No                        | No                      | 
| Retry support                                    | Yes                 | Yes                 | Yes                 | No                        | No                      | 
| Time control                                     | No                  | Yes                 | Yes                 | No                        | No                      | 
| Batching  / windowing                        	   | No                  | Yes                 | Yes                 | No                        | No                      |
| Zipping                                     	   | Yes                 | Yes                 | Yes                 | No                        | No                      |
| Compatible with SimpleReact async datastructures | Yes                 | Yes                 | Yes                 | No                      	 | No                      | 
| each task can be executed independently          | Yes                 | No                  | Yes                 | No                        | No                      | 
| async terminal operations                        | No                  | Yes                 | Yes                 | No                        | No                      | 
| implements java.util.stream.Stream               | No                  | Yes                 | Yes                 | Yes                       | Yes                     | 
| reactive-streams support in simple-react         | Yes                 | Yes                 | Yes                 | Yes                       | Yes                     | 
| HotStreams support in simple-react               | No                  | Yes                 | Yes                 | Yes                       | Yes                     | 

 

simple-react is a [fast](https://github.com/aol/simple-react/wiki/Benchmark-for-SimpleReact) Reactive Streams ([http://www.reactive-streams.org/](http://www.reactive-streams.org/)) implementation that also implements, and significantly enhances, the [JDK 8 Stream](https://github.com/aol/simple-react/wiki/JDK-8-Reactive-Streams) interface, to provide [powerful asynchronous Streams](https://github.com/aol/simple-react/wiki/A-simple-API,-and-a-Rich-API) backed by your choice of [wait-free queues](https://github.com/aol/simple-react/wiki/Agrona-Wait-Free-Queues) (with or without mechanical sympathy) or blocking queues. simple-react reuses standard Java 8 functional interfaces and libraries such as CompletableFuture.

LazyFutureStream pulls 'chains' of asynchronous FastFuture tasks into existance (SimpleReact pull 'chains' of CompletableFutures into existence).

![screen shot 2015-07-02 at 9 43 51 pm](https://cloud.githubusercontent.com/assets/9964792/8487360/8565cd32-2103-11e5-8253-26813dae66f5.png)

## Build Status 

![Build health](https://travis-ci.org/aol/simple-react.svg)
 
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.aol.simplereact/simple-react/badge.svg)](https://maven-badges.herokuapp.com/maven-central/com.aol.simplereact/simple-react)


##Stream Types

![latest simplereactstreaming types](https://cloud.githubusercontent.com/assets/9964792/10453001/d7a6b3a2-71a0-11e5-9c05-3b91d69d5a34.png)
 
##Asynchronous datastructures

* Queue : async queue that can be used to join producing and consuming streams. Multiple consuming streams (if connected) compete for messages on the queue.

* Topic : async topic that can be used to join producing and consuming streams. Multiple consuming streams (if connected) recieve each message on the topic.

* Signal : async signal that can stream changes, backed by either a Topic or a Queue.

![simplereact datastructures](https://cloud.githubusercontent.com/assets/9964792/6320736/fb884daa-badd-11e4-801f-9a61a5213be2.png)
 


SimpleReact is a parallel Stream library that implements java.util.stream.Stream. Under the hood, SimpleReact manages parallel streams as a stream of CompletableFutures. SimpleReact provides a simple core API based on the Promises / A++ spec, while also providing a full rich range of options by implementing both JDK 8 Stream, and the scala-like [jOOλ Seq](http://www.jooq.org/products/jOO%CE%BB/javadoc/0.9.7/org/jooq/lambda/Seq.html). SimpleReact goes beyond the traditional Java 8 Streaming interface by offering failure recovery, capture and retry functionality.

It is an easy to use, concurrent, reactive programming library for JDK 8.  It provides a focused, simple and limited core Reactive API aimed at solving the 90% use case - but without adding complexity. It is a core goal of SimpleReact to integrate with JDK 8 Stream libraries for maximum reuse and plugability.

See [A Simple Api, and a Rich Api](https://github.com/aol/simple-react/wiki/A-simple-API,-and-a-Rich-API) for details on SimpleReact core and the java Streaming interfaces.

##Getting SimpleReact


* [Adding SimpleReact as a dependency](https://github.com/aol/simple-react/wiki/Adding-SimpleReact-as-a-dependency)
* [Search Maven](http://search.maven.org/#search%7Cga%7C1%7Ccom.aol.simplereact)


For Gradle : compile group: 'com.aol.simplereact', name:'simple-react', version:'0.99.3'

##Documentation

* [Reactive programming with Java 8 and simple-react: The Tutorial](https://medium.com/@johnmcclean/reactive-programming-with-java-8-and-simple-react-the-tutorial-3634f512eeb1)
* [wiki](https://github.com/aol/simple-react/wiki)
* [Javadoc](http://www.javadoc.io/doc/com.aol.simplereact/simple-react/0.99.3)
* [Articles on medium](https://medium.com/search?q=simplereact)




##Getting started

* [See an illustrative getting started example](https://github.com/aol/simple-react/wiki/Getting-started-with-a-simple-example)

* [What does Simple React do](https://github.com/aol/simple-react/wiki/What-does-SimpleReact-do%3F)

* [Understanding simple-react pull/push model](https://medium.com/@johnmcclean/reactive-programming-with-java-8-and-simple-react-pull-push-model-70751d63628f)

### Some less contrived / real world examples 

* [Example : Scaling microservices with NIO and SimpleReact](https://medium.com/@johnmcclean/scaling-up-microservices-with-nio-and-simplereact-b2e8f41fdd68)

 [Building a non blocking NIO rest client](https://github.com/aol/simple-react/wiki/Example-:-Building-a-non-blocking-NIO-rest-client)

* [Example : Plumbing Java 8 Streams with Queues, Topics and Signals](https://medium.com/@johnmcclean/plumbing-java-8-streams-with-queues-topics-and-signals-d9a71eafbbcc)

* [Example : Bulk loading files](https://github.com/aol/simple-react/wiki/Example-:-Bulk-loading-files)

* [Example : Implementing a Quorum](https://github.com/aol/simple-react/wiki/Example-:-Implementing-a-Quorum)

##Why SimpleReact

Why daisy-chain together CompletableFuture's by hand? SimpleReact allows you to put together sophisticated concurrent chains of CompletableFutures with a very easy to use API.

SimpleReact is built on top of JDK standard libraries and unlike other Reactive implementations for Java, specifically targets JDK 8 and thus reuses rather than reinvents  Streams, Functional interfaces etc. SimpleReact augments the *parallel* Streams functionality in JDK by providing a facade over both the Streams and CompletableFuture apis. Under-the-hood, SimpleReact *is* a Stream of CompletableFutures, and presents that externally as an api somewhat inspired by the Javascript Promises / A+ Spec (https://promisesaplus.com/).

Everything is concurrent in SimpleReact. While this does limit some of the syntax-sugar we can provide directly, the small & focused SimpleReact Api together with the Apis of the underlying JDK 8 primitives offer often surprising levels of power and flexibility.

* [See an example of using CompletableFuture directly with SimpleReact](https://github.com/aol/simple-react/wiki/Example-:-Reacting-to-Asynchronous-Events-with-a-Stream-of-CompletableFutures)

#SimpleReact Streams and commands

* [List of operators] (https://github.com/aol/simple-react/wiki/A-simple-API,-and-a-Rich-API)
* [Batching, control, sharding and zipping operators ](https://github.com/aol/simple-react/wiki/Batching,-Time-Control,-Sharding-and-Zipping-Operators)

##limit



###LazyFutureStream, SimpleReactStream, SequenceM

When a limit is applied to  a LazyFutureStream it is applied to the tasks before they start. 

![lazyfuturestream limit](https://cloud.githubusercontent.com/assets/9964792/6320738/04cd3678-bade-11e4-9268-b224b6c2dd21.png)




##skip

Skip will perform as in the same way as Limit for all three Stream types but skips the first X data points instead.


###LazyFutureStream
For LazyFutureStream specifying a skip will skip the first X tasks specified in the previous stage.

![lazyfuturestream - skip](https://cloud.githubusercontent.com/assets/9964792/6329113/7291ff54-bb65-11e4-8d40-024e731a588e.png)


##map / then

###EagerFutureStream, LazyFutureStream, SimpleReactStream

For all three Streams map or then converts input data in one format to output data in another.

![stream map/then](https://cloud.githubusercontent.com/assets/9964792/6320750/3e6ccaa6-bade-11e4-9ebf-ad63996c489f.png)

##retry

###EagerFutureStream, LazyFutureStream, SimpleReactStream

Retry allows a task in a stage to be retried if it fails

![stream retry](https://cloud.githubusercontent.com/assets/9964792/6320754/4ea4061e-bade-11e4-8692-481e0dc0e3f9.png)

##onFail

###LazyFutureStream, SimpleReactStream
For all three Streams onFail allows recovery from a Streaming stage that fails.

![stream onFail](https://cloud.githubusercontent.com/assets/9964792/6320747/34c8d666-bade-11e4-817d-2d4c5c3fa6e7.png)

##capture

### LazyFutureStream, SimpleReactStream

Capture allows error handling for unrecoverable errors.

![eagerfuturestream capture](https://cloud.githubusercontent.com/assets/9964792/6329474/8ee5cdea-bb68-11e4-96c3-d8335879408e.png)

##flatMap

###EagerFutureStream, LazyFutureStream, SimpleReactStream

For all three Streams specifying a flatMap splits a single result into multiple tasks by returning a Stream from the flatMap method.

![stream flatMap](https://cloud.githubusercontent.com/assets/9964792/6320742/18ce599a-bade-11e4-8bdb-8909c71da06c.png)

##allOf (async collect)
###SimpleReactStream

allOf is the inverse of flatMap. It rolls up a Stream from a previous stage, asynchronously into a single collection for further processing as a group.

![stream allOf](https://cloud.githubusercontent.com/assets/9964792/6320739/0a98c8ba-bade-11e4-9097-0b3209a5aba1.png)

##anyOf
###SimpleReactStream

anyOf progresses the flow with the first result received.

![eagerfuturestream anyof](https://cloud.githubusercontent.com/assets/9964792/6586388/01c07982-c771-11e4-90fc-9fae9ec17aba.png)


##block / collect
###SequenceM, LazyFutureStream, SimpleReactStream

Block behaves like allOf except that it blocks the calling thread until the Stream has been processed.

![eagerfuturestream block](https://cloud.githubusercontent.com/assets/9964792/6329382/9d475a76-bb67-11e4-9fe9-b081046a659b.png)

##zip
###SequenceM, LazyFutureStream, SimpleReactStream

Zip merges two streams by taking the next available result from each stream. For SimpleReactStreams the underlying Stream of futures is zipped, connnecting two future objects into a Tuple2.

![stream zip](https://cloud.githubusercontent.com/assets/9964792/6320745/2a52717e-bade-11e4-93c7-8965a2d6f3ab.png)


##toQueue
###LazyFutureStream, SimpleReactStream

toQueue creates a new simplereact.aysnc.Queue that is populated asynchronously by the current Stream. Another Stream (Consumer) can be created from the Queue by calling queue.toStream()
![eagerfuturestream toqueue](https://cloud.githubusercontent.com/assets/9964792/6329104/5eb15034-bb65-11e4-9f18-82c34065465f.png)

#Choosing A Stream Type

The key components in choosing what type of Stream to create are :

1. Eager or Lazy
2. Multi-threaded blocking I/O or CPU bound tasks
4. What data a stream should be provided with
5. Optimising Stream performance

##Eager Streams and Lazy Streams

SimpleReactStreams can be either Eager or Lazy, by default they are Eager.

Eager Streams start processing immediately, while Lazy Streams start processing when a terminal operation is invoked.

SimpleReact provides builder classes, and JDK 8 Stream style factory methods on the Stream itself that can be used to create appropriate Streams.

*SimpleReact - builder class for SimpleReact

*LazyReact - builder class for LazyFutureStreams



###Configuring concurrency

TaskExecutor and RetryExecutor configuration can be changed on per stage basis of in any of the SimpleReact streams

##Data flow of the SimpleReactStream API


### SimpleReactStream : A Simple Fluent Api for Functional Reactive Programming with Java 8


*EagerFutureStream and LazyFutureStream* have this functionality in addition to JDK 8 Stream functionality and [jOOλ Seq](http://www.jooq.org/products/jOO%CE%BB/javadoc/0.9.5/org/jooq/lambda/Seq.html) methods, applied to a Stream of JDK 8 CompletableFutures.

SimpleReact starts with an array of Suppliers which generate data other functions will react to. Each supplier will be passed to an Executor to be executed, potentially on a separate thread. Each additional step defined when calling Simple React will also be added as a linked task, also to be executed, potentially on a separate thread.

##Example 1 : reacting with completablefutures

React **with**
```java
			List<CompletableFuture<Integer>> futures = new SimpleReact()
				.<Integer> react(() -> 1, () -> 2, () -> 3)
				.with(it -> it * 100);
```
In this instance, 3 suppliers generate 3 numbers. These may be executed in parallel, when they complete each number will be multiplied by 100 - as a separate parrellel task (handled by a ForkJoinPool or configurable task executor). A List of Future objects will be returned immediately from Simple React and the tasks will be executed asynchronously.
React with does not block.
##Example 2 : chaining

React **then** / **map**
```java
	 	new SimpleReact()
	 			.<Integer> react(() -> 1, () -> 2, () -> 3)
				.then(it -> it * 100)
				.then(it -> "*" + it)

```
React then allows event reactors to be chained. Unlike React with, which returns a collection of Future references, React then is a fluent interface that returns the React builder - allowing further reactors to be added to the chain.
React then does not block.
React with can be called after React then which gives access to the full CompleteableFuture api. CompleteableFutures can be passed back into SimpleReact via SimpleReact.react(streamOfCompleteableFutures);
See this blog post for examples of what can be achieved via CompleteableFuture :- http://www.nurkiewicz.com/2013/12/promises-and-completablefuture.html

React **retry**
```java
	 	new SimpleReact()
	 			.<Integer> react(() -> url1, () -> url2, () -> url3)
				.retry(it -> readRemoteService(it))
				.then(it ->  extractData(it))
				.then(it -> writeToQueue(it))

```
Retry allows a stage to be retried a configurable number of times. Retry functionlity is provided by async-retry (https://github.com/nurkiewicz/async-retry), that provides a very configurable mechanism for asynchronous retrying based on CompletableFutures.
In SimpleReact a RetryExecutors can be plugged in at any stage. Once plugged in it will be used for the current and subsequent stages of the Stream (until replaced).

e.g.  
```java
	new SimpleReact()
		.<Integer> react(() -> url1, () -> url2, () -> url3)
		.withRetrier(retryExecutor)
		.retry(it -> readRemoteService(it))
```
To configure a retry executor follow the instructions on https://github.com/nurkiewicz/async-retry. E.g :-

```java		
    ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
    RetryExecutor executor = new AsyncRetryExecutor(scheduler).
       retryOn(SocketException.class).
       withExponentialBackoff(500, 2).     //500ms times 2 after each retry
       withMaxDelay(10_000).               //10 seconds
       withUniformJitter().                //add between +/- 100 ms randomly
       withMaxRetries(20);
```    
React and **flatMap**       
       
##Example 3: blocking

React and **block**
```java
			List<String> strings = new SimpleReact()
				.<Integer> react(() -> 1, () -> 2, () -> 3)
				.then(it -> it * 100)
				.then(it -> "*" + it)
				.block();
```						
In this example, once the current thread of execution meets the React block method, it will block until all tasks have been completed. The result will be returned as a List. The Reactive tasks triggered by the Suppliers are non-blocking, and are not impacted by the block method until they are complete. Block, only blocks the current thread.

##Example 4: breakout

Sometimes you may not need to block until all the work is complete, one result or a subset may be enough. To faciliate this, block can accept a Predicate functional interface that will allow SimpleReact to stop blocking the current thread when the Predicate has been fulfilled. E.g.
```java
			List<String> strings = new SimpleReact()
				.<Integer> react(() -> 1, () -> 2, () -> 3)
				.then(it -> it * 100)
				.then(it -> "*" + it)
				.block(status -> status.getCompleted()>1);
```
In this example the current thread will unblock once more than one result has been returned. The available fields on the status object are :-
completed
errors
total
elapsedMillis

##Example 5 : onFail

React **onFail**
onFail allows disaster recovery for each task (a separate onFail should be configured for each react phase that can fail). E.g. if reading data from an external service fails, but default value is acceptable - onFail is a suitable mechanism to set the default value.
```java
			List<String> strings = new SimpleReact()
				.<Integer> react(() -> 100, () -> 2, () -> 3)
				.then(it -> {
					if (it == 100)
						throw new RuntimeException("boo!");
		
					return it;
				})
				.onFail(e -> 1)
				.then(it -> "*" + it)
				.block();
```
In this example, should the first "then" phase fail, the default value of 1 will be used instead.

##Example 6: non-blocking

React and **allOf**

allOf is a non-blocking equivalent of block. The current thread is not impacted by the calculations, but the reactive chain does not continue until all currently alloted tasks complete. The allOf task is then provided with a list of the results from the previous tasks in the chain. Any parallelStreams used inside allOf will reuse the SimpleReact ExecutorService - if it is a ForkJoinPool (which it is by default), rather than the Common ForkJoinPool parallelStreams use by default. 
```java
        	boolean blocked[] = {false};
			new SimpleReact()
				.<Integer> react(() -> 1, () -> 2, () -> 3)	
				.then(it -> {
					try {
						Thread.sleep(50000);
					} catch (Exception e) {
						
					}
					blocked[0] =true;
					return 10;
				})
				.allOf( it -> it.size());

		
			assertThat(blocked[0],is(false));
```
In this example, the current thread will continue and assert that it is not blocked, allOf could continue and be executed in a separate thread.

first() is a useful method to extract a single value from a dataflow that ends in allOf. E.g. 
```java

        	boolean blocked[] = {false};
			int size = new SimpleReact()
				.<Integer> react(() -> 1, () -> 2, () -> 3)	
				.then(it -> {
					try {
						Thread.sleep(50000);
					} catch (Exception e) {
						
					}
					blocked[0] =true;
					return 10;
				})
				.allOf( it -> it.size()).first();

		
			assertThat(blocked[0],is(false));
```
##Example 7: non-blocking with the Stream api
```java
             List<Integer> result =new SimpleReact()
             	.<Integer> react(() -> 1, () -> 2, () -> 3)
				.then(it -> {
					return it*200;
				})
				.<Integer,Integer>allOf( (it )->{
					return it.parallelStream()
					.filter( f -> f>300)
					.map(m -> m-5)
					.reduce(0, (acc,next) -> acc+next); 
				}).block();

		
			assertThat(result.size(),is(1));
			assertThat(result.get(0),is(990));

```
In this example we block the current thread to get the final result, the allOf task uses the Streams api to setup another FRP chain that takes the inputs from our initial parellel jobs ([1,2,3] -> [200,400,600]), and does a filter / map/ reduce on them in parallel.

##Example 6 : capturing exceptions

React *capture*

onFail is used for disaster recovery (when it is possible to recover) - capture is used to capture those occasions where the full pipeline has failed and is unrecoverable.
```java
			List<String> strings = new SimpleReact()
				.<Integer> react(() -> 1, () -> 2, () -> 3)
				.then(it -> it * 100)
				.then(it -> {
					if (it == 100)
						throw new RuntimeException("boo!");
		
					return it;
				})
				.onFail(e -> 1)
				.then(it -> "*" + it)
				.then(it -> {
					
					if ("*200".equals(it))
						throw new RuntimeException("boo!");
		
					return it;
				})
				.capture(e -> logger.error(e.getMessage(),e))
				.block();

```
In this case, strings will only contain the two successful results (for ()->1 and ()->3), an exception for the chain starting from Supplier ()->2 will be logged by capture. Capture will not capture the exception thrown when an Integer value of 100 is found, but will catch the exception when the String value "*200" is passed along the chain.

##Example 7 : using the Streams Api

React and the *Streams Api*

A SimpleReact Stage implements both [java.util.stream.Stream](http://docs.oracle.com/javase/8/docs/api/java/util/stream/Stream.html) and [org.jooq.lambda](http://www.jooq.org/products/jOO%CE%BB/javadoc/0.9.5/org/jooq/lambda/Seq.html) Streaming interfaces. This section describes how to interact with the JDK implementation of java.util.stream.Stream. 

It is possible to reuse the internal SimpleReact ExecutorService for JDK 8 parallelStreams. SimpleReact uses a ForkJoinPool as the ExecutorService by default, and to reuse the ExecutorService with parallelStreams it must be a ForkJoinPool - so if you want to supply your own make sure it is also a ForkJoinPool. The easiest way to do this is via the submitAndBlock method.

*Detailed Explanation* A mechanism to share the SimpleReact ExecutorService with JDK Parallel Streams is provided via the *collectResults* method. NB This will only actually share the ExecutorService if it is an instance of ForkJoinPool (limitation imposed on JDK side). This method collects the results from the current active tasks, and clients are given the full range of SimpleReact blocking options.  The results will then be made available to a user provided function when the *submit* method is called. The submit method will ensure that the user function is executed in such a way that the SimpleReact ExecutorService will also be used by ParallelStreams. It does this by submiting the user function & results to the ForkJoinPool and ParallelStreams has been written in such away to resuse any ForkJoinPool it is executed inside.
 
A way to merge all these steps into a single method is also provided (submitAndBlock). 

Example :
```java
		 Integer result = new SimpleReact()
				.<Integer> react(() -> 1, () -> 2, () -> 3)
				.then(it -> it * 200)
				.<List<Integer>,Integer>submitAndblock(
						it -> it.parallelStream()
								.filter(f -> f > 300)
								.map(m -> m - 5)
								.reduce(0, (acc, next) -> acc + next));
```								
To use a different ExecutorService than SimpleReact's internal ExecutorService leverae parallelStream directly from block() 
```java
			ImmutableMap<String,Integer> dataSizes = new SimpleReact()
				.<Integer> react(() -> 1, () -> 2, () -> 30,()->400)
				.then(it -> it * 100)
				.then(it -> "*" + it)
				.<String>block()
				.parallelStream()
				.filter( it -> it.length()>3)
				.map(it -> ImmutableMap.of(it,it.length()))
				.reduce(ImmutableMap.of(),  (acc, next) -> ImmutableMap.<String, Integer>builder()
					      .putAll(acc)
					      .putAll(next)
					      .build());
```
In this example the converted Strings are filtered by length and an ImmutableMap created using the Java 8 Streams Api.


##Example 8 : peeking at the current stage

Particularly during debugging and troubleshooting it can be very useful to check the results at a given stage in the dataflow. Just like within the Streams Api, the peek method can allow you to do this.

Example :
```java
	List<String> strings = new SimpleReact()
				.<Integer> react(() -> 1, () -> 2, () -> 3)
				.then(it -> it * 100)
				.<String>then(it -> "*" + it)
				.peek((String it) -> logger.info("Value is {}",it))
				.block();
```				
##Example 9 : filtering results

The filter method allows users to filter out results they are not interested in.

Example :
```java
	List<String> result = new SimpleReact()
				.<Integer> react(() -> 1, () -> 2, () -> 3)
				.then(it -> "*" + it)
				.filter(it -> it.startsWith("*1"))
				.block();
```
##Example 10 : Concurrent iteration

SimpleReact provides a mechanism for starting a dataflow an iterator.
```java
	List<Integer> list = Arrays.asList(1,2,3,4);
	List<String> strings = new SimpleReact()
				.<Integer> react(list.iterator() ,list.size())
				.then(it -> it * 100)
				.then(it -> "*" + it)
				.block(); 
```				
##Example 11 : Infinite generators & iterators

Since v0.2 SimpleReact supports fully Infinite Streams, See :- 

https://github.com/aol/simple-react/wiki/Infinite-Streams-in-SimpleReact
https://medium.com/@johnmcclean/plumbing-java-8-streams-with-queues-topics-and-signals-d9a71eafbbcc


SimpleReact provides a mechanism over JDK Stream iterate and generate which will create 'infinite' Streams of data to react to. Because SimpleReact eagerly collects these Streams (when converting to *active* CompletableFutures), the SimpleReact api always requires a maximum size parameter to be set.
```java
	List<String> strings = new SimpleReact()
				.<Integer> react(() -> count++ ,SimpleReact.times(4))
				.then(it -> it * 100)
				.then(it -> "*" + it)
				.capture(e -> capture++)
				.block();
```
##Example 12 : Splitting and merging SimpleReact dataflows

A simple example below where a dataflow is split into 3, processed separately then merged back into a single flow.
```java
	Stage<String> stage = new SimpleReact()
				.<Integer> react(() -> 1, () -> 2, () -> 3)
				.then(it -> "*" + it);
		Stage<String> stage1 = stage.filter(it -> it.startsWith("*1"));
		Stage<String> stage2 = stage.filter(it -> it.startsWith("*2"));
		Stage<String> stage3 = stage.filter(it -> it.startsWith("*3"));
		
		stage1 = stage1.then(it -> it+"!");
		stage2 = stage2.then(it -> it+"*");
		stage3 = stage3.then(it -> it+"%");
		
		List<String> result = stage1.merge(stage2).merge(stage3).block();
```
      #Feature matrix


#Queues, Topics, Signals quick overview

Queues can be populated asyncrhonously by a Stream and read at will be consumers. Each message added to a queue can only be read by a single consumer, once a consuming Stream has removed a message from the Queue it is gone.

![Visualisation of a SimpleReact dataflow : Queues](https://cloud.githubusercontent.com/assets/9964792/6219467/c48aed46-b621-11e4-9aaa-9dcea3019024.png)

Topics can be populated asynchronously by a Stream and read at will by consumers. Each consumer is guaranteed to recieve every message sent to a topic once connected.

![Visualisation of a SimpleReact dataflow : Topics](https://cloud.githubusercontent.com/assets/9964792/6219474/db8243aa-b621-11e4-89d3-b854aa00adce.png)

Signals track changes, and can provide those changes as continuous or discrete Streams.

![Visualisation of a SimpleReact dataflow : Signals](https://cloud.githubusercontent.com/assets/9964792/6219478/e18ca33a-b621-11e4-9a51-a07e7c5364d7.png)



      
		
# License

Simple React is licensed under the Apache 2.0 license.		

http://www.apache.org/licenses/LICENSE-2.0
