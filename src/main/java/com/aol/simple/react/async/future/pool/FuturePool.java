package com.aol.simple.react.async.future.pool;

import java.util.Deque;
import java.util.function.Supplier;

import uk.co.real_logic.agrona.concurrent.ManyToOneConcurrentArrayQueue;
import lombok.AllArgsConstructor;

import com.aol.simple.react.async.future.FastFuture;
/**
 * Single consumer / multiple producer future pool
 * 
 * @author johnmcclean
 *
 */
@AllArgsConstructor
public class FuturePool {

	private final ManyToOneConcurrentArrayQueue<FastFuture> pool;
	private final int max;
	
	
	public<T> FastFuture<T> next(Supplier<FastFuture<T>> factory){
		if(pool.size()>0){
			
			FastFuture next = pool.poll();
			next.clearFast();
			return next;
		}
		
		return factory.get();
	}
	
	public <T> void done(FastFuture<T> f){
		if(pool.size()<max){
			
			pool.offer(f);
		}
		
	}	
}
