package src.test;

import java.util.ArrayList;
import java.util.List;

public class LamdaTest {
	public static void main(String[] args) {
		List<String> list = new ArrayList<String>();
		list.add("1");
		list.add("1");
		list.add("1");
		list.add("1");
		list.add("1");
		list.forEach(str -> System.out.println(str));
		list.stream().distinct();
		System.out.println(list);

	}

}
