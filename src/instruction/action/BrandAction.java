package instruction.action;

import instruction.model.Brand;
import instruction.service.BrandService;
import instruction.util.page.PageView;

public class BrandAction extends BaseAction {
	private static final long serialVersionUID = 7930453330829478289L;
	private BrandService brandService;
	private int cid = 0;
	private PageView<Brand> brands;
	private int pageNum = 1;
	private int pageSize = 100;

	public String list() {
		brands = brandService.findScrollData(pageNum, pageSize, order, sort);
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

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getFeedback() {
		return this.feedback;
	}
}
