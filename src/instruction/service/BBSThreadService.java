package instruction.service;

import instruction.model.BBSThread;

import java.util.List;

public interface BBSThreadService {
	public List<BBSThread> findAll();
	
	public List<BBSThread> findTop(int top);

	public int edit(List<BBSThread> inputs);

	public int delete(int id);

	public int add(String title, String link, int order);
}
