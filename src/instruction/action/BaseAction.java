package instruction.action;

import instruction.SystemConstants;
import instruction.model.TimeStampRange;
import instruction.rules.EasyUiDataGrid;
import instruction.rules.FeedbackRule;

import com.opensymphony.xwork2.ActionSupport;

public class BaseAction extends ActionSupport implements FeedbackRule, EasyUiDataGrid {
	private static final long serialVersionUID = 4186341131437455986L;

	public int feedback = SystemConstants.FEEDBACK.SUCCESS;

	public int page = 1;
	public int rows = 10;
	public String sort = "id";
	public String order = "desc";

	public TimeStampRange tsRange = new TimeStampRange();

	public int getFeedback() {
		return this.feedback;
	}

	public void setFeedback(int feedback) {
		this.feedback = feedback;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public void setTsRange(TimeStampRange tsRange) {
		this.tsRange = tsRange;
	}

	public TimeStampRange getTsRange() {
		return tsRange;
	}

}
