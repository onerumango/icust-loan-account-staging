package com.rumango.enums;

import com.google.common.base.Strings;

public enum LoanAccStatusEnum {

	BACKLOG(1, "BACKLOG"), 
	ONGOING(2, "ONGOING"),
	APPROVED(3, "APPROVED"),
	REJECTED(4, "REJECTED");
	
	Integer code;
	String name;
	
	LoanAccStatusEnum(Integer code, String name){
		this.code = code;
		this.name= name;
	}
	
	public static Boolean isExists(String name) {
		if (Strings.isNullOrEmpty(name)) {
			return false;
		}
		for (LoanAccStatusEnum status : LoanAccStatusEnum.values()) {
			if (name.equals(status.name)) {
				return true;
			}
		}

		return false;
	}
}
