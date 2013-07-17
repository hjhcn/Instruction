package instruction.action.admin;

import instruction.SystemConstants;
import instruction.SystemConstants.OPERATE;
import instruction.SystemConstants.STATUS;
import instruction.model.News;
import instruction.service.NewsService;
import instruction.util.page.PageView;

public class NewsAdminAction extends BaseAdminAction {

	private static final long serialVersionUID = 1L;
	private NewsService newsService;
	private PageView<News> newses;
	private int id;
	private News news;
	private String search;
	private Short status = STATUS.UNVERIFY;
	private String ids;
	private SystemConstants.OPERATE operate;

	public String list() {
		newses = newsService.findScrollData(search, page, rows, order, sort, status);
		return SUCCESS;
	}

	public String add() {
		feedback = newsService.operate(admin.getUid(), news, OPERATE.ADMIN_UPLOAD);
		return SUCCESS;
	}

	public String edit() {
		feedback = newsService.operate(admin.getUid(), news, OPERATE.EDIT);
		return SUCCESS;
	}

	public String addPage() {
		return SUCCESS;
	}

	public String operate() {
		feedback = newsService.operate(ids, operate);
		return SUCCESS;
	}

	public String news() {
		news = newsService.get(id, status);
		return SUCCESS;
	}

	public int getFeedback() {
		return this.feedback;
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

	public Short getStatus() {
		return status;
	}

	public void setStatus(Short status) {
		this.status = status;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public void setOperate(SystemConstants.OPERATE operate) {
		this.operate = operate;
	}

	public SystemConstants.OPERATE getOperate() {
		return operate;
	}

}
