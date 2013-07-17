package instruction;

import instruction.model.Daren;
import instruction.util.DecompFile;
import instruction.util.DocConverter;

import java.util.ArrayList;
import java.util.List;

public class SystemConstants {
	public static final String USER_SESSION = "user.session";
	public static final String USER_COOKIE = "user.cookie";
	public static final String ADMIN_SESSION = "admin";

	public static final String UPLOAD_FOLDER_TEMP = "G:/uploadfileTemp/";
	// public static final String UPLOAD_FOLDER_TEMP =
	// "/Users/haojunhua/Workspaces/J2EE project/uploadfileTemp/";

	public static final String UPLOAD_FOLDER = "//192.168.0.137/ins_uploadfile/";
	// public static final String UPLOAD_FOLDER =
	// "/Users/haojunhua/Workspaces/J2EE project/ins_uploadfile/";
	public static final String UPLOAD_FOLDER_SERVER = "server137";

	public static final String OLD_UPLOAD_FOLDER = "F:/uploadfile/";
	public static final String OLD_UPLOAD_FOLDER_SERVER = "server138";

	public static final String DATA_TO_EXCEL_FOLDER = "F:/dataToExcel/";

	public static final String DEFAULT_PIC = "default.png";

	public static final int UPLOAD_BRAND_OTHER = -12345;

	public static final int OPENING_TIMESTAMP = 1262304000;// 2010-01-01

	public static final class STATUS {
		public static final short PASS = 1;
		public static final short UNVERIFY = 0;
		public static final short DELETED = -1;
		public static final short SHOWALL = -100;
	}

	public static final class COMMENT_CREDIT_TYPE {
		public static final short UNVERIFY = 0;
		public static final short NO = 1;
		public static final short YES = 2;
		public static final short CREDIT_THRESHOLD = 3;
		public static final short SHOWALL = -100;
	}

	public enum OPERATE {
		ADMIN_UPLOAD, USER_UPLOAD, EDIT, DELETE, VERIFY, UNVERIFY;
	}

	public static final class FEEDBACK {
		public static final int SUCCESS = 100;

		public static final int LOGIN_ERROR = -101;
		public static final int UID_ERROR = -102;// 用户Uid不存在

		public static final int USER_UNLOGIN_ERROR = -201;// 未登录错误
		public static final int USER_TOKEN_ERROR = -202;// 用户token错误
		public static final int USER_LOGINNAME_PSWD_ERROR = -203;// 登陆用户名密码错
		public static final int USER_API_ERROR = -204;// 登陆用户名密码错

		public static final int IID_ERROR = -301;// 说明书id不存在

		public static final int COMMENT_GRADE_ERROR = -401;
		public static final int COMMENT_CONTENT_ERROR = -402;

		public static final int ADMIN_UNLOGIN_ERROR = -501;// 未登录错误
		public static final int ADMIN_USERNAME_NOTEXSIT = -502;// 管理用户名不存在
		public static final int ADMIN_LOGIN_PASW_ERROR = -503;// 登录密码错失败
		public static final int ADMIN_UID_NOTEXSIT = -504;// 管理员Uid不存在
		public static final int ADMIN_NEW_PASW_ERROR = -505;// 新密码格式错

		public static final int INS_ID_NOEXSIT = -601;
		public static final int INS_TITLE_EXSIT = -602;
		public static final int INS_TITLE_ILLEGAL = -603;
		public static final int INS_CATEGORY_ILLEGAL = -604;
		public static final int INS_BRAND_ILLEGAL = -605;
		public static final int INS_ICON_ILLEGAL = -606;
		public static final int INS_FILE_ILLEGAL = -607;
		public static final int INS_MODEL_ILLEGAL = -608;

		public static final int CATEGORY_CID_NOEXSIT = -701;// 分类CID不存在
		public static final int CATEGORY_NAME_EXSIT = -702;// 分类名称已存在

		public static final int BRAND_BID_NOEXSIT = -801;
		public static final int BRAND_NAME_EXSIT = -802;

