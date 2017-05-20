package refactoring.testing;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.io.InputStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class FileReaderTest {

    private InputStream input;

    @Before
    public void setUp() {
        input = loadFixtureFile();
    }

    @Test
    public void testRead() throws IOException {
        char ch = '&';

        for (int i = 0; i < 4; i++) {
            ch = (char) input.read();
        }

        assertEquals('d', ch);
    }

    @Test(expected = IOException.class)
    public void testReadWhenStreamClosed() throws IOException {
        input.close();
        input.read();
    }

    @Test
    public void testReadAtEnd() throws IOException {
        int ch = -1234;

        for (int i = 0; i < 298; i++) {
            ch = input.read();
        }

        assertEquals("read at end", -1, input.read());
    }

    @Test
    public void testReadBoundaries() throws IOException {
        assertEquals("read first char", 'B', input.read());

        int ch;

        for (int i = 0; i < 296; i++) {
            ch = input.read();
        }

        assertEquals("read last char", '6', input.read());
        assertEquals("read at end", -1, input.read());
    }

    @After
    public void tearDown() throws IOException {
        input.close();
    }

    private InputStream loadFixtureFile() {
        return getClass().getClassLoader().getResourceAsStream("data.txt");
    }
}
