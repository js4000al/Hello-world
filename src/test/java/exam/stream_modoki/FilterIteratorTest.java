package exam.stream_modoki;

import static org.junit.Assert.*;
import static test.UT.*;

import org.junit.Test;

import exam.stream_modoki.FilterIterator;

public class FilterIteratorTest {

	@Test
	public void 要素0() {
		assertThat(new FilterIterator<>(it(), v -> false), isIterator());
		assertThat(new FilterIterator<>(it(), v -> true),  isIterator());
	}
	
	@Test
	public void 要素1(){
		assertThat(new FilterIterator<>(it(1), v -> false), isIterator());
		assertThat(new FilterIterator<>(it(1), v -> true),  isIterator(1));
		assertThat(new FilterIterator<>(it(1), v -> v > 1),  isIterator());
		assertThat(new FilterIterator<>(it(1), v -> v > 0),  isIterator(1));
	}

	@Test
	public void 要素3(){
		assertThat(new FilterIterator<>(it(1, 2, 3), v -> false), isIterator());
		assertThat(new FilterIterator<>(it(1, 2, 3), v -> true),  isIterator(1, 2, 3));
		assertThat(new FilterIterator<>(it(1, 2, 3), v -> v > 3),  isIterator());
		assertThat(new FilterIterator<>(it(1, 2, 3), v -> v > 2),  isIterator(3));
		assertThat(new FilterIterator<>(it(1, 2, 3), v -> v > 1),  isIterator(2, 3));
		assertThat(new FilterIterator<>(it(1, 2, 3), v -> v > 0),  isIterator(1, 2, 3));
		
		assertThat(new FilterIterator<>(it(3, 2, 1), v -> v > 3),  isIterator());
		assertThat(new FilterIterator<>(it(3, 2, 1), v -> v > 2),  isIterator(3));
		assertThat(new FilterIterator<>(it(3, 2, 1), v -> v > 1),  isIterator(3, 2));
		assertThat(new FilterIterator<>(it(3, 2, 1), v -> v > 0),  isIterator(3, 2, 1));
		
		assertThat(new FilterIterator<>(it(1, 2, 0), v -> v > 1),  isIterator(2));
		assertThat(new FilterIterator<>(it(2, 1, 3), v -> v > 1),  isIterator(2, 3));
		
		assertThat(new FilterIterator<>(it(2, 1, 0, 3), v -> v > 1),  isIterator(2, 3));
		assertThat(new FilterIterator<>(it(2, 1, 0, 3, 1), v -> v > 1),  isIterator(2, 3));
	}
}
