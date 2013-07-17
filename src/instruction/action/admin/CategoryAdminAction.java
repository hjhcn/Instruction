package instruction.action.admin;

import instruction.model.Brand;
import instruction.model.Category;
import instruction.service.BrandService;
import instruction.service.CategoryService;

import java.util.List;

public class CategoryAdminAction extends BaseAdminAction {

	private static final long serialVersionUID = 7930453330829478289L;
	private CategoryService categoryService;
	private BrandService brandService;
	private int cid = 0;
	private List<Category> categorys;
	private List<Brand> brands;
	/**
	 * BelongWithCategory 属于某个分类的品牌
	 */
	private List<Brand> category_brandsBWC;
	/**
	 * NotBelongWithCategory 不属于某个分类的品牌
	 */
	private List<Brand> category_brandsNBWC;
	private Category category;
	private String name;
	private int parentId;
	private String bids;

	public String category() {
		category = categoryService.get(cid);
		category_brandsBWC = categoryService.brandsByCid(cid);
		category_brandsNBWC = brandService.all();
		category_brandsNBWC.removeAll(category_brandsBWC);
		return SUCCESS;
	}

	public String list() {
		categorys = categoryService.getTree(cid);
		return SUCCESS;
	}

	public String addPage() {
		categorys = categoryService.getTree(cid);
		return SUCCESS;
	}

	public String delete() {
		categoryService.detele(cid);
		return SUCCESS;
	}

	public String add() {
		feedback = categoryService.add(name, parentId);
		return SUCCESS;
	}

	public String edit() {
		// 取消修改父分类
		feedback = categoryService.edit(cid, name, parentId, bids);
		return SUCCESS;
	}

	public String brandsByCid() {
		brands = categoryService.brandsByCid(cid);
		return SUCCESS;
	}

	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	public void setCid(int cid) {
		this.cid = cid;
	}

	public int getCid() {
		return cid;
	}

	public List<Category> getCategorys() {
		return categorys;
	}

	public int getFeedback() {
		return this.feedback;
	}

	public void setBrands(List<Brand> brands) {
		this.brands = brands;
	}

	public List<Brand> getBrands() {
		return brands;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Category getCategory() {
		return category;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getParentId() {
		return parentId;
	}

	public void setParentId(int parentId) {
		this.parentId = parentId;
	}

	public void setBrandService(BrandService brandService) {
		this.brandService = brandService;
	}

	public void setBids(String bids) {
		this.bids = bids;
	}

	public String getBids() {
		return bids;
	}

	public List<Brand> getCategory_brandsNBWC() {
		return category_brandsNBWC;
	}

	public void setCategory_brandsNBWC(List<Brand> category_brandsNBWC) {
		this.category_brandsNBWC = category_brandsNBWC;
	}

	public List<Brand> getCategory_brandsBWC() {
		return category_brandsBWC;
	}

	public void setCategory_brandsBWC(List<Brand> category_brandsBWC) {
		this.category_brandsBWC = category_brandsBWC;
	}
}
