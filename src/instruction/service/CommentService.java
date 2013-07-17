package instruction.service;

import instruction.model.Comment;
import instruction.model.UserSession;
import instruction.util.page.PageView;

public interface CommentService {
	public PageView<Comment> findScrollData(int uid, int iid, int page, int rows, String order,
			String sort);

	public int post(UserSession userSession, int iid, Short grade, String content);

	public int credit(String ids, Short creditStatus);

	public int creditStatistic(int uid, int startTimeStamp, int endTimeStamp, Short creditStatus);
}
