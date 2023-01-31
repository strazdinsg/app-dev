package no.ntnu.testingdemo;

/**
 * A simple data-model class. Represents a simple message.
 */
public class Hello {
  private String title;
  private String message;

  public Hello(String title, String message) {
    this.title = title;
    this.message = message;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  /**
   * Check if the object is valid.
   *
   * @return True when it is valid, false otherwise
   */
  public boolean isValid() {
    return title != null && message != null;
  }
}
