package instruction.model;

import instruction.rules.DocConvertFinishRule;

public class UploadFileConvertObject {
	private UploadFile insFile;
	private DocConvertFinishRule docConvertFinishRule;

	public UploadFileConvertObject(UploadFile insFile, DocConvertFinishRule docConvertFinishRule) {
		this.insFile = insFile;
		this.docConvertFinishRule = docConvertFinishRule;
	}

	public DocConvertFinishRule getDocConvertFinishRule() {
		return docConvertFinishRule;
	}

	public void setDocConvertFinishRule(DocConvertFinishRule docConvertFinishRule) {
		this.docConvertFinishRule = docConvertFinishRule;
	}

	public UploadFile getInsFile() {
		return insFile;
	}

	public void setInsFile(UploadFile insFile) {
		this.insFile = insFile;
	}
}
