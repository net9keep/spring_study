package org.zerock;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//import static org.springframework.test.web.servlet.MockMvc.MvcResult'

import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
// 객체 테스트용 import
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import org.zerock.persistence.BoardRepository;
import org.zerock.domain.Board;

@SpringBootTest
public class BoardRepositoryTests {
	@Autowired
	private BoardRepository boardRepo;
	
	@Test
	public void inspect() {
		Class<?> clz = boardRepo.getClass();
		
		System.out.println(clz.getName());
		
		Class<?>[] interfaces = clz.getInterfaces();
		Stream.of(interfaces).forEach(inter -> System.out.println(inter.getName()));
		
		Class<?> superClasses = clz.getSuperclass();
		System.out.println(superClasses.getName());
	}
	
	
	@Test
	public void testInsert() {
		Board board = new Board();
		board.setTitle("제목1");
		board.setContent("게시물 내용");
		board.setWriter("tae0code");
		
		boardRepo.save(board);
	}
	
	@Test
	public void testRead() {
		boardRepo.findById(1L).ifPresent((board) -> { System.out.println(board); });
	}
	
	@Test
	public void testUpdate() {
		System.out.println("READ=======");
		Board board = boardRepo.findById(1L).get();
		
		System.out.println("UPDATE=======");
		board.setTitle("UPDATE TITLE");
		
		System.out.println("CALL SAVE=======");
		boardRepo.save(board);
		
	}
	
	@Test
	public void testDelete() {
		System.out.println("DELETE=======");
		boardRepo.deleteById(1L);
	}
}
