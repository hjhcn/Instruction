package instruction.util;

import instruction.dao.impl.PhpcmsDaoImpl;
import instruction.model.PhpcmsContent;

import java.util.List;

public class DataShift {

	public static void main(String s[]) {
		PhpcmsDaoImpl pcDao = new PhpcmsDaoImpl();
		List<PhpcmsContent> phpcmsContents = pcDao.getAllNewContent();
		for (PhpcmsContent pc : phpcmsContents) {
			System.out.println(pc.getTitle());
		}
	}
}
