package instruction.service.impl;

import instruction.SystemConstants;
import instruction.dao.CategoryDao;
import instruction.model.Brand;
import instruction.model.Category;
import instruction.service.BrandService;
import instruction.service.CategoryService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;

public class CategoryServiceImpl implements CategoryService {
	private CategoryDao categoryDao;
	private BrandService brandService;

	public void setBrandService(BrandService brandService) {
		this.brandService = brandService;
	}

	public List<Category> findAll() {
		return categoryDao.findAll();
	}

	public List<Category> findRelated(Category cat) {
		List<Category> sCates = this.findAll();
		List<Category> dCates = new ArrayList<Category>();
		return getTree(sCates, cat.getId(), 0, dCates);
	}

	public List<Category> getTree(int parentId) {
		// 同时获得时dCates冲突
		LinkedHashMap<String, String> orderbyClause = new LinkedHashMap<String, String>();
		orderbyClause.put("id", "asc");
		List<Category> sCates = categoryDao.findAllData(null, null,
				orderbyClause);
		List<Category> dCates = new ArrayList<Category>();
		return getTree(sCates, parentId, 0, dCates);
	}

	public List<Category> getTree(List<Category> sCates, int parentId,
			int level, List<Category> dCates) {
		for (Category cat : sCates) {
			if (cat.getParentId().intValue() == parentId) {
				cat.setLevel(level);
				dCates.add(cat);
				getTree(sCates, cat.getId(), level + 1, dCates);
			}
		}
		return dCates;
	}

	public List<Brand> brandsByCid(int cid) {
		List<Category> categorys = this.getTree(cid);
		// 这里需要优化效率
		List<Brand> brands = new ArrayList<Brand>(categoryDao.get(cid)
				.getBrands());
		for (Category category : categorys) {
			for (Brand brand : category.getBrands()) {
				if (!brands.contains(brand)) {
					brands.add(brand);
				}
			}
		}
		Collections.sort(brands, new Comparator<Brand>() {
			public int compare(Brand arg0, Brand arg1) {
				return arg0.getId() > arg1.getId() ? 1 : 0;
			}
		});
		return brands;
	}
	
	public int add(String name, int parentId) {
		List<Category> categorys = categoryDao.findByProperty("name", name);
		if (categorys != null && categorys.size() > 0)
			return SystemConstants.FEEDBACK.CATEGORY_NAME_EXSIT;
		else {
			Category category = new Category();
			category.setName(name);
			category.setParentId(parentId);
			category.setCount(0);
			categoryDao.saveOrUpdate(category);
			return SystemConstants.FEEDBACK.SUCCESS;
		}
	}

	public int addBrand(int cid, int bid) {
		Category category = categoryDao.get(cid);
		if (category == null)
			return SystemConstants.FEEDBACK.CATEGORY_CID_NOEXSIT;
		else {
			Brand brand = brandService.get(bid);
			if (brand == null)
				return SystemConstants.FEEDBACK.BRAND_BID_NOEXSIT;
			category.getBrands().add(brand);
			return SystemConstants.FEEDBACK.SUCCESS;
		}
	}

	public int edit(int cid, String name, int parentId, String bids) {
		// 暂时不修改父分类
		Category category = categoryDao.get(cid);
		if (category == null)
			return SystemConstants.FEEDBACK.CATEGORY_CID_NOEXSIT;
		else {
			String[] bidArray = bids.split(",");
			if (bidArray.length > 0) {
				Set<Brand> brands = new HashSet<Brand>();
				for (String strBid : bidArray) {
					try {
						int bid = Integer.parseInt(strBid);
						Brand brand = brandService.get(bid);
						brands.add(brand);
					} catch (NumberFormatException e) {

					}
				}
				category.setBrands(brands);
			}
			category.setName(name);
			// category.setParentId(parentId);
			categoryDao.saveOrUpdate(category);
			return SystemConstants.FEEDBACK.SUCCESS;
		}
	}

	public void setCategoryDao(CategoryDao categoryDao) {
		this.categoryDao = categoryDao;
	}

	public Category get(int cid) {
		return categoryDao.get(cid);
	}

	public void detele(int cid) {
		Category cat = categoryDao.get(cid);
		if (cat == null) {
			// return SystemConstants.FEEDBACK.CATEGORY_CID_NOEXSIT;
		} else {
			cat.setBrands(null);
			categoryDao.saveOrUpdate(cat);
			categoryDao.delete(cid);
			// return SystemConstants.FEEDBACK.SUCCESS;
		}

	}

	public void saveOrUpdate(Category category) {
		categoryDao.saveOrUpdate(category);
	}

}
