package instruction.action.admin;

import instruction.model.Comment;
import instruction.service.CommentService;
import instruction.util.page.PageView;

public class CommentAdminAction extends BaseAdminAction {

	private static final long serialVersionUID = 1979680168642525015L;
	private CommentService commentService;
	private int iid=0;// 说明书id
	private int id;// 评论id
	private String ids;// 批量操作id
	private int startTimeStamp = 0;
	private int endTimeStamp = 0;
	private int uid = 0;
	private Short creditStatus;
	private PageView<Comment> comments;

	public String list() {
		comments = commentService.findScrollData(uid, iid, page, rows, order, sort);
		return SUCCESS;
	}

	public String credit() {
		feedback = commentService.credit(ids, creditStatus);
		return SUCCESS;
	}

	public Short getCreditStatus() {
		return creditStatus;
	}

	public void setCreditStatus(Short creditStatus) {
		this.creditStatus = creditStatus;
	}

	public int getFeedback() {
		return this.feedback;
	}

	public int getIid() {
		return iid;
	}

	public void setIid(int iid) {
		this.iid = iid;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public int getStartTimeStamp() {
		return startTimeStamp;
	}

	public void setStartTimeStamp(int startTimeStamp) {
		this.startTimeStamp = startTimeStamp;
	}

	public int getEndTimeStamp() {
		return endTimeStamp;
	}

	public void setEndTimeStamp(int endTimeStamp) {
		this.endTimeStamp = endTimeStamp;
	}

	public void setCommentService(CommentService commentService) {
		this.commentService = commentService;
	}

	public CommentService getCommentService() {
		return commentService;
	}

	public void setComments(PageView<Comment> comments) {
		this.comments = comments;
	}

	public PageView<Comment> getComments() {
		return comments;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public String getIds() {
		return ids;
	}

}
