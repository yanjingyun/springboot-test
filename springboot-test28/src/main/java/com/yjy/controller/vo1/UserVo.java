package com.yjy.controller.vo1;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

/**
 * 测试嵌套对象校验
 */
public class UserVo {

	@NotNull
	@Length(min = 3, max = 10, message = "名称不能为空且长度为3~10之间")
	private String name;

	@NotNull(message = "手机号不能为空")
	private String mobilePhone;
	
	@Valid
	@NotNull(message = "地址对象不能为空")
	private Adress adress;

	public static class Adress {
		
		@NotNull
		@Length(min = 3, max = 10, message = "地址不能为空且长度为3~10之间")
		private String name;

		public void setName(String name) {
			this.name = name;
		}

		public String getName() {
			return name;
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMobilePhone() {
		return mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	public Adress getAdress() {
		return adress;
	}

	public void setAdress(Adress adress) {
		this.adress = adress;
	}

}
