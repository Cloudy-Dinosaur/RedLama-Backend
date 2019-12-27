package jchat.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = User.TABLE_NAME)
public class User {
  public static final String TABLE_NAME = "users";

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private int id;

  @NotBlank(message = "Name is mandatory")
  @Getter @Setter
  private String lastName;

  @NotBlank(message = "Name is mandatory")
  @Getter @Setter
  private String firstName;

  @NotBlank(message = "Email is mandatory")
  @Email
  @Getter @Setter
  private String emailAddress;

  @NotBlank(message = "User name is mandatory")
  @Getter @Setter
  private String userName;

  @NotBlank(message = "Password is mandatory")
  @JsonProperty(access = Access.WRITE_ONLY) @Getter @Setter
  private String password;

  @JsonIgnore @Getter @Setter
  private String passwordSalt;
}
