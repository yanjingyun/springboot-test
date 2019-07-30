package com.yjy.security.domain;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.yjy.core.domain.IdEntity;

/**
 * 菜单表
 */
@Entity
@Table(name = "sec_module")
public class Module extends IdEntity {

	private static final long serialVersionUID = 2516570620810479963L;
	
	public static final Comparator<Module> comparator = new Comparator<Module>() {
		@Override
		public int compare(Module m1, Module m2) {
			return m1.getTreePath().compareTo(m2.getTreePath());
		}
	};
	
	/**
	 * 模块名称
	 */
	private String name;
	
	/**
	 * 菜单URL
	 */
	private String menuUrl;

	/**
	 * 树编码，3位一层
	 * 一级菜单（001）
	 * 二级菜单（001001）
	 * 三级菜单（001001001）
	 */
	private String treePath;
	
	/**
	 * 父模块
	 */
	@Transient
	private Module parent;
	
	/**
	 * 下一级模块
	 */
	@Transient
	private Set<Module> children = new TreeSet<Module>(comparator);
	
	@ManyToMany(mappedBy = "modules")
	private List<Role> roles = new ArrayList<Role>();


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMenuUrl() {
		return menuUrl;
	}

	public void setMenuUrl(String menuUrl) {
		this.menuUrl = menuUrl;
	}

	public String getTreePath() {
		return treePath;
	}

	public void setTreePath(String treePath) {
		this.treePath = treePath;
	}

	public Module getParent() {
		return parent;
	}

	public void setParent(Module parent) {
		this.parent = parent;
	}

	public Set<Module> getChildren() {
		return children;
	}

	public void setChildren(Set<Module> children) {
		this.children = children;
	}
	
	public List<Role> getRoles() {
		return roles;
	}
	
	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
}
