package com.aol.simple.react.lazy;

import static com.aol.simple.react.stream.traits.LazyFutureStream.of;

import org.jooq.lambda.tuple.Tuple2;
import org.jooq.lambda.tuple.Tuple3;
import org.jooq.lambda.tuple.Tuple4;
import org.junit.Test;

import com.aol.cyclops.sequence.SequenceM;
import static org.hamcrest.Matchers.instanceOf;
import static org.junit.Assert.*;
public class DuplicationTest {
	@Test
	public void testDuplicate(){
		 Tuple2<SequenceM<Integer>, SequenceM<Integer>> copies =of(1,2,3,4,5,6).duplicateSequence();
		 assertTrue(copies.v1.anyMatch(i->i==2));
		 assertTrue(copies.v2.anyMatch(i->i==2));
	}
	@Test
	public void testTriplicate(){
		 Tuple3<SequenceM<Integer>, SequenceM<Integer>, SequenceM<Integer>> copies =of(1,2,3,4,5,6).triplicate();
		 assertTrue(copies.v1.anyMatch(i->i==2));
		 assertTrue(copies.v2.anyMatch(i->i==2));
		 assertTrue(copies.v3.anyMatch(i->i==2));
	}
	
	@Test
	public void testQuadriplicate(){
		 Tuple4<SequenceM<Integer>, SequenceM<Integer>, SequenceM<Integer>,SequenceM<Integer>> copies =of(1,2,3,4,5,6).quadruplicate();
		 assertTrue(copies.v1.anyMatch(i->i==2));
		 assertTrue(copies.v2.anyMatch(i->i==2));
		 assertTrue(copies.v3.anyMatch(i->i==2));
		 assertTrue(copies.v4.anyMatch(i->i==2));
	}

	@Test
	public void testDuplicateFilter(){
		 Tuple2<SequenceM<Integer>, SequenceM<Integer>> copies =of(1,2,3,4,5,6).duplicateSequence();
		 assertTrue(copies.v1.filter(i->i%2==0).toList().size()==3);
		 assertTrue(copies.v2.filter(i->i%2==0).toList().size()==3);
	} 
	@Test
	public void testTriplicateFilter(){
		Tuple3<SequenceM<Integer>, SequenceM<Integer>, SequenceM<Integer>> copies =of(1,2,3,4,5,6).triplicate();
		 assertTrue(copies.v1.filter(i->i%2==0).toList().size()==3);
		 assertTrue(copies.v2.filter(i->i%2==0).toList().size()==3);
		 assertTrue(copies.v3.filter(i->i%2==0).toList().size()==3);
	} 
	@Test
	public void testQuadriplicateFilter(){
		 Tuple4<SequenceM<Integer>, SequenceM<Integer>, SequenceM<Integer>,SequenceM<Integer>> copies =of(1,2,3,4,5,6).quadruplicate();
		 assertTrue(copies.v1.filter(i->i%2==0).toList().size()==3);
		 assertTrue(copies.v2.filter(i->i%2==0).toList().size()==3);
		 assertTrue(copies.v3.filter(i->i%2==0).toList().size()==3);
		 assertTrue(copies.v4.filter(i->i%2==0).toList().size()==3);
	}
	@Test
	public void testDuplicateLimit(){
		 Tuple2<SequenceM<Integer>, SequenceM<Integer>> copies =of(1,2,3,4,5,6).duplicateSequence();
		 assertTrue(copies.v1.limit(3).toList().size()==3);
		 assertTrue(copies.v2.limit(3).toList().size()==3);
	} 
	@Test
	public void testTriplicateLimit(){
		Tuple3<SequenceM<Integer>, SequenceM<Integer>, SequenceM<Integer>> copies =of(1,2,3,4,5,6).triplicate();
		 assertTrue(copies.v1.limit(3).toList().size()==3);
		 assertTrue(copies.v2.limit(3).toList().size()==3);
		 assertTrue(copies.v3.limit(3).toList().size()==3);
	} 
	@Test
	public void testQuadriplicateLimit(){
		 Tuple4<SequenceM<Integer>, SequenceM<Integer>, SequenceM<Integer>,SequenceM<Integer>> copies =of(1,2,3,4,5,6).quadruplicate();
		 assertTrue(copies.v1.limit(3).toList().size()==3);
		 assertTrue(copies.v2.limit(3).toList().size()==3);
		 assertTrue(copies.v3.limit(3).toList().size()==3);
		 assertTrue(copies.v4.limit(3).toList().size()==3);
	}
}
