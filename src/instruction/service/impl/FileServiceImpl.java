package instruction.service.impl;

import instruction.SystemConstants;
import instruction.dao.FileDao;
import instruction.model.File;
import instruction.service.FileService;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class FileServiceImpl implements FileService {
	private FileDao fileDao;

	public File decompAndConverFile() {
		ArrayList<String> whereClause = new ArrayList<String>();
		whereClause.add("fileUrl like '%rar%' or fileUrl like '%zip%'");
		ArrayList<String> groupbyClause = new ArrayList<String>();
		groupbyClause.add("instruction.id");
		LinkedHashMap<String, String> orderbyClause = new LinkedHashMap<String, String>();
		orderbyClause.put("id", "desc");
		List<File> files = fileDao.findTopData(100000, whereClause,
				groupbyClause, orderbyClause);
		for (File file : files) {
			SystemConstants.decompFile.push(SystemConstants.UPLOAD_FOLDER
					+ file.getFileUrl());
		}
		return null;
	}

	public File get(int fid) {
		return fileDao.get(fid);
	}

	public void setFileDao(FileDao fileDao) {
		this.fileDao = fileDao;
	}

	public FileDao getFileDao() {
		return fileDao;
	}

}
