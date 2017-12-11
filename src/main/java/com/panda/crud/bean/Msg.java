package com.panda.crud.bean;

import java.util.HashMap;
import java.util.Map;

/**
 * 通用的返回类
 * 
 */
public class Msg {
	
	private int code;//状态码   100 成功   200失败
	
	private String msg;//提示信息
	
	private Map<String, Object> extend = new HashMap<String, Object>();;//用户要返回给浏览器的数据

	public Msg() {
            super();
            // TODO Auto-generated constructor stub
        }

        public Msg(int code, String msg) {
            super();
            this.code = code;
            this.msg = msg;
        }

        public static Msg success(){
		return new Msg(100, "处理成功！");
	}
	
	public static Msg fail(){
		return new Msg(200, "处理失败！");
	}
	
	public Msg add(String key,Object value){
		this.getExtend().put(key, value);
		return this;
	}
	
	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Map<String, Object> getExtend() {
		return extend;
	}

	public void setExtend(Map<String, Object> extend) {
		this.extend = extend;
	}
	//测试github功能 
}
