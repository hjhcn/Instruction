package instruction.service.impl;

import instruction.SystemConstants;
import instruction.SystemConstants.EXT_TYPE;
import instruction.dao.UploadFileDao;
import instruction.model.Admin;
import instruction.model.UploadFile;
import instruction.service.UploadFileService;
import instruction.service.UserService;
import instruction.util.FileUtils;
import instruction.util.Time;
import instruction.util.page.PageView;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.UUID;

public class UploadFileServiceImpl implements UploadFileService {
	private UploadFileDao uploadFileDao;
	private UserService userService;

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public UploadFile get(int id) {
		return uploadFileDao.get(id);
	}

	public void setUploadFileDao(UploadFileDao uploadFileDao) {
		this.uploadFileDao = uploadFileDao;
	}

	public int importFromFtp(Admin admin, final List<String> importFileNames) {
		int count = 0;
		File ftpFile = new File(SystemConstants.UPLOAD_FOLDER_TEMP + admin.getUsername() + "/");
		if (importFileNames != null && ftpFile.exists() && ftpFile.isDirectory()) {
			File[] files = ftpFile.listFiles(new FilenameFilter() {
				public boolean accept(File dir, String name) {
					if (importFileNames.contains(name)) {
						return true;
					}
					return false;
				}
			});
			for (File file : files) {
				UploadFile uploadfile = this.uploadFile(file, file.getName(), "", admin.getUid(),
						EXT_TYPE.ALL);
				if (uploadfile.getId() > 0) {
					count++;
				}
			}
		}
		return count;
	}

	public PageView<UploadFile> list(int uid, String statuses, String search, int startTimeStamp,
			int endTimeStamp, int page, int rows, String order, String sort, EXT_TYPE extType) {
		ArrayList<String> whereClause = new ArrayList<String>();
		if (uid > 0)
			whereClause.add("user.uid=" + uid);
		if (statuses != null) {
			String[] statusArray = statuses.split(",");
			String statusFilterStr = "";
			for (int i = 0; i < statusArray.length; i++) {
				if (i > 0) {
					statusFilterStr += " or ";
				}
				statusFilterStr += "status=" + statusArray[i];
			}
			whereClause.add(statusFilterStr);
		}
		if (null != search && !search.isEmpty()) {
			whereClause.add("description like '%" + search + "%'");

		}
		whereClause.add("dateline>=" + startTimeStamp);
		whereClause.add("dateline<=" + endTimeStamp);
		if (extType == EXT_TYPE.PIC) {
			whereClause.add("fileType in ("
					+ FileUtils.acceptExt2SqlString(SystemConstants.ACCEPTED_PIC_EXT) + ")");
		} else if (extType == EXT_TYPE.DOC) {
			whereClause.add("fileType in ("
					+ FileUtils.acceptExt2SqlString(SystemConstants.ACCEPTED_DOC_EXT) + ")");
		} else if (extType == EXT_TYPE.SWF) {
			whereClause.add("fileType in ("
					+ FileUtils.acceptExt2SqlString(SystemConstants.ACCEPTED_SWF_EXT) + ")");
		}

		LinkedHashMap<String, String> orderbyClause = new LinkedHashMap<String, String>();
		orderbyClause.put(sort, order);
		PageView<UploadFile> uploadFiles = new PageView<UploadFile>(page, rows);
		uploadFiles.setQueryResult(uploadFileDao.findScrollData(page, rows, whereClause,
				orderbyClause));
		return uploadFiles;
	}

	public void delete(int uid, String ids) {
		String[] idArray = ids.split(",");
		for (String idStr : idArray) {
			try {
				int id = Integer.parseInt(idStr);
				UploadFile uploadFile = uploadFileDao.get(id);
				if (uploadFile != null && uploadFile.getUser().getUid() == uid) {
					uploadFile.setStatus(new Short((short) -2));
				}
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}
		}
	}

