package example.mybatits;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class TestMain {
	public static void main(String[] args) {
		String resource = "mybatis-config.xml";
//		InputStream inputStream = Test.class.getClassLoader().getResourceAsStream(resource);
		InputStream inputStream = null;
		try {
			inputStream = Resources.getResourceAsStream(resource);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);	
		SqlSession session = sqlSessionFactory.openSession();
		try {
			TestCla	tc = session.selectOne("MyMapper.select_test", 1);
			System.out.println(tc);
		} finally {
			session.close();
		}
	}
}
