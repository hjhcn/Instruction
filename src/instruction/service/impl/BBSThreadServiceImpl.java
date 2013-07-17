package instruction.service.impl;

import instruction.SystemConstants;
import instruction.dao.BBSThreadDao;
import instruction.model.BBSThread;
import instruction.service.BBSThreadService;

import java.util.LinkedHashMap;
import java.util.List;

public class BBSThreadServiceImpl implements BBSThreadService {
	private BBSThreadDao bbsThreadDao;

	public int add(String title,String link,int order) {
		int feedback=SystemConstants.FEEDBACK.SUCCESS;
		if(title==null||"".equals(title))
			feedback = SystemConstants.FEEDBACK.BBSTHREAD_TITLE_ERROR;
		else if (link == null || "".equals(link))
			feedback = SystemConstants.FEEDBACK.BBSTHREAD_LINK_ERROR;
		else {
			BBSThread bbsTHread = new BBSThread();
			bbsTHread.setTitle(title);
			bbsTHread.setLink(link);
			bbsTHread.setOrder(order);
			bbsThreadDao.saveOrUpdate(bbsTHread);
			feedback=SystemConstants.FEEDBACK.SUCCESS;
		}
		return feedback;
	}

	public int edit(List<BBSThread> inputs) {
		for(BBSThread bbsTHread:inputs){
			bbsThreadDao.update(bbsTHread);
		}
		return inputs.size();
	}
	
	public List<BBSThread> findAll() {
		return bbsThreadDao.findAll();
	}

	public List<BBSThread> findTop(int top) {
		LinkedHashMap<String, String> orderbyClause = new LinkedHashMap<String, String>();
		orderbyClause.put("order", "desc");
		return bbsThreadDao.findTopData(top, null, null, orderbyClause);
	}

	public void setBbsThreadDao(BBSThreadDao bbsThreadDao) {
		this.bbsThreadDao = bbsThreadDao;
	}

	public int delete(int id) {
		BBSThread bbsThread=bbsThreadDao.get(id);
		if(bbsThread==null)return SystemConstants.FEEDBACK.BBSTHREAD_NULL_ERROR;
		else{
			bbsThreadDao.delete(bbsThread);
			return SystemConstants.FEEDBACK.SUCCESS;
		}
	}


}
