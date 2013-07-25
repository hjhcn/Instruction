package instruction.util;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;

public class Test {

	public static void main(String[] args) throws UnsupportedEncodingException, ParseException {
		// String str = "\u521b\u5efa\u81ea\u52a8\u6d3b\u52a8";
		// str = new String(str.getBytes("Unicode"), "UTF-16");
		// System.out.println(str);
		//
		// str = "activity";
		// System.out.println(Test.toUNICODE(str));

		// String title = " UT斯达康UTS 702-U中文手机使用说明书下载 ";
		// title = title.replaceAll("\\s+", "");
		// Pattern pattern = Pattern.compile("[a-zA-Z0-9-/]+");
		// Matcher matcher = pattern.matcher(title);
		// int modelstart = 0;
		// while (matcher.find()) {
		// modelstart = matcher.start();
		// }
		// if (modelstart > 0)
		// System.out.println(title.substring(0, modelstart));
		//
		// String test =
		// "S19说明书.part1|uploadfile/2011/0906/20110906083735439.r";
		// String[] xs = test.split("\n");
		// for (String x : xs) {
		// String[] x2 = x.split("\\|");
		// for (String x1 : x2) {
		// System.out.println(x1);
		// }
		// }

		// String test = "uploadfile/2012/0424/20120424094555718.png";
		// System.out.println(test.substring(11));

		// SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd");
		// String dateString = "2011/07/29";
		// Date date = df.parse(dateString);
		// long today = date.getTime();
		//
		// System.out.println(today);
		// int time = Time.getTimeStamp();
		// System.out.println(time);
		// System.out.println(Time.formatTimeStamp(time,
		// "yyyy/MM/dd HH:mm:ss"));
		//
		// System.out.println(System.currentTimeMillis());

		// String x = "123456.8";
		// String y = x.substring(x.lastIndexOf("."),8);
		// System.out.println(y);
		//
		// File dir = new File("F:/uploadfiletemp/hjhx");
		// if (dir.isDirectory()) {
		// String[] children = dir.list();
		// for (int i = 0; i < children.length; i++) {
		// System.out.println(children[i]);
		// }
		// }

		// String xx = "鎴愬姛!";
		// xx = new String(xx.getBytes(), "GBK");
		// System.out.println(xx);
		//
		// Date date = new Date();
		// SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-01");
		// String sDateSuffix = dateformat.format(date);
		// try {
		// date = dateformat.parse(sDateSuffix);
		// } catch (ParseException e) {
		// e.printStackTrace();
		// }
		// System.out.println(Time.formatTimeStamp(System.currentTimeMillis(),
		// "yyyyMMddHHmmssSSS"));

//		SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		Date date1 = dateformat.parse("1927-12-31 23:54:07");
//		Date date2 = dateformat.parse("1927-12-31 23:54:08");
//		System.out.println(date2.getTime() / 1000 - date1.getTime() / 1000);

		
		
		File file=new File("E:/test.jpg");
		if(file.exists()){
			System.out.println("exists");
		}else {
			System.out.println("not exists");
			
		}
		
	}

	public static String toUNICODE(String s) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) <= 256) {
				sb.append("\\u00");
			} else {
				sb.append("\\u");
			}
			sb.append(Integer.toHexString(s.charAt(i)));
		}
		return sb.toString();
	}

}
