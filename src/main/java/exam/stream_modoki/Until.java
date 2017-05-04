package exam.stream_modoki;

import java.util.function.Predicate;

/**
 * 条件を満たすまではtrue、満たした後はfalseを返す。
 * 
 * @param <T>
 */
public final class Until<T> implements Predicate<T>{
	private final Predicate<? super T> stopCond;
	private boolean notSatisfied = true;
	
	public Until(Predicate<? super T> cond){
		this.stopCond = cond;
	}

	@Override
	public boolean test(T t) {
		if(notSatisfied && stopCond.test(t)){
			notSatisfied = false;
		}
		return notSatisfied;
	}
	
}
