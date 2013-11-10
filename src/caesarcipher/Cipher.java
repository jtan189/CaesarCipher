/*
 * CSCI 469
 * Project #1
 * Tan, Josh
 * 10/08/13
 */
package caesarcipher;

// add option to make all lower case for more secrecy

/**
 * A Caesar cipher with an adjustable shift size. The implementation of this
 * cipher is simple nature. Only letters are shifted and whitespace is retained
 * through the encryption process (though multiple consecutive whitespace
 * characters are merged into a single space in the ciphertext). In addition,
 * punctuation and numbers are not shifted.
 *
 * @author Josh Tan
 */
public class Cipher {

    public static final int ALPHABET_SIZE = 128;
//    public static final int ALPHABET_OFFSET = 97; // 97 = ASCII 'a'
    private int shift; // shift size for the cipher

    /**
     * Constructor specifying the shift size.
     *
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
     *
     * @return the shift size
     */
    public int getShift() {
        return shift;
    }

    /**
     * Set the shift size for the cipher.
     *
     * @param shift the shift size
     */
    public void setShift(int shift) {
        this.shift = shift;
    }

    /**
     * Encrypt the given plaintext according to this Caesar cipher.
     *
     * @param plaintext the plaintext to be encrypted
     * @return ciphertext the resulting ciphertext
     */
    public String encipher(String plaintext) {
        
        return rotate(plaintext, shift);      
    }
    
    public String decipher(String plaintext) {
        return rotate(plaintext, shift * -1);
    }
    
    public String rotate(String original, int directedShift){
        

        StringBuilder rotationBuilder = new StringBuilder();

        for (int i = 0; i < original.length(); i++) {

            // obtain char as an int
            int charAsInt = (int) original.charAt(i);

            // if char is ASCII
            if (charAsInt >= 0 && charAsInt <= (ALPHABET_SIZE - 1)) {
                // shift the character, wrapping around the alphabet, if necessary
                
                int actualShift;
                if (directedShift < 0) {
                    // neg - decrypt
                    int mappedOriginal = ALPHABET_SIZE - charAsInt;
                    int mappedRotated = (mappedOriginal + Math.abs(directedShift)) % ALPHABET_SIZE;
                    int rotated = ALPHABET_SIZE - mappedRotated;
                    actualShift = rotated;
                    
                } else {
                    // pos - encrypt
                    actualShift = (charAsInt + directedShift) % ALPHABET_SIZE;
                }
                
                
                rotationBuilder.append((char) (actualShift));
            } else {
                // char is not ASCII; do not shift
                rotationBuilder.append((char) charAsInt);
            }
        }

        // return the resulting ciphertext string
        return rotationBuilder.toString();
        
    }

}
