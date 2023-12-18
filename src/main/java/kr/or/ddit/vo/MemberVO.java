package kr.or.ddit.vo;

import java.io.IOException;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Base64;
import java.util.Set;
import java.util.UUID;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.validation.groups.Default;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

import kr.or.ddit.validate.grouphint.DeleteGroup;
import kr.or.ddit.validate.grouphint.InsertGroup;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 한사람의 회원의 정보를 캡슐화하기 위한 도메인 레이어.
 *
 *	*** Data Mapper를 이용하여 다중 엔터티를 조회하는 단계
 *	1. 주엔터티를 기준으로 엔터티간 관계를 파악
 *		1:1 ex) 하나의 상품과 그 상품의 제조사 정보 PROD(1) : BUYER(1)
 *		1:N ex) 한사람의 구매정보 MEMBER(1) : PROD(N)
 *	2. 각 엔터티의 조회 결과를 바인딩할 vo에 엔터티간에 HAS 관계로 반영
 *		1:1 has A ex) ProdVo has a BuyerVO
 *		1:N has Many ex)MemberVO has many ProdVO → outer정보는 주엔터티
 *		ex) 하나의 제조사(BUYER)와 그 제조사의 제조상품(PROD) 목록을 조회.
 *			BUYER(1) : PROD(N) → BuyerVO has many ProdVO
 *		ex) 하나의 제조사(BUYER)와 그 제조사의 분류명(LPROD)을 조회.
 *			BUYER(1) : LPROD(1) → BuyerVO has a LprodVO
 *	3. 조회결과 바인딩시, resultType 대신 resultMap을 이용해 수동바인딩 설정.
 *		has many : collection(ofType)		
 *		has a : association(javaType)
 *
 */
@Getter
@Setter
@EqualsAndHashCode(of = "memId")
@ToString
public class MemberVO implements Serializable {
	
	private int rnum;
	@NotBlank(groups = {Default.class, DeleteGroup.class})
	private String memId;
	@NotBlank(groups = {Default.class, DeleteGroup.class})
	@Size(min = 4, max = 12, groups = {Default.class, DeleteGroup.class})
	@ToString.Exclude
	@JsonIgnore
	private transient String memPass;
	
	@NotBlank(groups = InsertGroup.class)
	@Size(max = 10, groups = InsertGroup.class)
	private String memName;
	@Size(min = 6, max = 6, groups = InsertGroup.class)
	@ToString.Exclude
	@JsonIgnore
	private transient String memRegno1;
	@Size(min = 7, max = 7, groups = InsertGroup.class)
	@ToString.Exclude
	@JsonIgnore
	private transient String memRegno2;
	
	@DateTimeFormat(iso = ISO.DATE)
	private LocalDate memBir;
	
	
	@NotBlank
	private String memZip;
	@NotBlank
	private String memAdd1;
	@NotBlank
	private String memAdd2;
	
	@Pattern(regexp = "\\d{2,3}-\\d{3,4}-\\d{4}")
	private String memHometel;
	@Pattern(regexp = "\\d{2,3}-\\d{3,4}-\\d{4}")
	private String memComtel;
	@Pattern(regexp = "010-\\d{3,4}-\\d{4}")
	private String memHp;
	@NotBlank
	@Email
	@Size(min = 8)
	private String memMail;
	private String memJob;
	private String memLike;
	private String memMemorial;
	
	@DateTimeFormat(iso = ISO.DATE)
	private LocalDate memMemorialday;
	
	@Min(0)	//음수불가
	private Integer memMileage;
	private boolean memDelete;
	private int prodCount;
	
	// Keypoint!!!!!! 구매상품에 대해 정의 → has many 관계(1:N 관계 표현) : 한사람의 사용자가 여러건을 구매한다를 표현
	private Set<CartVO> cartSet;
	
	private byte[] memImg;
	private MultipartFile memImage;
	
	public void setMemImage(MultipartFile memImage) throws IOException {
		if(memImage != null && memImage.isEmpty()) {
			this.memImage = memImage;
			memImg = memImage.getBytes();
		}
	}
	
	
	public String getMemImgBase64() {
		if(memImg != null)
			return Base64.getEncoder().encodeToString(memImg);
		else
			return null;
	}
	
	
	

}
