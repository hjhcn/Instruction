package instruction.service;

import instruction.SystemConstants;
import instruction.SystemConstants.OPERATE;
import instruction.model.AdminStatistic;
import instruction.model.InsUpload;
import instruction.model.Instruction;
import instruction.model.TimeStampRange;
import instruction.model.UploadFile;
import instruction.util.page.PageView;

import java.util.List;

public interface InsService {
	public Instruction get(int id, Short status);

	public List<Instruction> findTopData(int cid, int bid, int hasIcon, int page, String groupby,
			String order, String sort, Short status);

	public PageView<Instruction> findScrollData(int uid, int page, int rows, String order,
			String sort, Short status);

	public PageView<Instruction> findScrollData(int uid, int cid, int bid, String search,
			TimeStampRange tsRange, int page, int rows, String order, String sort, Short status,
			boolean isMobile3d);

	public PageView<Instruction> findScrollDataCascade(int uid, int cid, int bid, String search,
			TimeStampRange tsRange, int page, int rows, String order, String sort, Short status,
			boolean isMobile3d);

	public int operate(int uid, InsUpload insUpload, Short status, SystemConstants.OPERATE operate);

	public int operate(String ids, OPERATE operate);

	public UploadFile download(int iid, int uid);

	public List<Instruction> findTopNew(int count);

	/**
	 * 管理员后台统计
	 * 
	 * @param startTime
	 * @param endTime
	 * @param uid
	 * @return
	 */
	public List<AdminStatistic> statistic(int startTime, int endTime, int uid);

	/**
	 * 用户上传数量统计
	 * 
	 * @param uid
	 * @param startTimeStamp
	 * @param endTimeStamp
	 * @param status
	 * @return
	 */
	public int countByUid(int uid, int startTimeStamp, int endTimeStamp, short status);

	public int judageModel(InsUpload insUpload);

	public void verifyCountBath();

	public void reOrder(String orderStr);

	public String dataExport(int startTime, int endTime);

	public int reCoverter(int id);

}
