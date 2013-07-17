package instruction.dao;

import java.util.List;

import instruction.model.Category;

public interface CategoryDao  extends BaseDao<Category> {

	List<Category> findLevel1();

}
