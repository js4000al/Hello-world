package exam.util;

public final class Tuple {
	private Tuple(){}
	
	public static final class Cns<T1, T2>{
		public final T1 l;
		public final T2 r;
		
		private Cns(T1 left, T2 rest){
			this.l = left;
			this.r = rest;
		}
		public <T3> Cns<T1, Cns<T2, T3>> add(T3 v3){
			return new Cns<>(l, new Cns<>(r, v3));
		}
	}
	
	
	public static <T1, T2> Cns<T1, T2> cons(T1 v1, T2 v2){
		return new Cns<>(v1, v2);
	}
}
