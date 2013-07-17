package instruction.service.impl;

import instruction.SystemConstants;
import instruction.dao.TravelSignDao;
import instruction.model.TravelSign;
import instruction.service.TravelSignService;
import instruction.util.IdcardValidator;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TravelSignServiceImpl implements TravelSignService {
	private TravelSignDao travelSignDao;

	public void saveOrUpdate(TravelSign travelSign) {
		travelSignDao.saveOrUpdate(travelSign);
	}

	public int sign(TravelSign travelSignInput) {
		if (null == travelSignInput) {
			return SystemConstants.FEEDBACK.TRAVELSIGN_NULL_ERROR;
		} else if (null == travelSignInput.getName()
				|| travelSignInput.getName().isEmpty()) {
			return SystemConstants.FEEDBACK.TRAVELSIGN_NAME_ERROR;
		} else if (null == travelSignInput.getIdCardNo()
				|| !new IdcardValidator().isValidatedAllIdcard(travelSignInput
						.getIdCardNo())
				|| this.isIdCardNoExists(travelSignInput.getIdCardNo())) {
			return SystemConstants.FEEDBACK.TRAVELSIGN_IDCARDNO_ERROR;
		} else if (null == travelSignInput.getPhone()
				|| travelSignInput.getPhone().isEmpty()
				|| !this.isNumeric(travelSignInput.getPhone())) {
		} else if (null != travelSignInput.getType()) {
			if (travelSignInput.getType().equals(
					SystemConstants.TRAVEL_SIGN_TYPE.CAR.toString())) {
				// 自驾
				if (null == travelSignInput.getEnrollment()
						|| travelSignInput.getEnrollment() <= 0) {
					return SystemConstants.FEEDBACK.TRAVELSIGN_ENROLLMENT_ERROR;
				} else if (null == travelSignInput.getPlateNumber()
						|| travelSignInput.getPlateNumber().isEmpty()) {
					return SystemConstants.FEEDBACK.TRAVELSIGN_PLATENUMBER_ERROR;
				}
			} else if (travelSignInput.getType().equals(
					SystemConstants.TRAVEL_SIGN_TYPE.BIK.toString())) {
				// 车协
				if (null == travelSignInput.getYear()
						|| travelSignInput.getYear() <= 0) {
					return SystemConstants.FEEDBACK.TRAVELSIGN_YEAR_ERROR;
				}
			} else if (travelSignInput.getType().equals(
					SystemConstants.TRAVEL_SIGN_TYPE.OTR.toString())) {
				// 其他
			} else
				return SystemConstants.FEEDBACK.TRAVELSIGN_TYPE_ERROR;
		} else
			return SystemConstants.FEEDBACK.TRAVELSIGN_TYPE_ERROR;
		TravelSign travelSign = new TravelSign(travelSignInput);
		this.saveOrUpdate(travelSign);

		return SystemConstants.FEEDBACK.SUCCESS;
	}

	public void setTravelSignDao(TravelSignDao travelSignDao) {
		this.travelSignDao = travelSignDao;
	}

	public boolean isNumeric(String str) {
		Pattern pattern = Pattern.compile("[0-9]*");
		Matcher isNum = pattern.matcher(str);
		if (!isNum.matches()) {
			return false;
		}
		return true;
	}

	public boolean isIdCardNoExists(String idCardNo) {
		List<TravelSign> travelSigns = travelSignDao.findByProperty("idCardNo",
				idCardNo);
		if (travelSigns.size() > 0) {
			return true;
		}
		return false;
	}

}
