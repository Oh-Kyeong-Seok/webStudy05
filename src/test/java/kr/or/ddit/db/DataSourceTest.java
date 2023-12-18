package kr.or.ddit.db;

import static org.junit.jupiter.api.Assertions.*;

import javax.inject.Inject;
import javax.sql.DataSource;

import org.junit.jupiter.api.Test;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;

import lombok.extern.slf4j.Slf4j;

@SpringJUnitWebConfig(locations = "file:src/main/resources/kr/or/ddit/spring/*-context.xml")
//@ContextConfiguration("file:src/main/resources/kr/or/ddit/spring/datasource-context.xml")     여기 있는 " " 값 가져다 위에 사용
@Slf4j
class DataSourceTest {

	@Inject
	private DataSource dataSource;
	
	private SqlSessionTemplate sqlSession;
	
	@Test
	void test() {
		log.info("주입된 객체 : {}", dataSource);
	}
	
	@Test 
	void test1(){
		log.info("주입된 객체 : {}", sqlSession);
	}

}
