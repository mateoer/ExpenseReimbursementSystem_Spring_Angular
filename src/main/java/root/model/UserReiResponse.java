package root.model;

import java.time.LocalDateTime;

import lombok.Data;
import root.model.enumscontainer.ReiStatus;
import root.model.enumscontainer.ReiType;

@Data
public class UserReiResponse {

	private int userId;        
    private String firstName;
    private String lastName;
    private String username;

    private int reiId;
    private double rei_amount;
    private String rei_description;
    private ReiStatus reiStatus;
    private ReiType reiType;
    private Integer reiAuthor;
    private Integer rei_resolver;
    private LocalDateTime rei_submitteDate;
    private LocalDateTime rei_resolvedDate; 
    private String receiptPicName;
	
}
