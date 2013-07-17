package instruction.service.impl;

import instruction.SystemConstants;
import instruction.dao.BrandDao;
import instruction.model.Brand;
import instruction.service.BrandService;
import instruction.util.page.PageView;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class BrandServiceImpl implements BrandService {
	public BrandDao brandDao;

	public PageView<Brand> findScrollData(int page, int rows, String order, String sort) {
		ArrayList<String> whereClause = new ArrayList<String>();
		LinkedHashMap<String, String> orderbyClause = new LinkedHashMap<String, String>();
		orderbyClause.put(sort, order);
		PageView<Brand> brands = new PageView<Brand>(page, rows);
		brands.setQueryResult(brandDao.findScrollData(page, rows, whereClause, orderbyClause));
		return brands;
	}

	public void setBrandDao(BrandDao brandDao) {
		this.brandDao = brandDao;
	}

	public int add(Brand brandInput) {
		List<Brand> brands = brandDao.findByProperty("name", brandInput.getName());
		if (brands != null && brands.size() > 0) {
			return SystemConstants.FEEDBACK.BRAND_NAME_EXSIT;
		} else {
			Brand brand = new Brand();
			brand.setName(brandInput.getName());
			brand.setNameEn(brandInput.getNameEn());
			brand.setNameZh(brandInput.getNameEn());
			brandDao.saveOrUpdate(brand);
			return SystemConstants.FEEDBACK.SUCCESS;
		}
	}

	public int edit(List<Brand> brandInputs) {
		int count = 0;
		for (Brand brandInput : brandInputs) {
			Brand brand = brandDao.get(brandInput.getId());
			if (brand != null) {
				brand.setName(brandInput.getName());
				brand.setNameEn(brandInput.getNameEn());
				brand.setNameZh(brandInput.getNameEn());
				brandDao.saveOrUpdate(brand);
				count++;
			}
		}
		return count;
	}

	public int delete(int bid) {
		Brand brand = brandDao.get(bid);
		if (brand == null) {
			return SystemConstants.FEEDBACK.BRAND_BID_NOEXSIT;
		} else {
			brand.setCats(null);
			brandDao.saveOrUpdate(brand);
			brandDao.delete(brand);
			return SystemConstants.FEEDBACK.SUCCESS;
		}
	}

	public Brand get(int bid) {
		return brandDao.get(bid);
	}

	public List<Brand> all() {
		return brandDao.getAll();
	}

	public void saveOrUpdate(Brand brand) {
		brandDao.saveOrUpdate(brand);
	}

}
