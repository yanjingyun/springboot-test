package com.yjy.core.entity;

import com.baomidou.mybatisplus.annotation.Version;

public class VersionEntity extends IdEntity {

	private static final long serialVersionUID = 6365561473207360832L;

	/** 版本号 */
	@Version
	private int versionNumber;

	public int getVersionNumber() {
		return versionNumber;
	}

	public void setVersionNumber(int versionNumber) {
		this.versionNumber = versionNumber;
	}

	@Override
	public String toString() {
		return super.toString() + " VersionEntity [versionNumber=" + versionNumber + "]";
	}
}
