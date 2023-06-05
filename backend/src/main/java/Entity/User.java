package Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Date;

@Entity
@Table(name = "user")
@Getter @Setter @ToString
public class User {
    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @Column(unique = true)
    private String email;

    @Column(unique = true, name = "PN")
    private String phoneNum;

    @Column(name = "PW")
    private String password;

    private String gender;

    @Column(name = "b_day")
    private Date birthday;

    @Column(name = "PRFCN")
    private String score;

    private Date regDate;

    private String state;

}
