package action.video;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Timestamp;
import java.util.Date;

import org.apache.struts2.ServletActionContext;

import service.BaseService;

import bean.Category;
import bean.Configure;
import bean.Video;
import bean.Videostate;

import com.opensymphony.xwork2.ActionSupport;
/**
 * @author ������
 * �����Ƶ��Action
 */
public class VideoAdd extends ActionSupport {
	private static final int FILE_SIZE=16*1024;
	private String name;
	private String intro;
	private File videofile;
	private String videofileFileName;
	private String videofileContentType;
	private BaseService baseService;
	private int islive;
	private String url;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getIntro() {
		return intro;
	}
	public void setIntro(String intro) {
		this.intro = intro;
	}
	public BaseService getBaseService() {
		return baseService;
	}
	public void setBaseService(BaseService baseService) {
		this.baseService = baseService;
	}
	public File getVideofile() {
		return videofile;
	}
	public void setVideofile(File videofile) {
		this.videofile = videofile;
	}
	public String getVideofileFileName() {
		return videofileFileName;
	}
	public void setVideofileFileName(String videofileFileName) {
		this.videofileFileName = videofileFileName;
	}
	public String getVideofileContentType() {
		return videofileContentType;
	}
	public void setVideofileContentType(String videofileContentType) {
		this.videofileContentType = videofileContentType;
	}
	public int getIslive() {
		return islive;
	}
	public void setIslive(int islive) {
		this.islive = islive;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	/**
	 * д���ļ�ʱ���õ��ĺ���
	 * @param source Դ�ļ�
	 * @param target Ŀ���ļ�
	 */
	 public void upLoadFile(File source,File target){
		  InputStream in=null;
		  OutputStream out=null;
			try{
			in=new BufferedInputStream(new FileInputStream(source),FILE_SIZE);
			out=new BufferedOutputStream(new FileOutputStream(target),FILE_SIZE);
			//in-->image-->out
			byte[] buffer=new byte[FILE_SIZE];
			while(in.read(buffer)>0){
				out.write(buffer);
			}
		  }catch(IOException ex){
			  ex.printStackTrace();
		  }finally{
		   try{
			    in.close();
			    out.close();
			}catch(IOException ex){
				ex.printStackTrace();
			}
		}
	}
	 
	public String execute(){
		try{
			int order=1;
			Video video=new Video();
			video.setName(name);
			video.setIntro(intro);
			video.setEdittime(new Timestamp(new Date().getTime()));
 			Configure folder_videoori_cfg=(Configure) baseService.ReadSingle("Configure", "name", "folder_videoori");
			Configure folder_thumbnail_cfg=(Configure) baseService.ReadSingle("Configure", "name", "folder_thumbnail");
			if(islive==0){
				//�㲥
				String oriurl=folder_videoori_cfg.getVal()+"/"+videofileFileName;
				video.setOriurl(oriurl);
				Category category=(Category) baseService.ReadSingle("Category", "id", 1);
				video.setCategory(category);
				//״̬���ã��ȴ��ϴ�
				Videostate videostate=(Videostate) baseService.ReadSingle("Videostate", "order", order);
				video.setVideostate(videostate);
				video.setIslive(0);
				//Default Thumbnail
				String defaultthumbnail=folder_thumbnail_cfg.getVal()+"/default.jpg";
				video.setThumbnailurl(defaultthumbnail);
				baseService.save(video);
				
				//�ϴ���Ƶ�ļ�
				String realfileoriDir=ServletActionContext.getServletContext().getRealPath(folder_videoori_cfg.getVal()).replace('\\', '/');
				//Check
				File realfileoriDirFile =new File(realfileoriDir);
				if(!realfileoriDirFile.exists()  && !realfileoriDirFile.isDirectory()){
					System.out.println("Directory not exist. Create it.");  
					System.out.println(realfileoriDirFile);  
					realfileoriDirFile.mkdir();
				}
				String realfileoriPath=realfileoriDir+"/"+videofileFileName;
				File targetFile=new File(realfileoriPath);
				upLoadFile(videofile,targetFile);
				//�ȴ��ͼ
				videostate=(Videostate) baseService.ReadSingle("Videostate", "order", order+1);
				video.setVideostate(videostate);
				baseService.update(video);
			}else{
				//ֱ��
				Category category=(Category) baseService.ReadSingle("Category", "id", 2);
				video.setCategory(category);
				video.setUrl(url);
				video.setIslive(1);
				//�ȴ��ͼ
				Videostate videostate=(Videostate) baseService.ReadSingle("Videostate", "order", order+1);
				video.setVideostate(videostate);
				//Default Thumbnail
				String defaultthumbnail=folder_thumbnail_cfg.getVal()+"/default.jpg";
				video.setThumbnailurl(defaultthumbnail);
				
				baseService.save(video);
			}
			
			return SUCCESS;
		}
		catch(Exception ex){
			ex.printStackTrace();
			return ERROR;
		}
	}
}
