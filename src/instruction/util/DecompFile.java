package instruction.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.LinkedList;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

/**
 * @author haojunhua 因DocConverter修改，本类有待更新，暂时不可以使用
 */
public class DecompFile extends Thread {
	public static enum EXT {
		ZIP, RAR, OTHER;
	}

	public static final String TEMP = "F:/uploadfile/temp/";

	private static DecompFile instance = null;
	private static LinkedList<String> fileStringList = new LinkedList<String>();

	private DecompFile() {

	}

	public static synchronized DecompFile getInstance() {
		if (instance == null) {
			instance = new DecompFile();
		}
		instance.start();
		return instance;
	}

	public void push(String fileString) {
		fileStringList.add(fileString);
	}

	public static String unZipFile(String fileString) {
		String newfilePath = "";
		try {
			org.apache.tools.zip.ZipFile zipFile = new org.apache.tools.zip.ZipFile(fileString);
			@SuppressWarnings("rawtypes")
			java.util.Enumeration e = zipFile.getEntries();
			org.apache.tools.zip.ZipEntry zipEntry = null;
			while (e.hasMoreElements()) {
				zipEntry = (org.apache.tools.zip.ZipEntry) e.nextElement();
				String fileName = zipEntry.getName();
				if (checkExt(FileUtils.getExt(fileName))) {
					// 判别后缀
					File f = new File(TEMP + File.separator + zipEntry.getName());
					f.createNewFile();
					InputStream in = zipFile.getInputStream(zipEntry);
					FileOutputStream out = new FileOutputStream(f);
					int c;
					byte[] by = new byte[1024];
					while ((c = in.read(by)) != -1) {
						out.write(by, 0, c);
					}
					out.close();
					in.close();
					newfilePath = shiftFile(fileString);
				}
			}
		} catch (Exception ex) {
		}
		return newfilePath;
	}

	public static String unZip(String fileString) {
		String newfilePath = "";
		File file = new File(fileString);// 压缩文件
		ZipFile zipFile;
		try {
			zipFile = new ZipFile(file);
			// 实例化ZipFile，每一个zip压缩文件都可以表示为一个ZipFile
			// 实例化一个Zip压缩文件的ZipInputStream对象，可以利用该类的getNextEntry()方法依次拿到每一个ZipEntry对象
			ZipInputStream zipInputStream = new ZipInputStream(new FileInputStream(file));
			ZipEntry zipEntry = null;
			while ((zipEntry = zipInputStream.getNextEntry()) != null) {
				String fileName = zipEntry.getName();
				if (checkExt(FileUtils.getExt(fileName))) {
					File temp = new File(TEMP + fileName);
					OutputStream os = new FileOutputStream(temp);
					// 通过ZipFile的getInputStream方法拿到具体的ZipEntry的输入流
					InputStream is = zipFile.getInputStream(zipEntry);
					int len = 0;
					while ((len = is.read()) != -1)
						os.write(len);
					os.close();
					is.close();
				}

			}
			newfilePath = shiftFile(fileString);
			zipInputStream.close();
		} catch (ZipException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return newfilePath;
	}

	/**
	 * 解压RAR文件
	 * 
	 */

	public static String unRarFile(String fileString) {
		String newfilePath = "";
		Runtime r = Runtime.getRuntime();
		Process process = null;
		try {
			// 系统安装winrar的路径
			String cmd = "C:\\Program Files\\Unrar\\UnRAR.exe";
			String unrarCmd = cmd + " x -o+ -y " + fileString + " " + TEMP;
			System.out.println(unrarCmd);
			process = r.exec(unrarCmd);
			StreamReader err = new StreamReader(process.getErrorStream());
			StreamReader out = new StreamReader(process.getInputStream());
			err.start();
			out.start();
			int exitCode = process.waitFor();
			System.out.print(err.getMessage());
			System.err.print(out.getMessage());
			if (exitCode == 0) {
				System.err.println("RAR解压成功");
				newfilePath = shiftFile(fileString);
			} else {
				System.out.println("解压失败！" + exitCode);
			}

		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (process != null) {
				process.destroy();
			}
		}
		return newfilePath;
	}

	public static String shiftFile(String fileString) {
		String newfilePath = "";
		File TempFile = new File(TEMP);
		File firstFile = findFirstFile(new File(TEMP));
		if (firstFile != null) {
			String sroucePath = firstFile.getPath();
			String targetPath = FileUtils.getPre(fileString) + "." + FileUtils.getExt(sroucePath);
			FileUtils.copyPath(sroucePath, targetPath);
			newfilePath = targetPath;
			for (File file : TempFile.listFiles()) {
				FileUtils.deleteDir(file);
			}
		}
		return newfilePath;
	}

	public static File findFirstFile(File file) {
		if (file.isFile() && checkExt(FileUtils.getExt(file.getName())))
			return file;
		else {
			File fileList[] = file.listFiles();
			for (File _file : fileList) {
				if (_file.isFile()) {
					if (checkExt(FileUtils.getExt(_file.getName()))) {
						return _file;
					}
				} else {
					if (findFirstFile(_file) != null)
						return findFirstFile(_file);
				}
			}
		}
		return null;
	}

	public static EXT judgeExt(String fileString) {
		String fileExt = FileUtils.getExt(fileString);
		if (fileExt.equals("rar"))
			return EXT.RAR;
		else if (fileExt.equals("zip"))
			return EXT.ZIP;
		return EXT.OTHER;
	}

	public static boolean checkExt(String ext) {
		if (ext != null) {
			if (ext.equals("pdf") || ext.equals("doc") || ext.equals("docx") || ext.equals("ppt") || ext.equals("pptx"))
				return true;
		}
		return false;
	}

	/**
	 * 线程运行
	 */
	@SuppressWarnings("unused")
	public void run() {
		while (true) {
			while (fileStringList.size() > 0) {
				String fileString = fileStringList.removeFirst();
				String newfilePath = "";
				if (judgeExt(fileString) == EXT.RAR) {
					newfilePath = unRarFile(fileString);
					// 尚未处理
				} else if (judgeExt(fileString) == EXT.ZIP) {
					newfilePath = unZipFile(fileString);
				}
				// 这里因为docConverter更新，已不能使用
				// SystemConstants.docConverter.push(newfilePath);
			}
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		DecompFile decompFile = DecompFile.getInstance();
		decompFile.push("D:/x.zip");
		decompFile.push("D:/xx.rar");
		// File file = DecompFile.findFirstFile(new File(TEMP));
		// System.out.println(file.getName());
	}
}
