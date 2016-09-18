
public class NumberDivideException extends Exception {


	private static final long serialVersionUID = -1049317663306637382L;

	public NumberDivideException() {
        super("Illegal complex number operation!");
    }

    public NumberDivideException(String s) {
        super(s);
    }
} // end NumberDivideException
