package instruction.service.impl;

import instruction.SystemConstants;
import instruction.dao.AdminDao;
import instruction.model.Admin;
import instruction.model.InsOrderBy;
import instruction.model.LoginAdmin;
import instruction.service.AdminService;
import instruction.util.MD5;
import instruction.util.Time;
import instruction.util.page.PageView;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

public class AdminServiceImpl implements AdminService {
	private AdminDao adminDao;

	public String dataExport(Admin admin, String tableName, String filterStr) {
		if (tableName == null)
			return "";

		try {
			// 打开文件
			WritableWorkbook book = Workbook
					.createWorkbook(new File(admin.getUsername() + "测试.xls"));
			// 生成名为“第一页”的工作表，参数0表示这是第一页
			WritableSheet sheet = book.createSheet(tableName, 0);
			// 在Label对象的构造子中指名单元格位置是第一列第一行(0,0)
			// 以及单元格内容为test
			Label label = new Label(0, 0, "test");
			// 将定义好的单元格添加到工作表中
			sheet.addCell(label);
			/*
			 * 生成一个保存数字的单元格 必须使用Number的完整包路径，否则有语法歧义 单元格位置是第二列，第一行，值为789.123
			 */
			jxl.write.Number number = new jxl.write.Number(1, 0, 789.123);
			sheet.addCell(number);
			// 写入数据并关闭文件
			book.write();
			book.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		return "";
	}

	public Admin getAdminByUid(int uid) {
		return adminDao.get(uid);
	}

	public LoginAdmin login(String username, String password, String ip) {
		Admin admin = adminDao.getByUsername(username);
		LoginAdmin loginAdmin = new LoginAdmin();
		int feedback = SystemConstants.FEEDBACK.SUCCESS;
		MD5 md5 = new MD5();
		if (admin != null) {
			if (!md5.getMD5ofStr(password).equals(admin.getPassword()))
				feedback = SystemConstants.FEEDBACK.ADMIN_LOGIN_PASW_ERROR;
			else {
				admin.setLastTime(admin.getLoginTime());
				admin.setLastIp(admin.getLoginIp());
				admin.setLoginTime(Time.getTimeStamp());
				admin.setLoginIp(ip);
				adminDao.saveOrUpdate(admin);
			}
		} else
			feedback = SystemConstants.FEEDBACK.ADMIN_USERNAME_NOTEXSIT;

		loginAdmin.setAdmin(admin);
		loginAdmin.setFeedback(feedback);
		return loginAdmin;
	}

	public PageView<Admin> findScrollData(String search, int page, int rows, String order,
			String sort) {
		ArrayList<String> whereClause = new ArrayList<String>();
		if (search != null && !"".equals(search)) {
			whereClause.add("title like '%" + search + "%'");
		}
		PageView<Admin> admins = new PageView<Admin>(page, rows);
		LinkedHashMap<String, String> orderbyClause = new LinkedHashMap<String, String>();
		if (InsOrderBy.include(sort)) {
			orderbyClause.put(sort, order);
		}
		admins.setQueryResult(adminDao.findScrollData(page, rows, whereClause, orderbyClause));
		return admins;
	}

	public void setAdminDao(AdminDao adminDao) {
		this.adminDao = adminDao;
	}

	public int delete(int uid) {
		Admin admin = this.getAdminByUid(uid);
		if (admin != null) {
			adminDao.delete(admin);
			return SystemConstants.FEEDBACK.SUCCESS;
		} else
			return SystemConstants.FEEDBACK.ADMIN_UID_NOTEXSIT;
	}

	public int changePassword(int uid, String OldPassword, String newPassword) {
		if (!checkPassword(newPassword))
			return SystemConstants.FEEDBACK.ADMIN_NEW_PASW_ERROR;
		Admin admin = adminDao.getByUid(uid);
		if (admin == null)
			return SystemConstants.FEEDBACK.ADMIN_UID_NOTEXSIT;
		if (!admin.getPassword().equals(new MD5().getMD5ofStr(OldPassword)))
			return SystemConstants.FEEDBACK.ADMIN_LOGIN_PASW_ERROR;
		else {
			admin.setPassword(new MD5().getMD5ofStr(newPassword));
			adminDao.saveOrUpdate(admin);
			return SystemConstants.FEEDBACK.SUCCESS;
		}
	}

	public boolean checkPassword(String password) {
		if (password != null && password.length() >= 6 && password.length() <= 16)
			return true;
		else
			return false;
	}

}
