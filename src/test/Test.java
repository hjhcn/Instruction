package test;

import java.util.LinkedList;

import com.fivestars.interfaces.bbs.client.UCClient;
import com.fivestars.interfaces.bbs.util.XMLHelper;

/**
 * ================================================ Discuz! Ucenter API for JAVA
 * ================================================ 测试类 示例：本类实现在如何实现在登入/登出，以及注册。
 * 
 * 更多信息：http://code.google.com/p/discuz-ucenter-api-for-java/ 作者：梁平 (no_ten@163.com) 创建时间：2009-2-20
 */
public class Test {

	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {

		// testLogin();
		UCClient uc = new UCClient();
		//
		// String code =
		// "cb1aBRO/374kxLUkHkUC79Ed/ZpdUG1bt5/DmJQZgpIi0VsQ2r70pB82qjPgB+wGQl9lXLkaZmKUMC20eWVgiJ07tSUWkuYGomvAtnbVBbV2iBJEIGTwpo5Vcss7xrNQoRzjnCbFTCfEmva93/a0hZGUZja9fTr7ngSMZvUU0tK1rfNTaQV7cxI+lZ8roF3hhe7/78CL58JssH0cW2EqfDstaAZqIQ";
		// System.out.println("PHP加密密文:" + code);
		// // code= uc.uc_authcode(code, "DECODE");
		// Map<String, String> map = uc.decodeReturnMap(code);
		// System.out.println("解密:" + map);

		// String x=uc.uc_user_synlogin(56609);
		// System.out.println(x);

		// String result = uc.uc_credit_operatelfb("15105180884", 1302, 2,
		// "\"有奖上传\"活动第十名500来福币(来自管理员2062572@yujie操作)");
		// LinkedList<String> rs = XMLHelper.uc_unserialize(result);
		// System.out.println(rs);

		// String result = uc.uc_get_user("380050803@qq.com", 2);
		// LinkedList<String> rs = XMLHelper.uc_unserialize(result);
		// System.out.println(rs);

		// String result = uc.uc_credit_operatelfb("15105180884", 1302, 1, "测试(来自管理员1302@hjhx操作)");
		// LinkedList<String> rs = XMLHelper.uc_unserialize(result);
		// System.out.println(rs);

		uc.uc_get_user("1302", 1);
		// System.out.println(result);
		// LinkedList<String> rs = XMLHelper.uc_unserialize(result);
		// System.out.println("" + byte2hex(rs.get(1).getBytes()));

		//
		// String encoding = System.getProperty("file.encoding");
		// System.out.println("Default System Encoding:" + encoding);
		//
		// String oreason = "服务器测试(来自管理员1302@hjhx操作)";
		// oreason = URLEncoder.encode(oreason, "UTF-8");
		// System.out.println(oreason);
		// oreason = "服务器测试(来自管理员1302@hjhx操作)";
		// oreason = new String(oreason.getBytes(), "GBK");
		// System.out.println(oreason);
		//
		// oreason = "服务器测试(来自管理员1302@hjhx操作)";
		// oreason = new String(oreason.getBytes(encoding), "GBK");
		// System.out.println(oreason);
		// oreason = URLEncoder.encode(oreason, "UTF-8");
		// System.out.println(oreason);
		//
		// oreason =
		// "%EF%BF%BD%EF%BF%BD%EF%BF%BD%EF%BF%BD%EF%BF%BD%EF%BF%BD%EF%BF%BD%EF%BF%BD%EF%BF%BD%284%EF%BF%BD%D4%B9%EF%BF%BD%EF%BF%BD%EF%BF%BD%D4%B11302%40hjhx%EF%BF%BD%EF%BF";
		// oreason = URLDecoder.decode(oreason, "UTF-8");
		// System.out.println(oreason);
		// oreason = new String(oreason.getBytes("GBK"), "UTF-8");
		// System.out.println(oreason);

		// testLogin();

		// String x = "\\\"dsfsfsdf\"";
		// System.out.println(x);
		// x = x.replaceAll("\"", "\\\"");
		// System.out.println(x);

	}

	public static String byte2hex(byte[] b) {
		StringBuffer hs = new StringBuffer();
		String stmp = "";
		for (int n = 0; n < b.length; n++) {
			stmp = (java.lang.Integer.toHexString(b[n] & 0XFF));
			if (stmp.length() == 1)
				hs.append("0").append(stmp);
			else
				hs.append(stmp);
		}
		return hs.toString();
	}

	public static void testLogin() {

		UCClient e = new UCClient();
		String result = e.uc_user_login("380050803@qq.com", "122422", "");

		LinkedList<String> rs = XMLHelper.uc_unserialize(result);
		System.out.println(rs);
		if (rs.size() > 0) {
			int $uid = Integer.parseInt(rs.get(0));
			String $username = rs.get(1);
			String $password = rs.get(2);
			String $email = rs.get(3);
			if ($uid > 0) {
				System.out.println("登录成功");
				System.out.println($username);
				System.out.println($password);
				System.out.println($email);

				String $ucsynlogin = e.uc_user_synlogin($uid);
				System.out.println("登录成功" + $ucsynlogin);

			} else if ($uid == -1) {
				System.out.println("用户不存在,或者被删除");
			} else if ($uid == -2) {
				System.out.println("密码错");
			} else {
				System.out.println("未定义");
			}
		} else {
			System.out.println("Login failed");
			System.out.println(result);
		}
	}

	public static void testLogout() {

		UCClient uc = new UCClient();

		// setcookie('Example_auth', '', -86400);
		// 生成同步退出的代码
		String $ucsynlogout = uc.uc_user_synlogout();
		System.out.println("退出成功" + $ucsynlogout);

	}

	public static void testRegister() {

		UCClient uc = new UCClient();

		// setcookie('Example_auth', '', -86400);
		// 生成同步退出的代码
		String $returns = uc.uc_user_register("cccc", "ccccc", "ccc@abc.com");
		int $uid = Integer.parseInt($returns);
		if ($uid <= 0) {
			if ($uid == -1) {
				System.out.print("用户名不合法");
			} else if ($uid == -2) {
				System.out.print("包含要允许注册的词语");
			} else if ($uid == -3) {
				System.out.print("用户名已经存在");
			} else if ($uid == -4) {
				System.out.print("Email 格式有误");
			} else if ($uid == -5) {
				System.out.print("Email 不允许注册");
			} else if ($uid == -6) {
				System.out.print("该 Email 已经被注册");
			} else {
				System.out.print("未定义");
			}
		} else {
			System.out.println("OK:" + $returns);
		}

	}

}
