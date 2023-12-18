package kr.or.ddit.vo;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.validation.groups.Default;

import org.springframework.web.multipart.MultipartFile;

import kr.or.ddit.validate.grouphint.DeleteGroup;
import kr.or.ddit.validate.grouphint.InsertGroup;
import kr.or.ddit.validate.grouphint.UpdateGroup;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of = "buyerId")
public class BuyerVO implements Serializable{
	private int rnum;
   
   @NotBlank(groups = {UpdateGroup.class, DeleteGroup.class})
   private String buyerId;
   @NotBlank(groups = InsertGroup.class)
   @Size(min=0, max = 6 , groups = InsertGroup.class)
   private String buyerName;
   @NotBlank(groups = InsertGroup.class)
   @Size(max=4, min=4, groups = InsertGroup.class)
   private String buyerLgu;
   
   private LprodVO lprod; // has a 1:1 관계
   
   private String buyerBank;
   private String buyerBankno;
   private String buyerBankname;
   private String buyerZip;
   @NotBlank
   private String buyerAdd1;
   @NotBlank
   private String buyerAdd2;
  
   private String buyerComtel;
   
   private String buyerFax;
   @Email
   @NotBlank
   private String buyerMail;
   private String buyerCharger;
   private String buyerTelext;
   
   private List<ProdVO> ProdList; //has many 1:N 관계
   
   private String buyerImg;
   
   private MultipartFile buyerImage;
   
   public void setBuyerImage(MultipartFile buyerImage) {
      if(buyerImage !=null && !buyerImage.isEmpty()) {
         this.buyerImage = buyerImage;
         buyerImg = UUID.randomUUID().toString();
      }
   }
   
   public void saveTo(File saveFolder) throws IllegalStateException, IOException {
      if(buyerImage != null)
         buyerImage.transferTo(new File(saveFolder, buyerImg));
   }
   

}