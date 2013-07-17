package instruction.util;

import instruction.SystemConstants;
import instruction.model.UploadFile;
import instruction.model.UploadFileConvertObject;
import instruction.rules.DocConvertFinishRule;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;

import com.artofsolving.jodconverter.DocumentConverter;
import com.artofsolving.jodconverter.openoffice.connection.OpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.connection.SocketOpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.converter.OpenOfficeDocumentConverter;

/*
 * doc docx格式转换
 * @author Administrator
 */
public class DocConverter extends java.lang.Thread {
	@SuppressWarnings("unused")
	private static final int environment = 1;
	// 环境1：windows
	// 2:linux(涉及pdf2swf路径问题)
	private static final String SWF_SUFFIX = "_SWF";
	private UploadFile insFileCoverting;
	@SuppressWarnings("unused")
	private String outputPath = "";// 输入路径，如果不设置就输出在默认位置
	private String fileName;
	private File pdfFile;
	private File swfFile;
	private File docFile;
	private boolean fromOffice = false;// ****有待改善，因为是顺序队列所以只设置了类成员变量
	private static DocConverter instance = null;
	private static LinkedList<UploadFileConvertObject> insFileConvertList = new LinkedList<UploadFileConvertObject>();
	private DocConvertFinishRule docConvertFinishRule;
	private short covertStatus = 0;

	private DocConverter() {

	}

	public static synchronized DocConverter getInstance() {
		if (instance == null) {
			instance = new DocConverter();
		}
		instance.start();
		return instance;
	}

	public void push(UploadFileConvertObject uploadFileConvertObject) {
		if (null != uploadFileConvertObject)
			insFileConvertList.add(uploadFileConvertObject);
	}

	/**
	 * 初始化
	 * 
	 * @param fileString
	 */
	private void ini(UploadFileConvertObject uploadFileConvertObject) {
		this.insFileCoverting = uploadFileConvertObject.getInsFile();
		this.docConvertFinishRule = uploadFileConvertObject.getDocConvertFinishRule();
		String tempFileString = insFileCoverting.getTempFolder();
		System.out.println("当前转换目录:" + tempFileString);
		fileName = FileUtils.getPre(tempFileString);
		docFile = new File(tempFileString);
		pdfFile = new File(fileName + ".pdf");
		File swfFolder = new File(fileName + SWF_SUFFIX);
		swfFolder.mkdir();
		swfFile = new File(fileName + SWF_SUFFIX + "/%.swf");
	}

	/**
	 * 转为PDF
	 * 
	 * @param file
	 */
	private void doc2pdf() throws Exception {
		if (docFile.exists()) {
			if (!pdfFile.exists()) {
				fromOffice = true;
				OpenOfficeConnection connection = new SocketOpenOfficeConnection(8100);
				try {
					connection.connect();
					DocumentConverter converter = new OpenOfficeDocumentConverter(connection);
					converter.convert(docFile, pdfFile);
					// close the connection
					connection.disconnect();
					System.out.println("****pdf转换成功，PDF输出：" + pdfFile.getPath() + "****");
				} catch (java.net.ConnectException e) {
					// ToDo Auto-generated catch block
					e.printStackTrace();
					System.out.println("****swf转换异常，openoffice服务未启动！****");
					throw e;
				} catch (com.artofsolving.jodconverter.openoffice.connection.OpenOfficeException e) {
					e.printStackTrace();
					System.out.println("****swf转换器异常，读取转换文件失败****");
					throw e;
				} catch (Exception e) {
					e.printStackTrace();
					throw e;
				}
			} else {
				fromOffice = false;// 重要！对于上传即是PDF文件，保留原文件
				System.out.println("****已经转换为pdf，不需要再进行转化****");
			}
		} else {
			System.out.println("****swf转换器异常，需要转换的文档不存在，无法转换****");
		}
	}

	/*
	 * 转换成swf
	 */
	private void pdf2swf() throws Exception {
		Runtime r = Runtime.getRuntime();
		if (!swfFile.exists()) {
			if (pdfFile.exists()) {
				{
					Process process = null;
					try {
						process = r.exec("C:/Program Files/SWFTools/pdf2swf.exe " + pdfFile.getPath() + " -o " + swfFile.getPath() + " -T 9");
						StreamReader err = new StreamReader(process.getErrorStream());
						StreamReader out = new StreamReader(process.getInputStream());
						err.start();
						out.start();
						int exitCode = process.waitFor();
						System.err.print(err.getMessage());
						System.out.print(out.getMessage());

						if (exitCode == 0) {
							System.out.println("****swf转换成功，文件输出：" + swfFile.getPath() + "****");
							FileUtils.movePath(swfFile.getParentFile().getPath(), SystemConstants.UPLOAD_FOLDER + FileUtils.getPre(insFileCoverting.getFileUrl()) + SWF_SUFFIX);
							System.out.println("****swf转存****");
							covertStatus = 2;
						} else {
							System.out.println("****swf转换失败！" + exitCode);
						}

						if (pdfFile.exists() && fromOffice) {
							pdfFile.delete();
						}
					} finally {
						if (process != null) {
							process.destroy();
						}
					}
				}
			} else {
				System.out.println("****pdf不存在，无法转换****");
			}
		} else {
			System.out.println("****swf已存在不需要转换****");
		}
	}

	private void doc2swf() {
		if (docFile.exists()) {
			try {
				if (!swfFile.exists()) {
					if (!pdfFile.exists()) {
						doc2pdf();
						pdf2swf();
					} else {
						fromOffice = false;// 重要标记
						pdf2swf();
						System.out.println("****已经转换为pdf，不需要再进行转化****");
					}
				} else {
					System.out.println("****已经转换为SWF，不需要再进行转化****");
				}
			} catch (Exception e) {
				e.printStackTrace();
				covertStatus = 3;
			}
			// 不管文件是否转换成功，都将原文件转存
			FileUtils.movePath(SystemConstants.UPLOAD_FOLDER_TEMP + insFileCoverting.getFileUrl(), SystemConstants.UPLOAD_FOLDER + insFileCoverting.getFileUrl());
			System.out.println("****原文件转存****");
		} else {
			System.out.println("****swf转换器异常，需要转换的文档不存在，无法转换****");
		}
	}

	static String loadStream(InputStream in) throws IOException {
		int ptr = 0;
		in = new BufferedInputStream(in);
		StringBuffer buffer = new StringBuffer();
		try {
			while ((ptr = in.read()) != -1) {
				buffer.append((char) ptr);
			}
		} catch (IOException e) {
			return buffer.toString();
		}
		return buffer.toString();
	}

	/*
	 * 转换线程
	 */
	public void run() {
		while (true) {
			while (insFileConvertList.size() > 0) {
				this.ini(insFileConvertList.removeFirst());
				if (swfFile.exists()) {
					System.out.println("****swf转换器开始工作，该文件已经转换为swf****");
				}

				// if (environment == 1) {
				// System.out.println("****swf转换器开始工作，当前设置运行环境windows****");
				// }

				doc2swf();
				insFileCoverting.setStatus(covertStatus);
				docConvertFinishRule.convertFinish(insFileCoverting);// 更新状态

				System.out.println("正在转换，队列中还有" + insFileConvertList.size() + "跟文件等待转换。");
			}
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String s[]) {
		/*
		*/
	}

}