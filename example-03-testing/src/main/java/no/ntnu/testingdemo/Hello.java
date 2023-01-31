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
}
