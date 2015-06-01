package action.video;

import java.util.List;

import service.BaseService;

import bean.Video;
import com.opensymphony.xwork2.ActionSupport;
/**
 * @author 雷霄骅
 * 根据指定顺序读取视频信息Action
 */
public class VideoReadLimitedByOrder extends ActionSupport {
	private int num;
	private BaseService baseService;
	private List<Video> resultvideo;

	public BaseService getBaseService() {
		return baseService;
	}
	public void setBaseService(BaseService baseService) {
		this.baseService = baseService;
	}
	
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public List<Video> getResultvideo() {
		return resultvideo;
	}
	public void setResultvideo(List<Video> resultvideo) {
		this.resultvideo = resultvideo;
	}
	public String execute(){
		try{
			resultvideo= baseService.ReadLimitedByOrder("Video", "edittime", num, "asc");
			return SUCCESS;
		}
		catch(Exception ex){
			ex.printStackTrace();
			return ERROR;
		}
	}
}
