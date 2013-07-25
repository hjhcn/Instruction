package instruction.action;

import instruction.model.Comment;
import instruction.model.UserSession;
import instruction.service.CommentService;
import instruction.util.page.PageView;

public class CommentAction extends SessionBaseAction {

	private static final long serialVersionUID = -6099566024995318676L;
	private PageView<Comment> comments;
	private int iid;
	private CommentService commentService;
	private short grade = 1;
	private String content = "";
	private int uid = 0;

	public String postAuth() {
		feedback = commentService.post(userSession, iid, grade, content);
		return SUCCESS;
	}

	public String list() {
		comments = commentService.findScrollData(uid, iid, page, rows, order, sort);
		return SUCCESS;
	}

	public UserSession getUserSession() {
		return userSession;
	}

	public CommentService getCommentService() {
		return commentService;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public PageView<Comment> getComments() {
		return comments;
	}

	public void setIid(int iid) {
		this.iid = iid;
	}

	public void setCommentService(CommentService commentService) {
		this.commentService = commentService;
	}

	public int getFeedback() {
		return feedback;
	}

	public void setGrade(short grade) {
		this.grade = grade;
	}

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

}
