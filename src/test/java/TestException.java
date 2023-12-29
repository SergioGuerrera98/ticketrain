import com.corso.ticketrain.service.exceptions.PasswordCaratteriMancantiException;
import com.corso.ticketrain.service.exceptions.PasswordCaratteriNonAccettatiException;
import com.corso.ticketrain.service.exceptions.PasswordException;
import com.corso.ticketrain.treno.exceptions.LocomotivaInCentroException;
import com.corso.ticketrain.treno.exceptions.PesoEccessivoException;
import com.corso.ticketrain.treno.exceptions.RistoranteNonAlCentroException;
import com.corso.ticketrain.treno.exceptions.TrenoException;
import org.junit.Test;
import static org.junit.Assert.*;

public class TestException {

    @Test
    public void ristoranteAlCentroTest() {
        TrenoException te = new RistoranteNonAlCentroException("HRCCCCH", true);
        assertEquals(te.getSuggerimento(), "Riposiziona il tuo ristorante : HCCRCCH");
    }

    @Test
    public void pesoEccessivoTest() {
        TrenoException te = new PesoEccessivoException("HRCCCCH", 4);
        assertEquals(te.getSuggerimento(), "Devi rimuovere dei vagoni; il peso accettabile : HRCC");
    }

    @Test
    public void locomotiveAlCentroTest_HT() {
        TrenoException te = new LocomotivaInCentroException("HRCCHHCCH", true);
        assertEquals(te.getSuggerimento(), "Rimuovi le locomotive centrali : HRCCCCH");
    }

    @Test
    public void locomotiveAlCentroTest_H() {
        TrenoException te = new LocomotivaInCentroException("HRCCHHCC", true);
        assertEquals(te.getSuggerimento(), "Rimuovi le locomotive centrali : HRCCCC");
    }

    @Test
    public void passwordMancaAZTest() { 
        PasswordException te = new PasswordCaratteriMancantiException("password_123");
        assertEquals(te.getSuggerimento(), "Nella password devi inserire almeno : Maiuscola");
    }

    @Test
    public void passwordMancaazTest() {
        PasswordException te = new PasswordCaratteriMancantiException("PASSWORD_123");
        assertEquals(te.getSuggerimento(), "Nella password devi inserire almeno : Minuscola");
    }

    @Test
    public void passwordManca09Test() {
        PasswordException te = new PasswordCaratteriMancantiException("Password_");
        assertEquals(te.getSuggerimento(), "Nella password devi inserire almeno : Numero");
    }

    @Test
    public void passwordMancaSpecialTest() {
        PasswordException te = new PasswordCaratteriMancantiException("Password123");
        System.out.println(te.getSuggerimento());
        assertEquals(te.getSuggerimento(), "Nella password devi inserire almeno : Speciale");
    }

    @Test
    public void passwordMancaAZspecTest() {
        PasswordException te = new PasswordCaratteriMancantiException("password123");
        System.out.println(te.getSuggerimento());
        assertEquals(te.getSuggerimento(), "Nella password devi inserire almeno : Maiuscola, Speciale");
    }
    
    @Test
    public void passwordMancaTuttoTest() {
        PasswordException te = new PasswordCaratteriMancantiException("----");
        System.out.println(te.getSuggerimento());
        assertEquals(te.getSuggerimento(), "Nella password devi inserire almeno : Maiuscola, Minuscola, Numero, Speciale");
    }
    
    @Test
    public void passwordCharNonAccettatiTest() {
        PasswordException te = new PasswordCaratteriNonAccettatiException("Pas*_(swor;-çd_123");
        System.out.println(te.getSuggerimento());
        assertEquals(te.getSuggerimento(), "I seguenti caratteri non sono accettati : (;-ç");
    }
}
