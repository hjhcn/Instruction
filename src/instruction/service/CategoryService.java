package instruction.service;

import instruction.model.Brand;
import instruction.model.Category;

import java.util.List;

public interface CategoryService {
	public List<Category> findAll();

	public List<Category> findRelated(Category cat);

	public List<Category> getTree(int parentId);

	public List<Brand> brandsByCid(int cid);

	public Category get(int cid);

	public int add(String name, int parentId);

	public int edit(int cid, String name, int parentId, String bids);

	public void detele(int cid);

	public void saveOrUpdate(Category category);
}
