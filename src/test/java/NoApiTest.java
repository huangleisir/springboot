package src.test;

import com.hl.HttpClientUtil;

public class NoApiTest {
	public static void main(String[] args) {
		String str = HttpClientUtil.doGet(
				"http://api.k780.com/?app=weather.today&weaid=1&appkey=37513&sign=03ece6e534315f18bb18cca05465217f&format=json",
				null);
		System.out.println(str);
	}

}
