package com.yjy.core.entity;

public class VersionEntity extends IdEntity {

	private static final long serialVersionUID = 4975607019709340776L;

	private int versionNumber;

	public int getVersionNumber() {
		return versionNumber;
	}

	public void setVersionNumber(int versionNumber) {
		this.versionNumber = versionNumber;
	}
}
