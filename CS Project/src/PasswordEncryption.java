/**
 * Encrypts the password using a simple caesar cipher.
 * Not very secure, but as long as someone doesn't brute force the
 * shift, it is good enough.
 *
 * TODO: If wanted, we can do a key system to "unlock" the
 *  encoding and decoding with a secret word. Each time a
 *  function is ran, it requests the key in the arguments
 *  of the function.
 *
 * @author Jaden Baker
 * @version 11/21/20
 */
public class PasswordEncryption {
    public static String encode(String input, int skip) {
        char[] letters = input.toCharArray();
        StringBuilder output = new StringBuilder();
        for (char a : letters) {
            int i;
            if (a == ' ' || a == ',' || a == '.' || a == '/' || a == ';' || a == '"') {
                output.append(a);
            } else {
                if (a <= 90) {
                    if (a + skip > 90) {
                        i = 65 + (skip - (90 - a));
                    } else {
                        i = a + skip;
                    }
                } else {
                    if (a + skip > 122) {
                        i = 96 + (skip - (122 - a));
                    } else {
                        i = a + skip;
                    }
                }
                output.append((char) i);
            }
        }
        return output.toString();
    }

    public static String decode(String input, int skip) {
        char[] letters = input.toCharArray();

        StringBuilder output = new StringBuilder();
        for (char a : letters) {
            int i;
            if (a == ' ' || a == ',' || a == '.' || a == '/' || a == ';' ||
                    a == '"' || a == 39 || a == '0' || a == '1' || a == '2' ||
                    a == '3' || a == '4' || a == '5' || a == '6' || a == '7' ||
                    a == '8' || a == '9') {
                output.append(a);
            } else {
                if (a <= 90) {
                    if (a - skip < 65) {
                        i = 91 - (skip - (a - 66));
                    } else {
                        i = a - skip;
                    }
                } else {
                    if (a - skip < 97) {
                        i = 123 - (skip - (a - 97));
                    } else {
                        i = a - skip;
                    }
                }
                output.append((char) i);
            }
        }
        return output.toString();
    }
}