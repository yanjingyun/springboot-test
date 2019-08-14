package com.yjy.test1.user.service;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.yjy.test1.user.dao.UserDao;
import com.yjy.test1.user.domain.User;
import com.yjy.test1.user.vo.UserQueryVo;

@Service
public class UserService {

	@Autowired
	private UserDao userDao;
	
	// 普通写法
	public List<User> getList(UserQueryVo vo) {
		return userDao.findAll(new Specification<User>() {
			private static final long serialVersionUID = 2807400572983838833L;
			@Override
			public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
				return builder.like(root.get("name"), '%' + vo.getName() + '%');
			}
		});
	}

	// 现在写法
	public List<User> getList2(UserQueryVo vo) {
		return userDao.findAll(vo.getSpecification());
	}

	// 现在写法-分页
	public Page<User> getPage(UserQueryVo vo) {
		return userDao.findAll(vo.getSpecification(), vo.getPageable());
	}
}
