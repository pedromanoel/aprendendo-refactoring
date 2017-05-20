package refactoring.testing;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.io.InputStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class FileReaderTest {

    private InputStream file;

    @Before
    public void setUp() {
        file = loadFixtureFile();
    }

    @Test
    public void testRead() throws IOException {
        char ch = '&';

        for (int i = 0; i < 4; i++) {
            ch = (char) file.read();
        }

        assertEquals('d', ch);
    }

    @Test(expected = IOException.class)
    public void testReadWhenStreamClosed() throws IOException {
        file.close();
        file.read();
    }

    @Test
    public void testReadAtEnd() throws IOException {
        int ch = -1234;

        for (int i = 0; i < 298; i++) {
            ch = file.read();
        }

        assertEquals("read at end", -1, file.read());
    }

    @After
    public void tearDown() throws IOException {
        file.close();
    }

    private InputStream loadFixtureFile() {
        return getClass().getClassLoader().getResourceAsStream("fixture.txt");
    }
}
