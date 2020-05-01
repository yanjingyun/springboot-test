package com.yjy.core.domain;

import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

@MappedSuperclass
public abstract class VersionEntity extends IdEntity {

	private static final long serialVersionUID = -6797102800369252249L;

	/**版本号*/
	@Version
	private int versionNumber;
	
	public int getVersionNumber() {
		return versionNumber;
	}
	
	public void setVersionNumber(int versionNumber) {
		this.versionNumber = versionNumber;
	}
}
