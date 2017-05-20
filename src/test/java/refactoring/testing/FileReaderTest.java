package refactoring.testing;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.io.InputStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

@SuppressWarnings("ResultOfMethodCallIgnored")
public class FileReaderTest {

    private InputStream input;
    private InputStream empty;

    @Before
    public void setUp() {
        input = loadFixtureFile("data.txt");
        empty = loadFixtureFile("empty.txt");
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
        for (int i = 0; i < 298; i++) {
            input.read();
        }

        assertEquals("read at end", -1, input.read());
    }

    @Test
    public void testReadBoundaries() throws IOException {
        assertEquals("read first char", 'B', input.read());

        for (int i = 0; i < 296; i++) {
            input.read();
        }

        assertEquals("read last char", '6', input.read());
        assertEquals("read at end", -1, input.read());
    }

    @Test
    public void testEmptyRead() throws IOException {
        assertEquals(-1, empty.read());
    }

    @After
    public void tearDown() throws IOException {
        input.close();
    }

    private InputStream loadFixtureFile(String fileName) {
        return getClass().getClassLoader().getResourceAsStream(fileName);
    }
}
