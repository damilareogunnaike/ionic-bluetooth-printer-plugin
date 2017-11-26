package raywox.com.lahray.printer;

/**
 * Created by Lahray on 10/8/2017.
 */

public class PrinterException extends RuntimeException {

    private String message;

    public PrinterException(String message){
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
