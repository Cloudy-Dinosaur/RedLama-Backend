package jchat.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = User.TABLE_NAME)
public class User {
  public static final String TABLE_NAME = "users";

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private int id;

  @Getter @Setter
  private String lastName;

  @Getter @Setter
  private String firstName;

  @Getter @Setter
  private String emailAddress;

  @Getter @Setter
  private String userName;

  @JsonProperty(access = Access.WRITE_ONLY) @Getter @Setter
  private String password;

  @JsonIgnore @Getter @Setter
  private String passwordSalt;
}
