package exam.util.maybe;


public interface IActionE<X extends Exception> {
	void run() throws X;
}
