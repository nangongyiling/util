package com.zp.test.util.file;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.StrutsStatics;

import com.google.gson.Gson;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.zpark.shop.util.FileBrowserUtil;


public class ImageBrowserAction extends ActionSupport{
	private String path;
	private String type;
	private File image;
	private String imageFileName;
	
	public String browser(){
		HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(StrutsStatics.HTTP_REQUEST);
		try {
			String jsons=FileBrowserUtil.getFiles(request.getSession().getServletContext().getRealPath("/uploadFolder"+path),request.getContextPath(), request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort());
			HttpServletResponse response =(HttpServletResponse) ActionContext.getContext().get(StrutsStatics.HTTP_RESPONSE);
			response.setCharacterEncoding("utf-8");
			response.setContentType("text/json;charset=utf-8");
			PrintWriter out = response.getWriter();
			out.write(jsons);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return NONE;
	}
	public String uploadImage(){
		Map<String,Object> map=new HashMap<String, Object>();
		Map<String, String> message=new HashMap<String, String>();
		try {
			HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(StrutsStatics.HTTP_REQUEST);
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
			String dateDir=simpleDateFormat.format(new Date());
			String path=request.getSession().getServletContext().getRealPath("/uploadFolder")+"/"+dateDir;
			File fileDir=new File(path);
			if(!fileDir.exists()){
				fileDir.mkdirs();
			}
			FileUtils.copyFile(image, new File(path+File.separator+imageFileName));
			message.put("type", "success");
			String url= request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();
			map.put("url",url+"/uploadFolder/"+dateDir+File.separator+imageFileName);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			message.put("type", "error");
			e.printStackTrace();
		}
		map.put("message", message);
		try {
			String jsons=new Gson().toJson(map);
			HttpServletResponse response =(HttpServletResponse) ActionContext.getContext().get(StrutsStatics.HTTP_RESPONSE);
			response.setCharacterEncoding("utf-8");
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();
			out.write(jsons);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return NONE;
	}
	
	
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public File getImage() {
		return image;
	}
	public void setImage(File image) {
		this.image = image;
	}
	public String getImageFileName() {
		return imageFileName;
	}
	public void setImageFileName(String imageFileName) {
		this.imageFileName = imageFileName;
	}
	
}
