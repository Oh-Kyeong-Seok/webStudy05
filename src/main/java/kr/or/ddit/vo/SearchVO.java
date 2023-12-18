package kr.or.ddit.vo;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor	// 기본생성자 생성
@AllArgsConstructor	// property전체를 받는 생성자 생성
public class SearchVO implements Serializable {

	private String searchType;
	private String searchWord;
}
