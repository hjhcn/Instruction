package instruction.model;

public class InsOrderBy {
	private static String[] orderby = { "id", "uploadTime", "updateTime",
			"viewCount", "downloadCount", "commentCount", "commentGrade",
			"brand.id", "category.id", "user.id", "status", "title" };

	public static boolean include(String s) {
		for (int i = 0; i < orderby.length; i++) {
			if (orderby[i].indexOf(s) != -1) {
				return true;
			}
		}
		return false;
	}
}
