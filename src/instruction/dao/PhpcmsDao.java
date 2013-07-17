package instruction.dao;

import instruction.model.PhpcmsContent;

import java.util.List;

public interface PhpcmsDao {
	public List<PhpcmsContent> getAllNewContent();
	public List<PhpcmsContent> getAll();

	public void save(PhpcmsContent pc);
}