	public boolean create(UploadFile uploadFileInput) {
		UploadFile uploadFile = new UploadFile();
		uploadFile.setFileUrl(uploadFileInput.getFileUrl());
		uploadFile.setFileSize(uploadFileInput.getFileSize());
		uploadFile.setDescription(uploadFileInput.getDescription());
		uploadFile.setUser(uploadFileInput.getUser());
		uploadFile.setFileType(uploadFileInput.getFileType());
		uploadFile.setStatus((short) 0);
		uploadFile.setDateline(Time.getTimeStamp());
		if (uploadFileInput.getServer() == null)
			uploadFile.setServer(SystemConstants.UPLOAD_FOLDER_SERVER);
		uploadFileDao.saveOrUpdate(uploadFile);
		return true;
	}

	public boolean saveOrUpdate(UploadFile uploadFile) {
		uploadFileDao.saveOrUpdate(uploadFile);
		return true;
	}

	public int setNotTmp(int id, short status) {
		UploadFile uploadFile = uploadFileDao.get(id);
		if (uploadFile != null) {
			uploadFile.setStatus(status);
			uploadFileDao.saveOrUpdate(uploadFile);
			return SystemConstants.FEEDBACK.SUCCESS;
		} else {
			return SystemConstants.FEEDBACK.UPLOADFILE_ID_NOEXSIT;
		}
	}

	public synchronized UploadFile uploadFile(File file, String fileFileName,
			String fileContentType, int uid, EXT_TYPE extType) {
		UploadFile uploadFile = new UploadFile();
		String ext = FileUtils.getExt(fileFileName);
		String pre = FileUtils.getPre(fileFileName);
		boolean isExtAccepted = false;
		if (extType == EXT_TYPE.ALL) {
			isExtAccepted = FileUtils.isExtAccepted(ext, SystemConstants.ACCEPTED_DOC_EXT)
					|| FileUtils.isExtAccepted(ext, SystemConstants.ACCEPTED_PIC_EXT)
					|| FileUtils.isExtAccepted(ext, SystemConstants.ACCEPTED_SWF_EXT);
		} else if (extType == EXT_TYPE.PIC) {
			isExtAccepted = FileUtils.isExtAccepted(ext, SystemConstants.ACCEPTED_PIC_EXT);
		} else if (extType == EXT_TYPE.DOC) {
			isExtAccepted = FileUtils.isExtAccepted(ext, SystemConstants.ACCEPTED_DOC_EXT);
		} else if (extType == EXT_TYPE.SWF) {
			isExtAccepted = FileUtils.isExtAccepted(ext, SystemConstants.ACCEPTED_SWF_EXT);
		}
		if (!isExtAccepted) {
			uploadFile.setId(SystemConstants.FEEDBACK.UPLOADFILE_TYPE_ERROR);
			return uploadFile;
		}

		String newFileName = UUID.randomUUID() + "." + ext;
		String folder = FileUtils.getFileFolderByNow();
		String fileFolderAndName = folder + newFileName;
		FileUtils.upload(SystemConstants.UPLOAD_FOLDER_TEMP + folder, newFileName, file);
		uploadFile.setFileUrl(fileFolderAndName);
		uploadFile.setFileSize((int) file.length() / 1024);
		uploadFile.setDescription(pre);
		uploadFile.setUser(userService.get(uid));
		uploadFile.setFileType(ext);
		uploadFile.setStatus((short) 0);
		uploadFile.setDateline(Time.getTimeStamp());
		uploadFile.setServer(SystemConstants.UPLOAD_FOLDER_SERVER);
		uploadFileDao.saveOrUpdate(uploadFile);

		return uploadFile;
	}

	public boolean saveFile(UploadFile uploadFile) {
		if (uploadFile != null) {
			if (uploadFile.getStatus() == 0) {
				FileUtils.movePath(SystemConstants.UPLOAD_FOLDER_TEMP + uploadFile.getFileUrl(),
						SystemConstants.UPLOAD_FOLDER + uploadFile.getFileUrl());
			}
			return true;
		}
		return false;
	}
}
