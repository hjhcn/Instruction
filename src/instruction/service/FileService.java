package instruction.service;

import instruction.model.File;

public interface FileService {
	public File get(int fid);

	public File decompAndConverFile();
}
