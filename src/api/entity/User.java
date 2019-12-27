package api.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = User.tableName)
public class User {
  public static final String tableName = "users";

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private int id;

  @Getter @Setter
  private String lastName;

  @Getter @Setter
  private String firstName;

  @NotNull @JsonIgnore @Getter @Setter
  private String emailAddress;

  @Getter @Setter
  private String userName;

  @NotNull @JsonIgnore @Getter @Setter
  private String passwordHash;

  @NotNull @JsonIgnore @Getter @Setter
  private String passwordSalt;
}
