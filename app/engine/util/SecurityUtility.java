package engine.util;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 * Stolen from https://www.owasp.org/index.php/Hashing_Java on 12/27/2014.
 */
public class SecurityUtility {
    private static final int ITERATION_NUMBER = 1337;

    private SecurityUtility(){
        //no instantiating allowed!
    }

    /**
     * From a password, a number of iterations and a generated salt,
     * returns the corresponding digest and salt
     * @param password String The password to encrypt
     * @return String[], {The digested password, salt}
     * @throws NoSuchAlgorithmException If the algorithm doesn't exist
     * @throws IOException if it fails to convert
     */
    public static String[] getHashPair (String password) throws
            NoSuchAlgorithmException,IOException {
        byte[] salt = generateSalt();
        byte[] input = calculateHash(password, salt);
        String [] result = {byteToBase64(input), byteToBase64(salt)};
        return result;
    }

    /**
     * From a password, a number of iterations and a provided salt,
     * returns the corresponding digest
     * @param password String The password to encrypt
     * @return String, The digested password
     * @throws NoSuchAlgorithmException If the algorithm doesn't exist
     * @throws IOException if it fails to convert
     */
    public static String getHash (String password, String bsalt) throws
            NoSuchAlgorithmException,IOException {
        byte[] salt = base64ToByte(bsalt);
        byte[] input = calculateHash(password, salt);
        return byteToBase64(input);
    }


    /**
     * Helper method that calculates the SHA-512 hash||salt
     * From a password, a number of iterations and a generated salt,
     * returns the corresponding digest and salt
     * @param password String The password to encrypt
     * @param salt byte[] The salt used in the hashing
     * @return byte[], the digested password
     * @throws NoSuchAlgorithmException If the algorithm doesn't exist
     * @throws IOException if it fails to convert
     */
    private static byte[] calculateHash(String password, byte[] salt) throws
            NoSuchAlgorithmException,IOException {
        MessageDigest digest = MessageDigest.getInstance("SHA-512");
        digest.reset();
        digest.update(salt);
        byte[] input = digest.digest(password.getBytes("UTF-8"));
        for (int i = 0; i < ITERATION_NUMBER; i++) {
            digest.reset();
            input = digest.digest(input);
        }
        return input;
    }

    /**
     * Generates a secure random 64 bit long salt
     * @return byte[]
     * @throws NoSuchAlgorithmException
     */
    private static byte[] generateSalt() throws NoSuchAlgorithmException {
        SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
        // Salt generation 64 bits long
        byte[] bSalt = new byte[8];
        random.nextBytes(bSalt);
        return bSalt;
    }

    /**
     * From a base 64 representation, returns the corresponding byte[]
     * @param data String The base64 representation
     * @return byte[]
     * @throws IOException
     */
    public static byte[] base64ToByte(String data) throws IOException {
        BASE64Decoder decoder = new BASE64Decoder();
        return decoder.decodeBuffer(data);
    }

    /**
     * From a byte[] returns a base 64 representation
     * @param data byte[]
     * @return String
     * @throws IOException
     */
    public static String byteToBase64(byte[] data){
        BASE64Encoder endecoder = new BASE64Encoder();
        return endecoder.encode(data);
    }

}
