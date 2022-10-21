package toguapoaclient;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author vdiazbus
 */
public class EncrypterTest {

    public EncrypterTest() {
    }

    @Test
    public void testIsPrime() {
        // Given
        int n1 = 3, n2 = 6;
        Encrypter encrypter = new Encrypter();

        // Then
        boolean realResult1 = Encrypter.isPrime(n1);
        boolean realResult2 = Encrypter.isPrime(n2);

        // Expect
        assertEquals(true, realResult1);
        assertEquals(false, realResult2);
    }

    @Test
    public void encrypt() {
        // Given
        String word = "abcd";
        Encrypter encrypter = new Encrypter();

        // Then
        String expected = encrypter.encrypt(word, encrypter.getRule());

        // Expect
        assertEquals("1 2 3 5", expected);

    }

    @Test
    public void inverseEncrypt() {
        // Given
        String word = "1 2 3 5";
        Encrypter encrypter = new Encrypter();

        // Then
        String expected = encrypter.inverseEncrypt(word, encrypter.getInverserule());

        // Expect

        assertEquals("abcd", expected);

    }
}
