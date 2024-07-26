package exercise;

// BEGIN
public class ReversedSequence implements CharSequence {
    private final String reversed;

    public ReversedSequence(String s) {
        reversed = new StringBuilder(s).reverse().toString();
    }

    @Override
    public int length() {
        return reversed.length();
    }

    @Override
    public char charAt(int index) {
        if (index < 0 || index >= reversed.length()) {
            throw new IndexOutOfBoundsException(index + " is negative or not less than length");
        }
        return reversed.charAt(index);
    }

    @Override
    public CharSequence subSequence(int start, int end) {
        if (start < 0 || end < 0) {
            throw new IndexOutOfBoundsException(start + " or " + end + " are negative");
        } else if (start > end) {
            throw new IndexOutOfBoundsException(start + " is greater than " + end);
        } else if (end >= reversed.length()) {
            throw new IndexOutOfBoundsException(end + " is greater than length");
        }
        return reversed.subSequence(start, end);
    }

    @Override
    public String toString() {
        return reversed.toString();
    }
}
// END
