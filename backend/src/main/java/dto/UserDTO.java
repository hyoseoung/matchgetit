package dto;

import lombok.Data;

import java.sql.Date;
import java.sql.Timestamp;

@Data
public class UserDTO {
    private String userId;
    private String bankId;
    private String pn;
    private String email;
    private String password;
    private String name;
    private String nameEdit;
    private Date birthday;
    private String gender;
    private String preference;
    private String accountType;
    private Date registrationDate;
    private String loginType;
    private String accountState;
    private String chargeId;
    private String chargeBank;
    private String ownedCard;
    private String ownedPoint;
    private String win;
    private String lose;
    private String rating;
    private Timestamp benefitPeriod;
    private String recommendCount;
}
