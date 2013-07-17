package instruction.service;

import instruction.model.Brand;
import instruction.util.page.PageView;

import java.util.List;

public interface BrandService {
	public PageView<Brand> findScrollData(int page, int rows, String order, String sort);

	public int add(Brand brandInput);

	public int edit(List<Brand> brandInputs);

	public int delete(int bid);

	public Brand get(int bid);

	public List<Brand> all();

	public void saveOrUpdate(Brand brand);
}
