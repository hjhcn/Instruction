package instruction.service;

import instruction.SystemConstants.EXT_TYPE;
import instruction.model.Admin;
import instruction.model.UploadFile;
import instruction.util.page.PageView;

import java.io.File;
import java.util.List;

public interface UploadFileService {
	public UploadFile get(int id);

	/**
	 * @param fileList
	 *            文件列表
	 * @param fileListFileName
	 *            文件列表原名
	 * @param fileListContentType
	 *            文件类型列表
	 * @param user
	 *            用户
	 * @param extType
	 *            接收类型
	 * @return 成功返回ufid，失败则返回0；
	 */
	public UploadFile uploadFile(File file, String fileFileName, String fileContentType, int uid,
			EXT_TYPE extType);

	public PageView<UploadFile> list(int uid, String statuses, String search, int startTimeStamp,
			int endTimeStamp, int page, int rows, String order, String sort, EXT_TYPE extType);

	public boolean create(UploadFile uploadFileInput);

	public boolean saveOrUpdate(UploadFile uploadFile);

	public int setNotTmp(int id, short status);

	public boolean saveFile(UploadFile uploadFile);

	public int importFromFtp(Admin admin, final List<String> importFileNames);

}
