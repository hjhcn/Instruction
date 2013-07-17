package instruction.action.admin;

import instruction.model.Brand;
import instruction.service.BrandService;
import instruction.util.page.PageView;

import java.util.List;

public class BrandAdminAction extends BaseAdminAction {
	private static final long serialVersionUID = 7930453330829478289L;
	private BrandService brandService;
	private int cid = 0;
	private PageView<Brand> brands;
	private int bid = 0;
	private Brand brand;
	private List<Brand> brandInputs;

	public String list() {
		brands = brandService.findScrollData(page, rows, order, sort);
		return SUCCESS;
	}

	public String brand() {
		setBrand(brandService.get(bid));
		return SUCCESS;
	}

	public String addPage() {
		return SUCCESS;
	}

	public String add() {
		feedback = brandService.add(brand);
		return SUCCESS;
	}

	public String edit() {
		feedback = brandService.edit(brandInputs);
		return SUCCESS;
	}

	public String delete() {
		feedback = brandService.delete(bid);
		return SUCCESS;
	}

	public void setBrandService(BrandService brandService) {
		this.brandService = brandService;
	}

	public void setCid(int cid) {
		this.cid = cid;
	}

	public int getCid() {
		return cid;
	}

	public void setBrands(PageView<Brand> brands) {
		this.brands = brands;
	}

	public PageView<Brand> getBrands() {
		return brands;
	}

	public int getFeedback() {
		return this.feedback;
	}

	public int getBid() {
		return bid;
	}

	public void setBid(int bid) {
		this.bid = bid;
	}

	public void setBrand(Brand brand) {
		this.brand = brand;
	}

	public Brand getBrand() {
		return brand;
	}

	public List<Brand> getBrandInputs() {
		return brandInputs;
	}

	public void setBrandInputs(List<Brand> brandInputs) {
		this.brandInputs = brandInputs;
	}
}
