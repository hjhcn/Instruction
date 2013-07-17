package instruction.service;


public interface BaseService<T> {

	public T get(int id);

	public void saveOrUpdate(T entity);
}
