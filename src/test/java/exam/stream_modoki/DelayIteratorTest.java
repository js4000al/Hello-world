package exam.stream_modoki;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import static test.UT.*;

import java.util.Collections;
import java.util.NoSuchElementException;

import org.junit.Test;

import exam.stream_modoki.DelayIterator;


public class DelayIteratorTest {

	@Test
	public void hasNext_空() {
		DelayIterator<Integer> it = new DelayIterator<>(Collections.emptyIterator());
		assertThat(it.hasNext(), is(false));
		assertThat(it.hasNext(), is(false));
	}
	@Test(expected=NoSuchElementException.class)
	public void peek_空() {
		DelayIterator<Integer> it = new DelayIterator<>(Collections.emptyIterator());
		it.peek();
	}
	@Test(expected=NoSuchElementException.class)
	public void next_空() {
		DelayIterator<Integer> it = new DelayIterator<>(Collections.emptyIterator());
		it.next();
	}
	
	@Test
	public void test_1要素() {
		assertThat(new DelayIterator<>(lst(1).iterator()), isIterator(1));
	}
	@Test(expected=NoSuchElementException.class)
	public void peek_1要素() {
		DelayIterator<Integer> it = new DelayIterator<>(lst(1).iterator());
		assertThat(it.next(), is(1));
		it.peek();
	}	
	@Test(expected=NoSuchElementException.class)
	public void next_1要素() {
		DelayIterator<Integer> it = new DelayIterator<>(lst(1).iterator());
		assertThat(it.next(), is(1));
		it.next();
	}
	
	@Test
	public void test_3要素() {
		assertThat(new DelayIterator<>(lst(1, 2, 3).iterator()), isIterator(1, 2, 3));
	}
}
