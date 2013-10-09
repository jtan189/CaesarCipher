/*
 * CSCI 469
 * Project #1
 * Tan, Josh
 * 10/08/13
 */

package caesarcipher;

/**
 * A Caesar cipher with an adjustable shift size. The implementation of this
 * cipher is simple nature. Only letters are shifted and whitespace is
 * retained through the encryption process (though multiple consecutive 
 * whitespace characters are merged into a single space in the ciphertext).
 * In addition, punctuation and numbers are not shifted.
 * 
 * @author Josh Tan
 */
public class Cipher {

    public static final int ALPHABET_SIZE = 26;
    public static final int ALPHABET_OFFSET = 97; // 97 = ASCII 'a'
    private int shift; // shift size for the cipher

    /**
     * Constructor specifying the shift size.
     * @param shift the shift size of the cypher
     */
    public Cipher(int shift) {
        this.shift = shift;
    }

    /**
     * Default constructor. The default shift size is 13 characters.
     */
    public Cipher() {
        this(13); // default shift is 13
    }

    /**
     * Return the shift size for the cipher.
     * @return the shift size
     */
    public int getShift() {
        return shift;
    }

    /**
     * Set the shift size for the cipher.
     * @param shift the shift size
     */
    public void setShift(int shift) {
        this.shift = shift;
    }
    
    /**
     * Encrypt the given plaintext according to this Caesar cipher.
     * @param plaintext the plaintext to be encrypted
     * @return ciphertext the resulting ciphertext
     */
    public String encipher(String plaintext) {

        // convert all characters to lower case
        plaintext = plaintext.toLowerCase();

        // split the plaintext into an String array, based on whitespace
        String[] plainArray = plaintext.split("\\s+");
        String[] cipherArray = new String[plainArray.length];

        // for each word in the plaintext
        for (int i = 0; i < plainArray.length; i++) {
            
            String word = plainArray[i];
            StringBuilder wordBuilder = new StringBuilder();
            
            // for each character in the word
            for (int j = 0; j < word.length(); j++) {

                // obtain char as an int
                int charAsInt = (int) word.charAt(j);

                // if char is in a-z
                if (charAsInt > 96 && charAsInt < 123) {
                    // shift the character, wrapping around the alphabet, if necessary
                    int actualShift = ((charAsInt - ALPHABET_OFFSET) + shift) % ALPHABET_SIZE;
                    wordBuilder.append((char) (actualShift + ALPHABET_OFFSET));
                } else {
                    // char is not a letter; do not shift
                    wordBuilder.append(word.charAt(j));
                }
            }

            cipherArray[i] = wordBuilder.toString();
        }

        // join the ciphertext words by spaces and return the resulting ciphertext string
        return join(cipherArray);
    }

    /**
     * Helper method to join an array of strings, using a space as the separator.
     * @param wordArray the array of strings to join
     * @return the joined string
     */
    private String join(String[] wordArray) {
        StringBuilder gluer = new StringBuilder();
        // for each word in the string array
        for (int i = 0; i < wordArray.length; i++) {
            // if not the last word
            if (i < wordArray.length - 1) {
                // append to output, followed by a space
                gluer.append(wordArray[i]).append(' ');
            } else {
                // word is last in the array; simply append
                gluer.append(wordArray[i]);
            }
        }
        return gluer.toString();
    }
}
