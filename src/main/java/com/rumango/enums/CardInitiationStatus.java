package com.rumango.enums;

import com.google.common.base.Strings;

public enum CardInitiationStatus {

	BACKLOG(1, "BACKLOG"), 
	ONGOING(2, "ONGOING"),
	APPROVED(3, "APPROVED"),
	REJECTED(4, "REJECTED");
	
	Integer code;
	String name;
	
	CardInitiationStatus(Integer code, String name){
		this.code = code;
		this.name= name;
	}
	
	public static Boolean isExists(String name) {
		if (Strings.isNullOrEmpty(name)) {
			return false;
		}
		for (CardInitiationStatus status : CardInitiationStatus.values()) {
			if (name.equals(status.name)) {
				return true;
			}
		}

		return false;
	}
}
