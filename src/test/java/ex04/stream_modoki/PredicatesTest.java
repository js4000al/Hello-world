package ex04.stream_modoki;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import java.util.function.Predicate;

import org.junit.Test;

import ex04.stream_modoki.Predicates;

public class PredicatesTest {

	@Test
	public void whileDo(){
		Predicate<? super Integer> p = Predicates.whileDo(t -> t > 0);
		assertThat(p.test(2),  is(true));
		assertThat(p.test(1),  is(true));
		assertThat(p.test(0),  is(false));
		assertThat(p.test(-1), is(false));
		assertThat(p.test(1),  is(false));
	}

	@Test
	public void until() {
		Predicate<? super Integer> p = Predicates.until(t -> t > 0);
		assertThat(p.test(-1), is(true));
		assertThat(p.test(0),  is(true));
		assertThat(p.test(1),  is(false));
		assertThat(p.test(0),  is(false));
	}

}
