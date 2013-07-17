package instruction.service;

import instruction.SystemConstants;
import instruction.SystemConstants.OPERATE;
import instruction.model.News;
import instruction.util.page.PageView;

import java.util.List;

public interface NewsService {
	public News get(int id, Short status);

	public List<News> findTopData(int count);

	public PageView<News> findScrollData(int page, int rows,
			String order, String sort);

	public PageView<News> findScrollData(int page, int rows,
			String order, String sort, Short status);

	public PageView<News> findScrollData(String search, int page,
			int rows, String order, String sort, Short status);

	public int operate(int uid, News news, SystemConstants.OPERATE operate);

	public int operate(String ids, OPERATE operate);
}
