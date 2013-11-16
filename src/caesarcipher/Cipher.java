/*
 * CSCI 469
 * Project #2
 * Tan, Josh
 * 11/15/13
 */
package caesarcipher;

/**
 * A Caesar cipher with an adjustable shift size. Encryption and decryption are
 * supported. The range of shift sizes available correspond to the range
 * of ASCII characters (minus one, to ensure different output is produced
 * from the input). Encryption and decryption are symmetric: either function
 * can be used to create ciphertext, and the other function will be able to
 * create plaintext from that ciphertext.
 *
 * @author Josh Tan
 */
public class Cipher {

    public static final int ALPHABET_SIZE = 128; // number of ASCII characters
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
     * Encrypt the given plaintext.
     *
     * @param plaintext the plaintext to be encrypted
     * @return the resulting ciphertext
     */
    public String encipher(String plaintext) {
        
        return rotate(plaintext, shift);      
    }
    /**
     * Decrypt the given ciphertext.
     * @param ciphertext the ciphertext to be decrypted
     * @return the resulting plaintext
     */
    public String decipher(String ciphertext) {
        return rotate(ciphertext, shift * -1);
    }
    
    /**
     * Rotate the specified string in the positive or negative
     * direction, as specified. Used as a helper method for the 
     * encipher and decipher methods.
     * @param original the original string
     * @param directedShift the direction to shift the text in
     * @return the shifted text
     */
    public String rotate(String original, int directedShift){
        
        StringBuilder rotationBuilder = new StringBuilder();

        // for each character in the string
        for (int i = 0; i < original.length(); i++) {

            // obtain char as an int
            int charAsInt = (int) original.charAt(i);

            // if char is ASCII
            if (charAsInt >= 0 && charAsInt <= (ALPHABET_SIZE - 1)) {
                
                // shift the character, wrapping around the alphabet, if necessary
                int actualShift;
                if (directedShift < 0) {
                    
                    // character should be shifted in the negative direction:
                    // map the character into its reflected positive value,
                    // shift it forward by the shift size, and remap it back
                    // into to its original (negative) space
                    int mappedOriginal = ALPHABET_SIZE - charAsInt;
                    int mappedRotated = (mappedOriginal + Math.abs(directedShift)) % ALPHABET_SIZE;
                    int rotated = ALPHABET_SIZE - mappedRotated;
                    actualShift = rotated;
                    
                } else {
                    // shift the character forward by the shift size, wrapping
                    // if necessary
                    actualShift = (charAsInt + directedShift) % ALPHABET_SIZE;
                }
                
                // append the shifted character to the output
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
