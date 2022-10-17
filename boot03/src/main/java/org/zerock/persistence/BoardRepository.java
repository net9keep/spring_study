package org.zerock.persistence;


import org.zerock.domain.Board;

import java.util.*;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface BoardRepository extends CrudRepository<Board, Long>, QuerydslPredicateExecutor<Board> {
	public List<Board> findBoardByTitle(String title);
	public Collection<Board> findBoardByWriter(String writer);
	public Collection<Board> findBoardByWriterContaining(String writer);
	public Collection<Board> findByTitleContainingOrContentContaining(String title, String content);
	public Collection<Board> findByTitleContainingAndBnoGreaterThan(String title, Long num);
	public Collection<Board> findByBnoGreaterThanOrderByBnoDesc(Long num);
	public List<Board> findByBnoGreaterThanOrderByBnoDesc(Long bno, Pageable paging);
	//public List<Board> findByBnoGreaterThan(Long bno, Pageable paging);
	public Page<Board> findByBnoGreaterThan(Long bno, Pageable paging);
	
	@Query("SELECT b FROM Board b WHERE b.title LIKE %?1% ANd b.bno > 0 ORDER BY b.bno DESC")
	public List<Board> findByTitle(String title);
	
	@Query("SELECT b FROM Board b WHERE b.content LIKE %:content% ANd b.bno > 0 ORDER BY b.bno DESC")
	public List<Board> findByContent(@Param("content") String Content);
	
	//필요한 컬럼만 가져오는 경우는, Object형식으로
	@Query("SELECT b.bno, b.title, b.writer, b.regdate"
			+ " FROM Board b WHERE b.title LIKE %?1% AND b.bno > 0 ORDER BY b.bno desc")
	public List<Object[]> findByTitle2(String title);
	
	
	@Query(value="select bno, title, writer, from Board where title like CONCAT('%', ?1, '%') and bno > 0 order by bno desc", nativeQuery=true)
	public List<Object[]> findByTitle3(String title);
	
	//Query 사용하면서 Pageing 
	@Query("SELECT b FROM Board b WHERE b.bno > 0 ORDER BY b.bno ASC")
	public List<Board> findByPage(Pageable pageable);
}