		public static final int CREDIT_RULE_ID_NOEXSIT = -901;
		public static final int CREDIT_EXEED_THRESHOLD = -902;
		public static final int CREDIT_THRESHOLD_ILLEGAL = -903;// 积分和阀值冲突，必须同正同负
		public static final int CREDIT_INS_HAVEADD = -904;//
		public static final int CREDIT_0 = -905;//

		public static final int BBSTHREAD_TITLE_ERROR = -1001;
		public static final int BBSTHREAD_LINK_ERROR = -1002;
		public static final int BBSTHREAD_ORDER_ERROR = -1003;
		public static final int BBSTHREAD_NULL_ERROR = -1004;

		public static final int NEWS_TITLE_ERROR = -1101;
		public static final int NEWS_CONTENT_ERROR = -1102;
		public static final int NEWS_DESCRIPTION_ERROR = -1103;
		public static final int NEWS_ID_NOEXSIT = -1104;

		public static final int UPLOADFILE_ID_NOEXSIT = -1201;
		public static final int UPLOADFILE_NOT_BELONG_TO_USER = -1202;
		public static final int UPLOADFILE_TYPE_ERROR = -1203;

		public static final int TRAVELSIGN_NULL_ERROR = -1301;
		public static final int TRAVELSIGN_TYPE_ERROR = -1302;
		public static final int TRAVELSIGN_NAME_ERROR = -1303;
		public static final int TRAVELSIGN_IDCARDNO_ERROR = -1304;
		public static final int TRAVELSIGN_PHONE_ERROR = -1305;
		public static final int TRAVELSIGN_ENROLLMENT_ERROR = -1306;
		public static final int TRAVELSIGN_PLATENUMBER_ERROR = -1307;
		public static final int TRAVELSIGN_YEAR_ERROR = -1308;
	}

	public enum TRAVEL_SIGN_TYPE {
		CAR, BIK, OTR;
	}

	public enum CREDIT_TYPE {
		ALL, ADD, CUT;
	}

	public static final String[] ACCEPTED_PIC_EXT = { "jpg", "gif", "png", "bmp", "jpeg" };
	public static final String[] ACCEPTED_DOC_EXT = { "pdf", "doc", "docx", "ppt", "pptx", "xls",
			"xlsx" };
	public static final String[] ACCEPTED_SWF_EXT = { "swf", "flv" };

	public enum EXT_TYPE {
		ALL, PIC, DOC, SWF;
	}

	public static final List<Daren> darens = new ArrayList<Daren>() {
		private static final long serialVersionUID = -4521481357143405250L;
		{
			add(new Daren(48469, "sherryxu"));
			add(new Daren(687092, "眼中的世界"));
			add(new Daren(1984618, "138xxxx3113p"));
			add(new Daren(554708, "ydsc5851585918c"));
			add(new Daren(4712182, "吴家碧玉"));
			add(new Daren(4707159, "末日重生"));
			add(new Daren(3408283, "151xxxx6396dm"));
			add(new Daren(805297, "159XXXX9526"));
			add(new Daren(4827452, "oomph_sun"));
			add(new Daren(4796643, "187life26809642"));

			// add(new Daren(4726800, "ｐāssion"));
			// add(new Daren(4765630, "帝君"));
			// add(new Daren(1452864, "幸福小猪"));

			// add(new Daren(3321017, "187xxxx6946d"));
			// add(new Daren(1264, "sunnybt"));

			// add(new Daren(155259, "jiaren"));
			// add(new Daren(4765630, "帝君"));
			// add(new Daren(59444, "zhoujunchao"));

			// add(new Daren(161460, "lann"));
			// add(new Daren(241850, "蕙兰"));
			// add(new Daren(689596, "ydsc3812790520c"));
			// add(new Daren(3241433, "138xxxx8313hd"));

			// add(new Daren(14371, "pallas"));
			// add(new Daren(1803733, "158****37596"));
			// add(new Daren(1452864, "幸福小猪"));
			// add(new Daren(4245295, "m65335108"));
			// add(new Daren(1984404, "Reeves"));
			// add(new Daren(2538475, "152xxxx7712n"));
		}
	};

	public static final DocConverter docConverter = DocConverter.getInstance();
	public static final DecompFile decompFile = DecompFile.getInstance();
	public static int UPLOAD_CLASH_COUNTING = 0;
}
