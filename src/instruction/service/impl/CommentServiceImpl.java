package instruction.service.impl;

import instruction.SystemConstants;
import instruction.SystemConstants.COMMENT_CREDIT_TYPE;
import instruction.dao.CommentDao;
import instruction.model.Comment;
import instruction.model.Instruction;
import instruction.model.User;
import instruction.model.UserSession;
import instruction.service.CommentService;
import instruction.service.CreditService;
import instruction.service.InsService;
import instruction.service.UserService;
import instruction.util.Time;
import instruction.util.page.PageView;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public class CommentServiceImpl implements CommentService {
	public CommentDao commentDao;
	public UserService userService;
	public InsService insService;
	public CreditService creditService;

	public void setCommentDao(CommentDao commentDao) {
		this.commentDao = commentDao;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public void setInsService(InsService insService) {
		this.insService = insService;
	}

	public void setCreditService(CreditService creditService) {
		this.creditService = creditService;
	}

	public int post(UserSession userSession, int iid, Short grade, String content) {
		User user = userService.get(userSession.getUid());
		if (user == null)
			return SystemConstants.FEEDBACK.UID_ERROR;
		else {
			Instruction ins = insService.get(iid, SystemConstants.STATUS.PASS);
			if (grade < 1 || grade > 5)
				return SystemConstants.FEEDBACK.COMMENT_GRADE_ERROR;
			else if (null == content || "".equals(content))
				return SystemConstants.FEEDBACK.COMMENT_CONTENT_ERROR;
			else {
				Comment comment = new Comment();
				comment.setUser(user);
				comment.setUsername(userSession.getUsername());
				comment.setDateline(Time.getTimeStamp());
				comment.setGrade(grade);
				comment.setInstruction(ins);
				comment.setIp(userSession.getIp());
				comment.setContent(content);
				comment.setCreditStatus(new Short((short) 0));
				commentDao.saveOrUpdate(comment);

				return SystemConstants.FEEDBACK.SUCCESS;
			}
		}
	}

	public PageView<Comment> findScrollData(int uid, int iid, int page, int rows, String order,
			String sort) {
		PageView<Comment> pageView = new PageView<Comment>(page, rows);
		ArrayList<String> whereClause = new ArrayList<String>();
		if (uid > 0)
			whereClause.add("user.uid=" + uid);
		if (iid > 0)
			whereClause.add("instruction.id=" + iid);
		LinkedHashMap<String, String> orderbyClause = new LinkedHashMap<String, String>();
		orderbyClause.put(sort, order);
		pageView.setQueryResult(commentDao.findScrollData(page, rows, whereClause, orderbyClause));
		return pageView;
	}

	public int credit(String ids, Short creditStatus) {
		int feedback = 0;
		String[] idArray = ids.split(",");
		for (String idStr : idArray) {
			try {
				int id = Integer.parseInt(idStr);
				Comment comment = commentDao.get(id);
				if (comment.getCreditStatus() < creditStatus) {
					comment.setCreditStatus(creditStatus);
					int creditFeedback = SystemConstants.FEEDBACK.SUCCESS;
					if (creditStatus == 2) {
						int crid = 3;
						if (userService.isDownLoad(comment.getUser(), comment.getInstruction()
								.getId())
								&& !creditService.isLogExists(7, comment.getInstruction(),
										comment.getUser())) {// 已下载并且是第一次
							crid = 7;
						}
						creditFeedback = creditService.addCredit(crid, comment.getInstruction(),
								comment.getUser(), comment.getDateline());
						if (creditFeedback != SystemConstants.FEEDBACK.SUCCESS) {
							if (creditFeedback == SystemConstants.FEEDBACK.CREDIT_EXEED_THRESHOLD) {
								comment.setCreditDesc("评论积分规则超过上限，未给予！");
							} else if (creditFeedback == SystemConstants.FEEDBACK.CREDIT_0) {
								comment.setCreditDesc("积分为0不再加入积分记录表");
							}
							comment.setCreditStatus(new Short((short) 3));
						}
					}
					commentDao.saveOrUpdate(comment);
					if (idArray.length == 1 && creditFeedback != SystemConstants.FEEDBACK.SUCCESS)
						feedback = creditFeedback;
					else
						feedback++;
				}
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}
		}
		return feedback;
	}

	public int creditStatistic(int uid, int startTimeStamp, int endTimeStamp, Short creditStatus) {
		ArrayList<String> whereClause = new ArrayList<String>();
		if (uid > 0)
			whereClause.add("user.uid=" + uid);
		if (startTimeStamp < endTimeStamp) {
			whereClause.add("dateline>=" + startTimeStamp);
			whereClause.add("dateline<=" + endTimeStamp);
		}
		whereClause.add("dateline<=" + endTimeStamp);
		if (creditStatus != COMMENT_CREDIT_TYPE.SHOWALL) {
			whereClause.add("creditStatus=" + creditStatus);
		}
		return commentDao.operate(whereClause, "count", "creditStatus");
	}

}
