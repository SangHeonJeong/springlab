package shjeong.springlab.applicationcontext.staticac;

public class StringPrinter implements Printer {
    
    private StringBuffer buffer = new StringBuffer();
    
    @Override
    public void print(String message) {
        this.buffer.append(message);
    }

    public String toStrilng() {
        return this.buffer.toString();
    }
}