package no.ntnu.dto;

/**
 * Data transfer object (DTO) for submitting changes to user profile data.
 */
public class UserProfileDto {
  private final String bio;

  public UserProfileDto(String bio) {
    this.bio = bio;
  }

  public String getBio() {
    return bio;
  }
}
