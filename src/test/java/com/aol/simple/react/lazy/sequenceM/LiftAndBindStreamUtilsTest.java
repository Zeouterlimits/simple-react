package com.aol.simple.react.lazy.sequenceM;

import static com.aol.cyclops.lambda.api.AsAnyM.*;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Test;

import com.aol.cyclops.sequence.SequenceM;
import com.aol.cyclops.streams.StreamUtils;
import com.aol.simple.react.stream.traits.LazyFutureStream;

public class LiftAndBindStreamUtilsTest {
	@Test
	public void testLiftAndBindFile(){
		
		
		List<String> result = StreamUtils.flatMapFile(LazyFutureStream.of("input.file")
								
								.map(getClass().getClassLoader()::getResource)
								.peek(System.out::println)
								.map(URL::getFile),
								File::new)
								.collect(Collectors.toList());
		
		assertThat(result,equalTo(Arrays.asList("hello","world")));
	}
	@Test
	public void testLiftAndBindURL(){
		
		
		List<String> result = StreamUtils.flatMapURL(LazyFutureStream.of("input.file")
								
								,getClass().getClassLoader()::getResource)
								.collect(Collectors.toList());
		
		assertThat(result,equalTo(Arrays.asList("hello","world")));
	}
	@Test
	public void testLiftAndBindString(){
		
		
		List<Character> result = StreamUtils.flatMapCharSequence(LazyFutureStream.of("input.file"),i->"hello world")
									.collect(Collectors.toList());
		
		assertThat(result,equalTo(Arrays.asList('h','e','l','l','o',' ','w','o','r','l','d')));
	}
	@Test
	public void testLiftAndBindBufferedReader(){
		
		
		List<String> result = StreamUtils.flatMapBufferedReader(LazyFutureStream.of("input.file")
								.map(getClass().getClassLoader()::getResourceAsStream)
								.map(InputStreamReader::new)
								,in-> new BufferedReader(in))
								.peek(System.out::println)
								.collect(Collectors.toList());
		
		assertThat(result,equalTo(Arrays.asList("hello","world")));
	}
}
