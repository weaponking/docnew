~~~
import static org.junit.Assert.assertEquals;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Locale;
import java.util.Properties;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;
import org.junit.Test;

public class TestCase {

	@Test
	public void test() {
		try {
			String file = "/home/weapon/current/test.properties";

			InputStream in = new BufferedInputStream(new FileInputStream(file));
			Properties p = new Properties();
			p.load(in);
			assertEquals(p.getProperty("jdbc.url"), "mysql");

			ResourceBundle rb = ResourceBundle.getBundle("test", Locale.getDefault());
			assertEquals(rb.getString("jdbc.pwd"), "MTIzNDU2");

			ResourceBundle rb1 = new PropertyResourceBundle(new BufferedInputStream(new FileInputStream(file)));
			assertEquals(rb1.getString("jdbc.pwd"), "MTIzNDU2");

			InputStream in1 = Properties.class.getResourceAsStream("/test1.properties");
			Properties p1 = new Properties();
			p1.load(in1);
			assertEquals(p1.getProperty("jdbc.url"), "mysql1");

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		System.out.println(TestCase.class.getClassLoader()==null);
		System.out.println(Properties.class.getClassLoader()==null);
	}
}
~~~
