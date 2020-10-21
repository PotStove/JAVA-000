import java.io.IOException;
import java.lang.reflect.Method;
import java.nio.file.Files; 
import java.nio.file.Paths; 

public class MyClassLoader extends ClassLoader{
	
	public static void main(String[] args) {
		try {
			MyClassLoader loader = new MyClassLoader();
			Class c = loader.findClass("Hello");
			Method m = c.getDeclaredMethod("hello");
			m.invoke(c.getDeclaredConstructor().newInstance());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
    protected Class<?> findClass(String name) {
		try {
			byte[] fin = Files.readAllBytes(Paths.get("D:/study/极客Java/0_homework/Week_01/Hello.xlass"));
			// byte [127, -128]，最高位1负0正，负数值为取反加1
			byte allOne = -1;
			for (int i=0; i<fin.length; i++) {
				fin[i] = (byte)(allOne - fin[i]);
			}
			// javap test success
			Files.write(Paths.get("D:/study/极客Java/0_homework/Week_01/MyLoadedHello.class"), fin);
	
			return defineClass(name, fin, 0, fin.length);	
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}