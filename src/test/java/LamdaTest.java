import com.hl.util.Mail;

public class LamdaTest {
	/*
	 * public static void main(String[] args) { List<String> list = new
	 * ArrayList<String>(); list.add("1"); list.add("1"); list.add("1");
	 * list.add("1"); list.add("1"); list.forEach(str -> System.out.println(str));
	 * list.stream().distinct(); System.out.println(list);
	 * 
	 * 
	 * }
	 */

	public static void main(String[] args) {
		String smtp = "smtp.126.com";// smtp服务器
		String from = "huangleisir@126.com";// 邮件显示名称
		String to = "huanglein@yonyou.com";// 收件人的邮件地址，必须是真实地址
		String copyto = "";// 抄送人邮件地址
		String subject = "樊总，你好，请及时审批邮件";// 邮件标题
		String content = "<h1 style=\"color:red; \">本公司账单已发出，请审核， 你好！<h1>";// 邮件内容
		String username = "huangleisir@126.com";// 发件人真实的账户名
		String password = "13579Hl";// 发件人密码

		Mail.sendAndCc(smtp, from, to, copyto, subject, content, username, password);
	}

}
