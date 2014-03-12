/**  
 * @Title: ResultDataBean.java
 * @Package com.ehome.webapp.bean
 * @ClassName: ResultDataBean
 * @Description: 数据结果组件，用于向页面返回数据
 * @author xiaolei-0228@163.com
 * @date 2014年3月8日 下午3:28:11
 * @version V1.0  
 */ 
package com.xiaolei.webapp.bean;

import java.io.Serializable;

public class ResultDataBean implements Serializable {
	private static final long serialVersionUID = 5933106683129425849L;
	
	private String error;		// 错误代码 （方便开发人员分析问题）
	private String message;		// 错误提示信息 （向用户展示用）
	private Object result;		// 数据结果
	
	private Paging paging;		// 分页信息
	
	
	/**********************************************************************************************/
	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getResult() {
		return result;
	}

	public void setResult(Object result) {
		this.result = result;
	}

	public Paging getPaging() {
		return paging;
	}

	public void setPaging(Paging paging) {
		this.paging = paging;
	}
	
	
}
