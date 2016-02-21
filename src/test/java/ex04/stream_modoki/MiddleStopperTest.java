package ex04.stream_modoki;

import static org.junit.Assert.*;
import static test.UT.*;

import org.junit.Test;

import ex04.stream_modoki.MiddleStopper;

public class MiddleStopperTest {

	//TODO Gradle標準構成にしようず
	
	@Test
	public void 要素0() {
		assertThat(new MiddleStopper<>(it(), v -> false), isIterator());
		assertThat(new MiddleStopper<>(it(), v -> true),  isIterator());
	}
	@Test
	public void 要素1(){
		assertThat(new MiddleStopper<>(it(1), v -> false), isIterator(1));
		assertThat(new MiddleStopper<>(it(1), v -> true),  isIterator());
		assertThat(new MiddleStopper<>(it(1), v -> v > 1),  isIterator(1));
		assertThat(new MiddleStopper<>(it(1), v -> v > 0),  isIterator());
	}
	@Test
	public void 要素3(){
		assertThat(new MiddleStopper<>(it(1, 2, 3), v -> false), isIterator(1, 2, 3));
		assertThat(new MiddleStopper<>(it(1, 2, 3), v -> true),  isIterator());
		assertThat(new MiddleStopper<>(it(1, 2, 3), v -> v > 3),  isIterator(1, 2, 3));
		assertThat(new MiddleStopper<>(it(1, 2, 3), v -> v > 2),  isIterator(1, 2));
		assertThat(new MiddleStopper<>(it(1, 2, 3), v -> v > 1),  isIterator(1));
		assertThat(new MiddleStopper<>(it(1, 2, 3), v -> v > 0),  isIterator());
		
		assertThat(new MiddleStopper<>(it(1, 2, 1), v -> v > 1),  isIterator(1));
	}

}
