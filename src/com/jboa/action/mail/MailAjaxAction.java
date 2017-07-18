package com.jboa.action.mail;

import java.io.File;
import java.util.Date;
import java.util.UUID;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.jboa.action.BaseAction;
import com.jboa.common.Pager;
import com.jboa.entity.Employee;
import com.jboa.entity.dto.AbstractMail;
import com.jboa.entity.dto.DetailMail;

@Controller("mailAjaxAction")
@Scope("prototype")

public class MailAjaxAction extends BaseAction{

	private static final long serialVersionUID = 4489779425172033364L;

	private DetailMail detailMail;
	private File upload;
	private String uploadContentType;
	private String uploadFileName;

	public File getUpload() {		return upload;	}
	public void setUpload(File upload) {		this.upload = upload;	}
	public String getUploadContentType() {		return uploadContentType;	}
	public void setUploadContentType(String uploadContentType) {		this.uploadContentType = uploadContentType;	}
	public String getUploadFileName() {		return uploadFileName;	}
	public void setUploadFileName(String uploadFileName) {		this.uploadFileName = uploadFileName;	}
	public DetailMail getDetailMail() {		return detailMail;	}
	public void setDetailMail(DetailMail detailMail) {		this.detailMail = detailMail;	}

	public String add (){
		try {
			if (getSYS_EMPId().equals(detailMail.getReceiverId())){
				logger.info("收件人不能为自己");
				resultMap.put("success", false);
				resultMap.put("msg", "发送失败，非法的收件人");
				return SUCCESS;
			}
			logger.debug("准备存储文件");
			if(upload != null){
				String filePath = ServletActionContext.getServletContext().getRealPath("upload");
				String fileName = UUID.randomUUID().toString().replaceAll("-", "") +
							uploadFileName.substring(uploadFileName.lastIndexOf("."));
				FileUtils.copyFile(upload, new File(filePath,fileName));
				detailMail.setFilename( fileName );
				logger.debug("文件存储完成,路径为：" + filePath);
			}
			logger.debug("已接收，获取的内容为：" + detailMail);
			Employee employee = (Employee) getAttributeFromSession("SYS_EMP");
			detailMail.setSenderId(employee.getEmpId());
			detailMail.setSendName(employee.getName());
			detailMail.setSendTime(new Date());
			logger.debug("待发送的邮件内容为：" + detailMail);
			mailService.save(detailMail);
			logger.debug("发送成功");
			resultMap.put("success", true);
			resultMap.put("msg", "保存成功");
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("发送邮件时发生异常为："+ e);
			resultMap.put("success", false);
			resultMap.put("msg", "发送失败，服务器错误，请稍后再试");
			return ERROR;
		}
		return SUCCESS;
	}
	
	private Pager<AbstractMail> pager;
	private String[] deleteIdList;

	public Pager<AbstractMail> getPager() {		return pager;	}
	public void setPager(Pager<AbstractMail> pager) {		this.pager = pager;	}
	public String[] getDeleteIdList() {		return deleteIdList;	}
	public void setDeleteIdList(String[] deleteIdList) {		this.deleteIdList = deleteIdList;	}

	public String delete() {
		logger.debug("待删除的邮件的ID为：" + deleteIdList);
		try {
			for (String id : deleteIdList) {
				mailService.delete(Integer.parseInt(id), getSYS_EMPId());
			}
			logger.debug("完成删除");
			resultMap.put("success", true);
			resultMap.put("msg", "删除成功");
		} catch (Exception e) {
			resultMap.put("success", false);
			resultMap.put("msg", "服务器错误，请稍后再试");
			logger.info("删除时发生异常" + e);
			e.printStackTrace();
			return ERROR;
		}
		return SUCCESS;
	}
	
	public String restore (){
		try {
			String mailId = getParameter("mailId");
			logger.debug("待还原的邮件ID为：" + mailId);
			mailService.update(Integer.parseInt(mailId), getSYS_EMPId());			
			logger.debug("复原完成");
			resultMap.put("success", true);
			resultMap.put("msg", "复原成功");
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("success", false);
			resultMap.put("msg", "服务器错误，请稍后再试");
			return ERROR;
		}
		return SUCCESS;
	}
	
	public String getUnreaded (){
		System.out.println("获取未读邮件数目");
		try {
			int empId = ((Employee)getAttributeFromSession("SYS_EMP")).getEmpId();
			Integer count = mailService.getUnreadCount(empId);
			resultMap.put("success", true);
			resultMap.put("totalCount", count);
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("msg", "服务器错误,暂时无法获取未读邮件数");
			resultMap.put("success", false);
		}
		return SUCCESS;
	}
	
	public String restoreSelected(){
		logger.debug("待恢复的邮件的ID为：" + deleteIdList);
		try {
			for (String id : deleteIdList) {
				mailService.update(Integer.parseInt(id), getSYS_EMPId());
			}
			logger.debug("完成恢复");
			resultMap.put("success", true);
			resultMap.put("msg", "恢复成功");
		} catch (Exception e) {
			resultMap.put("success", false);
			resultMap.put("msg", "服务器错误，请稍后再试");
			logger.info("恢复时发生异常" + e);
			e.printStackTrace();
			return ERROR;
		}
		return SUCCESS;
	}
	private Integer getSYS_EMPId ()throws Exception{
		Employee employee = (Employee) getAttributeFromSession("SYS_EMP");
		return employee.getEmpId();
	}
	
}
