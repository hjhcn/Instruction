package instruction.action.admin;

import instruction.model.BBSThread;
import instruction.service.BBSThreadService;

import java.util.ArrayList;
import java.util.List;

public class BBSThreadAdminAction extends BaseAdminAction {

	private static final long serialVersionUID = -4331385263204673922L;
	private BBSThreadService bbsThreadService;
	private List<BBSThread> bbsThreads;
	private int id;
	private String inputArrayStr;
    private String link;
    private String title;
    private int order;

	public String list() {
		bbsThreads=bbsThreadService.findAll();
		return SUCCESS;
	}
	
	public String delete(){
		feedback=bbsThreadService.delete(id);
		return SUCCESS;
	}
	
	public String addPage() {
		return SUCCESS;
	}
	
	public String add(){
		feedback=bbsThreadService.add(title,link,order);
		return SUCCESS;
	}
	
	public String edit(){
		List<BBSThread> bbsThreads=new ArrayList<BBSThread>();
		String[] threads=inputArrayStr.split("\\|");
		for(String thread:threads){
			String[] threadSplit=thread.split(",");
			if(threadSplit.length==4){
				try {
					BBSThread bbsThread=new BBSThread();
					bbsThread.setId(Integer.parseInt(threadSplit[0]));
					bbsThread.setTitle(threadSplit[1]);
					bbsThread.setLink(threadSplit[2]);
					bbsThread.setOrder(Integer.parseInt(threadSplit[3]));
					bbsThreads.add(bbsThread);
				} catch (NumberFormatException e) {
					e.printStackTrace();
				}
			}
		}
		feedback=bbsThreadService.edit(bbsThreads);
		return SUCCESS;
	}

	public BBSThreadService getBbsThreadService() {
		return bbsThreadService;
	}

	public void setBbsThreadService(BBSThreadService bbsThreadService) {
		this.bbsThreadService = bbsThreadService;
	}

	public List<BBSThread> getBbsThreads() {
		return bbsThreads;
	}

	public void setBbsThreads(List<BBSThread> bbsThreads) {
		this.bbsThreads = bbsThreads;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getFeedback() {
		return this.feedback;
	}

	public void setInputArrayStr(String inputArrayStr) {
		this.inputArrayStr = inputArrayStr;
	}

	public String getInputArrayStr() {
		return inputArrayStr;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getOrder() {
		return order;
	}

	public void setOrder(int order) {
		this.order = order;
	}
	
}
