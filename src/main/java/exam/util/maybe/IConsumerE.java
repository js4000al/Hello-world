package exam.util.maybe;

public interface IConsumerE<T, X extends Exception> {
	void accept(T v) throws X;
}
