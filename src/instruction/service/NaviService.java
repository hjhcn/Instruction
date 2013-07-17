package instruction.service;

import instruction.model.Navigation;

import java.util.List;

public interface NaviService {
	public List<Navigation> getAllTree();
	public List<Navigation> getTreeByCid(int cid);
}
