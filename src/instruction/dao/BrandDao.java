package instruction.dao;

import instruction.model.Brand;

import java.util.List;

public interface BrandDao   extends BaseDao<Brand> {
	public void save(Brand brand);

	public List<Brand> getByName(String name);

	public List<Brand> getAll();

	public void delete(Brand brand);
}
