/**  
 * @Title: ResultDataBean.java
 * @Package com.ehome.webapp.bean
 * @ClassName: ResultDataBean
 * @Description: 数据结果组件，用于向页面返回数据
 * @author xiaolei-0228@163.com
 * @date 2014年3月8日 下午3:28:11
 * @version V1.0  
 */ 
package com.ehome.webapp.bean;

import java.io.Serializable;

public class ResultDataBean implements Serializable {
	private static final long serialVersionUID = 5933106683129425849L;
	
	private String errCode;    // 错误代码 （方便开发人员分析问题）
	private String errMsg;		// 错误提示信息 （向用户展示用）
	private Object result;		// 数据结果
	
	private Paging paging;		// 分页信息
	
	
	/**********************************************************************************************/
    public String getErrCode() {
        return errCode == null ? "" : errCode;
    }

    public void setErrCode(String errCode) {
        this.errCode = errCode;
    }

    public String getErrMsg() {
        return errMsg == null ? "" : errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    public Object getResult() {
        return result == null ? "" : result;
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
