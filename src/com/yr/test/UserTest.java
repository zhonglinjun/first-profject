package com.yr.test;

import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.yr.bean.User;

public class UserTest {
	private SqlSession session;

	public static void main(String[] args) throws Exception {
		// Class<?> c = Class.forName("com.yr.test.UserTest");
		// UserTest test = (UserTest) c.newInstance();
		// test.query();
		// new UserTest().addUser();
		// new UserTest().updUser();
		//new UserTest().querycall();
		new UserTest().querys();
		// 创建缓存管理器
		/*CacheManager cacheManager = new CacheManager();
		// 创建一个缓存实例
		Cache cache = cacheManager.getCache("helloworld1");
		// 创建一个数据容器
		Element element = new Element("key", "Hello Word!");
		// 将数据放到缓存实例中
		cache.put(element);
		System.out.println(element.getObjectValue());*/
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

	/**
	 * 查询
	 * 
	 * @throws Exception
	 */
	public void querys() throws Exception {
		SqlSessionFactory sessionFactory = getFactory();
		// 创建能执行映射文件中sql的sqlSession
		session = sessionFactory.openSession();
		UserMapper mapper = session.getMapper(UserMapper.class);
		User usera = new User();
		usera.setId(10);
		usera.setName("%九%");
		usera.setAge(18);
		List<Integer> list = new ArrayList<>();
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(4);
		list.add(5);
		list.add(6);
		list.add(7);
		usera.setList(list);
		System.out.println("第一次查询:");
		List<User> userList = mapper.getAll(usera);
		for (User user : userList) {
			System.out.println(user);
		}
		System.out.println("第二次查询(读取缓存)<一级缓存>:");
		List<User> userLists = mapper.getAll(usera);
		for (User user : userLists) {
			System.out.println(user);
		}
		session.clearCache();
		System.out.println("第三次查询(缓存已清除,进行正常的读取):");
		List<User> userListse = mapper.getAll(usera);
		for (User user : userListse) {
			System.out.println(user);
		}
		session.close();// 或者commit();
		// Thread.sleep(60000);//超时之后缓存自动清除
		System.out.println("第四次查询(缓存进入二级缓存，从一级缓存中查看是否具有相同查询条件的结果，如果具有，则直接从一级缓存中读取,否则进行正常读取)<二级缓存>:");
		session = sessionFactory.openSession();
		UserMapper mappers = session.getMapper(UserMapper.class);
		// usera.setId(1);
		List<User> userListe = mappers.getAll(usera);
		for (User user : userListe) {
			System.out.println(user);
		}
		System.out.println("第五次查询(从一级缓存读取缓存):");
		List<User> userListses = mappers.getAll(usera);
		for (User user : userListses) {
			System.out.println(user);
		}
		System.out.println("第六次查询(从一级缓存读取缓存,测试缓存命中率是否提高):");
		List<User> userListsese = mappers.getAll(usera);
		for (User user : userListsese) {
			System.out.println(user);
		}
		System.out.println("第七次查询(从一级缓存读取缓存,进一步测试缓存命中率是否与读取缓存次数成正比):");
		List<User> userListseses = mappers.getAll(usera);
		for (User user : userListseses) {
			System.out.println(user);
		}
		session.close();
	}

	public void querycall() throws Exception {
		SqlSessionFactory sessionFactory = getFactory();
		// 创建能执行映射文件中sql的sqlSession
		session = sessionFactory.openSession();
		UserMapper mapper = session.getMapper(UserMapper.class);
		Map<String, Integer> map = new HashMap<>();
		map.put("sex_id", 1);
		mapper.getCount(map);
		System.out.println(map.get("user_count"));
		session.close();
	}

	public void addUser() throws Exception {
		SqlSessionFactory sessionFactory = getFactory();
		// 创建能执行映射文件中sql的sqlSession
		session = sessionFactory.openSession(true);
		UserMapper mapper = session.getMapper(UserMapper.class);
		User user = new User("黄袍带刀侍卫", 28);
		mapper.addUser(user);
		session.close();
	}

	public void updUser() throws Exception {
		SqlSessionFactory sessionFactory = getFactory();
		// 创建能执行映射文件中sql的sqlSession
		session = sessionFactory.openSession();
		UserMapper mapper = session.getMapper(UserMapper.class);
		User user = new User(5, "骠骑将军", 28);
		mapper.updateUser(user);
		session.commit();
		session.close();
	}

	public void delUserById(Integer id) throws Exception {
		SqlSessionFactory sessionFactory = getFactory();
		// 创建能执行映射文件中sql的sqlSession
		session = sessionFactory.openSession(true);
		UserMapper mapper = session.getMapper(UserMapper.class);
		mapper.deleteUser(id);
		session.close();
	}

	public void query() throws Exception {
		// mybatis的配置文件
		String resource = "conf.xml";
		/**
		 * 使用类加载器加载mybatis的配置文件(包括相关的映射文件) InputStream is =
		 * Test.class.getClassLoader().getResourceAsStream(resource);
		 * 构建sqlSession的工厂 SqlSessionFactory sessionFactory = new
		 * SqlSessionFactoryBuilder().build(is);
		 */
		// 使用MyBatis提供的Resource类加载mybatis的配置文件(包括相关的映射文件)
		Reader reader = Resources.getResourceAsReader(resource);
		// 构建sqlSession的工厂
		SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(reader);
		// 创建能执行映射文件中sql的sqlSession
		SqlSession session = sessionFactory.openSession(true);
		// 映射sql的表示字符串
		String statement = "com.yr.mapper.userMapper.getUserList";
		String statement1 = "com.yr.mapper.userMapper.getUser";
		/**
		 * selectOne:根据id查询一条记录
		 */
		User users = new User("下八旗", 25);
		session.insert("com.yr.mapper.userMapper.addUser", users);
		User user1 = session.selectOne(statement1, 1);
		System.out.println("根据id查询一条:" + user1);

		System.out.println("=============");

		/**
		 * selectList:查询所有返回list
		 */
		List<User> userList = session.selectList(statement);
		for (User user : userList) {
			System.out.println(user);
		}
	}
}