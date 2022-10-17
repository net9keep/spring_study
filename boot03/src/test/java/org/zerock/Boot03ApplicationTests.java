package org.zerock;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.zerock.domain.Board;
import org.zerock.domain.QBoard;
import org.zerock.persistence.*;

import com.querydsl.core.BooleanBuilder;

import java.util.*;

@SpringBootTest
class Boot03ApplicationTests {

	@Autowired
	private BoardRepository repo;
	
	@Test
	public void testInsert200() {
		for(int i = 0; i<200; i++) {
			Board board = new Board();
			board.setTitle("제목"+i);
			board.setContent("내용.."+i);
			board.setWriter("user"+i);
			repo.save(board);
		}
	}
	
	@Test
	public void testByTitle() {
		repo.findBoardByTitle("제목1").forEach(board -> System.out.println(board));
	}
	
	@Test
	public void testByWriter() {
		Collection<Board> results = repo.findBoardByWriter("user1");
		results.forEach( board -> System.out.println(board));
	}
	
	@Test
	public void testByWriterContainig() {
		Collection<Board> results = repo.findBoardByWriterContaining("user");
		results.forEach( board -> System.out.println(board));
	}
	
	@Test
	public void testByTitleAndBno() {
		Collection<Board> results = repo.findByTitleContainingAndBnoGreaterThan("5", 50L);
		results.forEach( board -> System.out.println(board));
	}
	
	@Test
	public void testByBnoOrdeByDesc() {
		Collection<Board> results = repo.findByBnoGreaterThanOrderByBnoDesc(100L);
		results.forEach(board -> System.out.println(board));
	}
	
	@Test
	public void testByBnoOrderByDescPaging() {
		Pageable paging = PageRequest.of(0, 10);
		List<Board> results = repo.findByBnoGreaterThanOrderByBnoDesc(0L, paging);
		results.forEach(board -> System.out.println(board));
	}
	
	@Test
	public void testByBno() {
		Pageable paging = PageRequest.of(0, 50, Sort.Direction.ASC, "bno");
		//List<Board> results = repo.findByBnoGreaterThan(0L, paging);
		Page<Board> results = repo.findByBnoGreaterThan(0L, paging);
		System.out.println("page size : " + results.getSize());
		System.out.println("Total pages  : " + results.getTotalPages());
		System.out.println("Total Count : " + results.getTotalElements());
		System.out.println("Next : " + results.nextPageable());
		List<Board> list = results.getContent();
		list.forEach(board -> System.out.println(board));
	}
	
	@Test
	public void testByTitle2() {
		repo.findByTitle("17").forEach(board -> System.out.println(board));
	}
	
	@Test
	public void testByContent2() {
		repo.findByContent("00").forEach(board -> System.out.println(board));
	}
	
	@Test
	public void testByTitle3() {
		repo.findByTitle2("00").forEach(board -> System.out.println(Arrays.toString(board)));
	}
	
	@Test
	public void testByTitle4() {
		repo.findByTitle3("00").forEach(board -> System.out.println(Arrays.toString(board)));
	}
	
	@Test 
	public void testByPaging() {
		Pageable paging = PageRequest.of(0, 50);
		repo.findByPage(paging).forEach(board -> System.out.println(board));
	}
	
	@Test
	public void testPredicate() {
		String type = "t";
		String keyword = "17";
		
		BooleanBuilder builder = new BooleanBuilder();
		QBoard board = QBoard.board;
		
		if(type.equals("t")) {
			builder.and(board.title.like("%"+keyword+"%"));
		}
		
		builder.and(board.bno.gt(0L));
		
		Pageable pageable = PageRequest.of(0, 10);
		Page<Board> result = repo.findAll(builder, pageable);
		List<Board> list = result.getContent();
		list.forEach(b -> System.out.println(b));
	}
	
}
