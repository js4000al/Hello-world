package ex04.stream_modoki;

import java.util.function.Predicate;

/**
 * ある回数だけ{@link #test(Object)}が呼ばれるまではtrue、それ以降は
 * falseを返す。
 */
public final class CountLimitter<T> implements Predicate<T>{
	private final int limit;
	private int i = 0;
	
	public CountLimitter(int limit){
		this.limit = limit;
	}
	
	@Override
	public boolean test(T v){
		// return (i++ < limit) だと無駄に加算するので
		
		if(i < limit){
			++i;
			return true;
		}
		else{
			return false;
		}
	}
}
