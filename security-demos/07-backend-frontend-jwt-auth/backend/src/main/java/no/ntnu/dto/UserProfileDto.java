package no.ntnu.dto;

/**
 * Data transfer object (DTO) for submitting changes to user profile data.
 */
public class UserProfileDto {
  private String bio;

  public UserProfileDto(String bio) {
    this.bio = bio;
  }

  public UserProfileDto() {
  }

  public String getBio() {
    return bio;
  }

  public void setBio(String bio) {
    this.bio = bio;
  }
}
