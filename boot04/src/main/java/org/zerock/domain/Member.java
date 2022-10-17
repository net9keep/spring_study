package org.zerock.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


import lombok.*;

@Getter
@Setter 
@ToString
@Entity
@Table(name="tbl_members")
@EqualsAndHashCode(of="uid")
public class Member {
	@Id
	private String uid;
	private String upw;
	private String uname;
}
