package instruction.service.impl;

import instruction.SystemConstants;
import instruction.SystemConstants.OPERATE;
import instruction.SystemConstants.STATUS;
import instruction.dao.NewsDao;
import instruction.dao.UserDao;
import instruction.model.News;
import instruction.model.UploadFile;
import instruction.model.User;
import instruction.service.NewsService;
import instruction.service.UploadFileService;
import instruction.util.Time;
import instruction.util.page.PageView;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class NewsServiceImpl implements NewsService {
	private NewsDao newsDao;
	private UserDao userDao;
	private UploadFileService uploadFileService;

	public void setNewsDao(NewsDao newsDao) {
		this.newsDao = newsDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	public void setUploadFileService(UploadFileService uploadFileService) {
		this.uploadFileService = uploadFileService;
	}

	/**
	 * upload or edit news
	 * 
	 * @param uid
	 *            id of user
	 * @return return a integer feedback
	 */
	public int operate(int uid, News newsInput, OPERATE operate) {
		User user = userDao.get(uid);
		int time = Time.getTimeStamp();
		if (newsInput.getTitle() == null || newsInput.getTitle().equals(""))
			return SystemConstants.FEEDBACK.NEWS_TITLE_ERROR;
		if ((operate == OPERATE.ADMIN_UPLOAD || operate == OPERATE.USER_UPLOAD)
				&& !checkTitle(newsInput.getTitle()))
			return SystemConstants.FEEDBACK.NEWS_TITLE_ERROR;
		if (newsInput.getContent() == null || newsInput.getContent().equals(""))
			return SystemConstants.FEEDBACK.NEWS_CONTENT_ERROR;
		if (newsInput.getDescription() == null || newsInput.getDescription().equals(""))
			return SystemConstants.FEEDBACK.NEWS_DESCRIPTION_ERROR;
		UploadFile coverFile = null;
		if (newsInput.getCoverFile().getId() != null) {
			coverFile = uploadFileService.get(newsInput.getCoverFile().getId());
			if (coverFile != null) {
				if (coverFile.getUser().getUid() != uid) {
					return SystemConstants.FEEDBACK.UPLOADFILE_NOT_BELONG_TO_USER;
				}
			}
		}
		boolean isNewFile = false;
		News news = null;
		if (operate == OPERATE.ADMIN_UPLOAD || operate == OPERATE.USER_UPLOAD) {
			news = new News();
			news.setUploadTime(time);
			news.setStatus(newsInput.getStatus());
			news.setUser(user);
			if (coverFile != null) {
				// 上传时，封面不空
				isNewFile = true;
			}
		} else if ((operate == OPERATE.EDIT)) {
			news = newsDao.get(newsInput.getId());
			if (null == news) {
				return SystemConstants.FEEDBACK.NEWS_ID_NOEXSIT;
			} else {
				news.setStatus(STATUS.UNVERIFY);
				if (coverFile != null && news.getCoverFile() != null
						&& news.getCoverFile().getId() != newsInput.getCoverFile().getId()) {
					// 编辑时，封面不空且封面修改
					isNewFile = true;
				}
			}
		}
		news.setTitle(newsInput.getTitle());
		news.setContent(newsInput.getContent());
		news.setDescription(newsInput.getDescription());
		news.setUpdateTime(time);
		if (isNewFile) {
			news.setCoverUrl(coverFile.getFileUrl());
			news.setCoverFile(coverFile);
		}
		newsDao.saveOrUpdate(news);
		return SystemConstants.FEEDBACK.SUCCESS;
	}

	public boolean checkTitle(String title) {
		List<String> whereClause = new ArrayList<String>();
		whereClause.add("title='" + title + "'");
		int size = newsDao.getCount(whereClause);
		if (size > 0)
			return false;
		else
			return true;
	}

	public int operate(String ids, OPERATE operate) {
		int count = 0;
		String[] idArray = ids.split(",");
		for (String idStr : idArray) {
			try {
				int id = Integer.parseInt(idStr);
				News news = newsDao.get(id);
				if (news != null) {
					if (operate == OPERATE.DELETE) {
						newsDao.delete(news);
					} else if (operate == OPERATE.VERIFY) {
						news.setStatus(STATUS.PASS);
						newsDao.saveOrUpdate(news);
					} else if (operate == OPERATE.UNVERIFY) {
						news.setStatus(STATUS.UNVERIFY);
						newsDao.saveOrUpdate(news);
					}
					count++;
				}
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}
		}
		return count;
	}

	public News get(int id, Short status) {
		News news = newsDao.get(id);
		news.setViewCount(news.getViewCount() + 1);
		newsDao.saveOrUpdate(news);
		if (news.getStatus() < status)
			news = null;
		return news;
	}

	public PageView<News> findScrollData(int page, int rows, String order, String sort) {
		return this.findScrollData(page, rows, order, sort, SystemConstants.STATUS.PASS);
	}

	public PageView<News> findScrollData(int page, int rows, String order, String sort, Short status) {
		return this.findScrollData(null, page, rows, order, sort, status);
	}

	public PageView<News> findScrollData(String search, int page, int rows, String order,
			String sort, Short status) {
		ArrayList<String> whereClause = new ArrayList<String>();
		if (null != search && !"".equals(search)) {
			whereClause.add("title like '%" + search + "%'");
		}
		if (status > 0)
			whereClause.add("status>=" + status);
		PageView<News> newes = new PageView<News>(page, rows);
		LinkedHashMap<String, String> orderbyClause = new LinkedHashMap<String, String>();
		orderbyClause.put(sort, order);
		newes.setQueryResult(newsDao.findScrollData(page, rows, whereClause, orderbyClause));
		return newes;
	}

	public List<News> findTopData(int count) {
		LinkedHashMap<String, String> orderbyClause = new LinkedHashMap<String, String>();
		ArrayList<String> whereClause = new ArrayList<String>();
		whereClause.add("status>=" + SystemConstants.STATUS.PASS);
		orderbyClause.put("id", "desc");
		return newsDao.findTopData(count, whereClause, null, orderbyClause);
	}

}
