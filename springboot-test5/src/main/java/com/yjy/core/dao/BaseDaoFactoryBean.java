package com.yjy.core.dao;

import java.io.Serializable;

import javax.persistence.EntityManager;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactory;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactoryBean;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.data.repository.core.RepositoryInformation;
import org.springframework.data.repository.core.RepositoryMetadata;
import org.springframework.data.repository.core.support.RepositoryFactorySupport;

import com.yjy.core.entity.IdEntity;


/**步骤四：自定义BaseDaoFactoryBean来代替默认的RepositoryFactoryBean
 * RepositoryFactoryBean负责返回一个RepositoryFactory
 * Spring Data Jpa 将使用RepositoryFactory来创建Repository具体实现，我们用BaseDaoImpl代替SimpleJpaRepository
 */
public class BaseDaoFactoryBean<R extends JpaRepository<T, ID>, T extends IdEntity, ID extends Serializable>
		extends JpaRepositoryFactoryBean<R, T, ID> {
	
	public BaseDaoFactoryBean(Class<? extends R> repositoryInterface) {
		super(repositoryInterface);
	}

	@Override
	protected RepositoryFactorySupport createRepositoryFactory(EntityManager entityManager) {
		return new MyRepositoryFactory(entityManager);
	}
	
	private static class MyRepositoryFactory extends JpaRepositoryFactory {
		
		public MyRepositoryFactory(EntityManager entityManager) {
			super(entityManager);
		}

		@SuppressWarnings({ "unchecked", "rawtypes" })
		@Override
		protected <T, ID extends Serializable> SimpleJpaRepository<?, ?> getTargetRepository(
				RepositoryInformation information, EntityManager entityManager) {
			Class<T> domainClass = (Class<T>) information.getDomainType();
			// 对于继承了IdEntity的实体类，返回自定义的BaseRepository（也就是BaseDaoImpl），否则就返回SimpleJpaRepository
			if(IdEntity.class.isAssignableFrom(domainClass)) {
				return new BaseDaoImpl(domainClass, entityManager);
			}
			return new SimpleJpaRepository(domainClass, entityManager);
		}
		
		@Override
        protected Class<?> getRepositoryBaseClass(RepositoryMetadata metadata) {
            return metadata.getDomainType().isAssignableFrom(IdEntity.class) ? BaseDaoImpl.class : SimpleJpaRepository.class;
        }
	}
}
