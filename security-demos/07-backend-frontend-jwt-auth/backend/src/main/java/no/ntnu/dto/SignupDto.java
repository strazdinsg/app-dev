package no.ntnu.dto;

/**
 * Data transfer object (DTO) for data from the sign-up form.
 */
public class SignupDto {
  private final String username;
  private final String password;

  public SignupDto(String username, String password) {
    this.username = username;
    this.password = password;
  }

  public String getUsername() {
    return username;
  }

  public String getPassword() {
    return password;
  }
}
