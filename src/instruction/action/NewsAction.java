package instruction.action;

import java.util.List;

import instruction.SystemConstants;
import instruction.SystemConstants.STATUS;
import instruction.model.BBSThread;
import instruction.model.Category;
import instruction.model.News;
import instruction.service.BBSThreadService;
import instruction.service.CategoryService;
import instruction.service.NewsService;
import instruction.util.page.PageView;

public class NewsAction extends SessionBaseAction {

	private static final long serialVersionUID = 5510382828215401690L;
	private NewsService newsService;
	private PageView<News> newses;
	private CategoryService categoryService;
	private BBSThreadService bbsThreadService;
	private int id;
	private News news;
	private String search;
	private String ids;
	private SystemConstants.OPERATE operate;
	private List<BBSThread> threads;
	private List<Category> cates;

	public String list() {
		threads = bbsThreadService.findTop(10);
		cates = categoryService.getTree(0);
		newses = newsService.findScrollData(search, page, rows, order, sort, STATUS.PASS);
		return SUCCESS;
	}

	public String news() {
		threads = bbsThreadService.findTop(10);
		cates = categoryService.getTree(0);
		news = newsService.get(id, STATUS.PASS);
		return SUCCESS;
	}

	public String newsApp() {
		news = newsService.get(id, STATUS.PASS);
		return SUCCESS;
	}

	public NewsService getNewsService() {
		return newsService;
	}

	public void setNewsService(NewsService newsService) {
		this.newsService = newsService;
	}

	public PageView<News> getNewses() {
		return newses;
	}

	public void setNewses(PageView<News> newses) {
		this.newses = newses;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public News getNews() {
		return news;
	}

	public void setNews(News news) {
		this.news = news;
	}

	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public SystemConstants.OPERATE getOperate() {
		return operate;
	}

	public void setOperate(SystemConstants.OPERATE operate) {
		this.operate = operate;
	}

	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	public void setBbsThreadService(BBSThreadService bbsThreadService) {
		this.bbsThreadService = bbsThreadService;
	}

	public List<BBSThread> getThreads() {
		return threads;
	}

	public List<Category> getCates() {
		return cates;
	}

}
