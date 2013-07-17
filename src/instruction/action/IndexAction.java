package instruction.action;

import instruction.SystemConstants;
import instruction.model.BBSThread;
import instruction.model.Category;
import instruction.model.Daren;
import instruction.model.Instruction;
import instruction.model.Navigation;
import instruction.model.News;
import instruction.service.BBSThreadService;
import instruction.service.CategoryService;
import instruction.service.InsService;
import instruction.service.NaviService;
import instruction.service.NewsService;
import instruction.util.Time;

import java.util.List;

public class IndexAction extends SessionBaseAction {

	private static final long serialVersionUID = 8857338739347049656L;
	private static List<Instruction> newInses;
	private static List<Instruction> hotInses;
	private static int freshTime = 0;
	private InsService insService;
	private CategoryService categoryService;
	private NaviService naviService;
	private BBSThreadService bbsThreadService;
	private NewsService newsService;
	private List<Category> cates;
	private List<BBSThread> threads;
	private List<Daren> darens = SystemConstants.darens;
	private List<Navigation> navis;
	private List<News> newses;

	public String execute() {
		cates = categoryService.getTree(0);
		if (freshTime == 0 || Time.getTimeStamp() - freshTime > 300 || hotInses == null
				|| newInses == null) {
			freshTime = Time.getTimeStamp();
			hotInses = insService.findTopData(0, 0, 1, 15, null, "desc", "viewCount",
					SystemConstants.STATUS.PASS);
			newInses = insService.findTopNew(15);
		}
		navis = naviService.getAllTree();
		threads = bbsThreadService.findTop(10);
		newses = newsService.findTopData(10);
		return SUCCESS;
	}

	public void setNewsService(NewsService newsService) {
		this.newsService = newsService;
	}

	public void setInsService(InsService insService) {
		this.insService = insService;
	}

	public List<Instruction> getNewInses() {
		return newInses;
	}

	public List<Instruction> getHotInses() {
		return hotInses;
	}

	public void setThreads(List<BBSThread> threads) {
		this.threads = threads;
	}

	public List<BBSThread> getThreads() {
		return threads;
	}

	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	public List<Category> getCates() {
		return cates;
	}

	public void setDarens(List<Daren> darens) {
		this.darens = darens;
	}

	public List<Daren> getDarens() {
		return darens;
	}

	public void setNaviService(NaviService naviService) {
		this.naviService = naviService;
	}

	public NaviService getNaviService() {
		return naviService;
	}

	public void setNavis(List<Navigation> navis) {
		this.navis = navis;
	}

	public List<Navigation> getNavis() {
		return navis;
	}

	public void setBbsThreadService(BBSThreadService bbsThreadService) {
		this.bbsThreadService = bbsThreadService;
	}

	public BBSThreadService getBbsThreadService() {
		return bbsThreadService;
	}

	public void setNewses(List<News> newses) {
		this.newses = newses;
	}

	public List<News> getNewses() {
		return newses;
	}

}
