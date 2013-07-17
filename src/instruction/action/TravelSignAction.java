package instruction.action;

import instruction.model.TravelSign;
import instruction.service.TravelSignService;

public class TravelSignAction extends BaseAction {

	private static final long serialVersionUID = 6502159202326252071L;
	private TravelSignService travelSignService;
	private TravelSign travelSignInput;

	public String index() {
		return SUCCESS;
	}

	public String form() {
		return SUCCESS;
	}

	public String sign() {
		feedback = travelSignService.sign(travelSignInput);
		return SUCCESS;
	}

	public void setTravelSignService(TravelSignService travelSignService) {
		this.travelSignService = travelSignService;
	}

	public void setTravelSignInput(TravelSign travelSignInput) {
		this.travelSignInput = travelSignInput;
	}

	public TravelSign getTravelSignInput() {
		return this.travelSignInput;
	}

	public int getFeedback() {
		return this.feedback;
	}

}
