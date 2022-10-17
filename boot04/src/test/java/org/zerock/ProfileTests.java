package org.zerock;

import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.zerock.domain.Member;
import org.zerock.persistence.MemberRepository;
import org.zerock.persistence.ProfileRepository;

import lombok.extern.java.Log;


@SpringBootTest
@Log
@Commit
public class ProfileTests {
	
	@Autowired
	MemberRepository memberRepo;
	
	@Autowired
	ProfileRepository profileRepo;
	
	
	@Test
	public void testInsertMembers() {
		IntStream.rangeClosed(1, 100).forEach( i -> {
			Member member = new Member();
			member.setUid("user"+i);
			member.setUpw("pw"+i);
			member.setUname("사용자"+i);
			
			memberRepo.save(member);
		});
	}

}
