public class Hello {

	public void hello()  {
		System.out.println("Hello, classLoader!");
		int i = 1;
		long l = 2L;
		boolean b = true;
		String[] sa = new String[]{"a", "b", "c"};
		if (b) {
			i++;//43
			++i;//46
		}
		for(int j = 0; j < sa.length; ++j) {//49  71
			System.out.println(sa[j]);//60
		}
		for(String s: sa) {//77 //111
			System.out.println(s);//103
		}
	}

}