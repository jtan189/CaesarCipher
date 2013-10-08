package caesarcipher;

/**
 *
 * @author jdot
 */
public class Cipher {

    public static final int ALPHABET_SIZE = 26;
    public static final int ALPHABET_OFFSET = 97;
    private int shift;

    public Cipher(int shift) {
        this.shift = shift;
    }

    public Cipher() {
        this(13); // default shift is 13 characters
    }

    public int getShift() {
        return shift;
    }

    public void setShift(int shift) {
        this.shift = shift;
    }
    
    

    /**
     *
     * @param plaintext
     * @return ciphertext
     */
    public String encipher(String plaintext) {

        // convert all characters to lower case
        plaintext = plaintext.toLowerCase();

        String[] plainArray = plaintext.split("\\s+");
        String[] cipherArray = new String[plainArray.length];

        for (int i = 0; i < plainArray.length; i++) {
            String word = plainArray[i];
            StringBuilder wordBuilder = new StringBuilder();
            for (int j = 0; j < word.length(); j++) {

                int charAsInt = (int) word.charAt(j);

                if (charAsInt > 96 && charAsInt < 123) {
                    int actualShift = ((charAsInt - ALPHABET_OFFSET) + shift) % ALPHABET_SIZE;
                    wordBuilder.append((char) (actualShift + ALPHABET_OFFSET));
                } else {
                    wordBuilder.append(word.charAt(j));
                }
            }

            cipherArray[i] = wordBuilder.toString();
        }

        return join(cipherArray);
    }

    private String join(String[] wordArray) {
        StringBuilder gluer = new StringBuilder();
        for (int i = 0; i < wordArray.length; i++) {
            if (i < wordArray.length - 1) {
                gluer.append(wordArray[i]).append(' ');
            } else {
                gluer.append(wordArray[i]);
            }
        }
        return gluer.toString();
    }
}
