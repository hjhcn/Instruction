package instruction.service;

import instruction.model.TravelSign;

public interface TravelSignService {
	public void saveOrUpdate(TravelSign travelSign);

	public int sign(TravelSign travelSign);

}
