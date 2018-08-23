package com.yr.test;

import java.io.Reader;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.yr.bean.Classes;
import com.yr.bean.Teacher;

/**
 * 测试:一对一关联表查询
 * 
 * @author liucong
 *
 * @date 2017年7月25日
 */
public class ClassesTest {
	private SqlSession session;

	public static void main(String[] args) throws Exception {
		//new ClassesTest().add();
		new ClassesTest().query();
		System.out.println("testezzzz");
		System.out.println("testezzzz");
	}

	/**
	 * 得到SessionFactory
	 * 
	 * @return
	 */
	private SqlSessionFactory getFactory() {
		// mybatis的配置文件
		String resource = "conf.xml";
		Reader reader;// 使用MyBatis提供的Resource类加载mybatis的配置文件,也加载关联的映射文件
		SqlSessionFactory sessionFactory = null;// 构建sqlSession的工厂
		try {
			reader = Resources.getResourceAsReader(resource);
			sessionFactory = new SqlSessionFactoryBuilder().build(reader);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sessionFactory;
	}

	public void querys() throws Exception {
		SqlSessionFactory sessionFactory = getFactory();
		// 创建能执行映射文件中sql的sqlSession
		session = sessionFactory.openSession();
		UserMapper mapper = session.getMapper(UserMapper.class);
		List<Classes> classesList = mapper.getAllClass();
		for (Classes classes : classesList) {
			System.out.println(classes);
		}
		session.close();
	}

	public void query() throws Exception {
		SqlSessionFactory sessionFactory = getFactory();
		// 创建能执行映射文件中sql的sqlSession
		session = sessionFactory.openSession();
		UserMapper mapper = session.getMapper(UserMapper.class);
		Classes classes = mapper.getOneClass(1);
		System.out.println(classes);
		session.close();
	}
	
	public void query1() throws Exception {
		SqlSessionFactory sessionFactory = getFactory();
		// 创建能执行映射文件中sql的sqlSession
		session = sessionFactory.openSession();
		UserMapper mapper = session.getMapper(UserMapper.class);
		Classes classes = mapper.getOneClasses(1);
		System.out.println(classes);
		session.close();
	}
	public void add() throws Exception {
		SqlSessionFactory sessionFactory = getFactory();
		// 创建能执行映射文件中sql的sqlSession
		session = sessionFactory.openSession(true);
		UserMapper mapper = session.getMapper(UserMapper.class);
		Classes classes=new Classes();
		Teacher teacher=new Teacher();
		teacher.setId(3);
		teacher.setName("teacher3");
		classes.setName("class3");
		classes.setTeacher(teacher);
		mapper.addClasses(classes);
		session.close();
	}

}
