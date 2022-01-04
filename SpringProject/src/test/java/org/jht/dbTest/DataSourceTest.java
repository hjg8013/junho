package org.jht.dbTest;

import static org.junit.Assert.fail;
import java.sql.Connection;
import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class DataSourceTest {
	   @Autowired
	   private DataSource dataSource;
	   @Autowired
	   private SqlSessionFactory sqlSessionFactory;
	   
	   @Test
	   public void testMyBatis() {
	      try(   SqlSession session = sqlSessionFactory.openSession();
	            Connection con = dataSource.getConnection()){
	         System.out.println(con);
	         System.out.println(session);
	      }catch(Exception e) {
	         fail(e.getMessage());
	      }
	   }
}
 