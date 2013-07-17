package instruction.service.impl;

import instruction.dao.NaviDao;
import instruction.model.Navigation;
import instruction.service.NaviService;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class NaviServiceImpl implements NaviService {
	private NaviDao naviDao;

	public List<Navigation> getAllTree() {
		LinkedHashMap<String, String> orderbyClause = new LinkedHashMap<String, String>();
		orderbyClause.put("category.id", "asc");
		List<Navigation> sNavis = naviDao
				.findAllData(null, null, orderbyClause);
		List<Navigation> dNavis = new ArrayList<Navigation>();
		int cid = 0;
		List<Navigation> sNaviPart = new ArrayList<Navigation>();
		List<Navigation> dNaviPart = new ArrayList<Navigation>();
		for (Navigation navi : sNavis) {
			if (navi.getCategory().getId() != cid) {
				cid = navi.getCategory().getId();
				if (sNaviPart.size() > 0) {
					this.getTree(sNaviPart, 0, 0, dNaviPart);
					dNavis.addAll(dNaviPart);
					sNaviPart.clear();
				}
			} 
			sNaviPart.add(navi);
		}
		this.getTree(sNaviPart, 0, 0, dNaviPart);
		dNavis.addAll(dNaviPart);
		return dNavis;
	}

	public List<Navigation> getTreeByCid(int cid) {
		List<Navigation> sNavis = naviDao.findByProperty("category.id", cid);
		List<Navigation> dNavis = new ArrayList<Navigation>();
		return getTree(sNavis, 0, 0, dNavis);
	}

	public List<Navigation> getTree(List<Navigation> sNavis, int parentId,
			int level, List<Navigation> dNavis) {
		for (Navigation navi : sNavis) {
			if (navi.getParentId().intValue() == parentId) {
				navi.setLevel(level);
				dNavis.add(navi);
				getTree(sNavis, navi.getId(), level + 1, dNavis);
			}
		}
		return dNavis;
	}

	public void setNaviDao(NaviDao naviDao) {
		this.naviDao = naviDao;
	}

}
