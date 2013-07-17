package instruction.util;

public class PageUtils {
	public static int getStartPage(int page, int count) {
		if (page <= 8)
			return 1;
		else {
			if (count - page < 7 && count > 15)
				return count - 14;
			else
				return page - 7;
		}
	}

	public static int getEndPage(int page, int count) {
		if (count < 16)
			return count;
		if (count - page < 7)
			return count;
		else if (page <= 8)
			return 15;
		else
			return page + 7;
	}

	public static int getFromIndex(int page, int size) {
		if (page <= 0)
			page = 1;
		return size * (page - 1);
	}

	public static int getToIndex(int page, int size) {
		if (page <= 0)
			page = 1;
		return size * (page - 1) + size;
	}

	public static int countToPage(int count, int size) {
		int page = 0;
		page = count / size;
		if (count % size != 0)
			page += 1;
		return page;
	}
}
