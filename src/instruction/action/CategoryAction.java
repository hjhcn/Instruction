package instruction.action;

import instruction.model.Brand;
import instruction.model.Category;
import instruction.service.CategoryService;

import java.util.List;

public class CategoryAction extends BaseAction {

	private static final long serialVersionUID = -5118638208369617326L;
	private CategoryService categoryService;
	private List<Category> categorys;
	private List<Brand> brands;
	private int cid;

	public int getCid() {
		return cid;
	}

	public void setCid(int cid) {
		this.cid = cid;
	}

	public List<Brand> getBrands() {
		return brands;
	}

	public void setBrands(List<Brand> brands) {
		this.brands = brands;
	}

	public String getTree() {
		categorys = categoryService.getTree(0);
		return SUCCESS;
	}

	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	public void setCategorys(List<Category> categorys) {
		this.categorys = categorys;
	}

	public List<Category> getCategorys() {
		return categorys;
	}

	public int getFeedback() {
		return feedback;
	}

	public String brandsByCid() {
		brands = categoryService.brandsByCid(cid);
		return SUCCESS;
	}

}
