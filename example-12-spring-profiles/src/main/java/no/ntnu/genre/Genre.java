package no.ntnu.genre;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

/**
 * A genre for a book. Entity class.
 */
@Entity
public class Genre {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;
  private String name;

  public Genre() {
  }

  public Genre(String name) {
    this.name = name;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String genre) {
    this.name = genre;
  }

  @Override
  public boolean equals(Object obj) {
    return obj instanceof Genre g2 && isIdEqualTo(g2.id) && isNameEqualTo(g2.name);
  }

  @Override
  public int hashCode() {
    return super.hashCode();
  }

  /**
   * Check if provided name is equal to name of this object. Null-safe.
   *
   * @param name Name to compare
   * @return True when names are equal
   */
  private boolean isNameEqualTo(String name) {
    if (this.name != null) {
      return this.name.equals(name);
    } else {
      return name == null;
    }
  }

  /**
   * Check if provided id is equal to ID of this object. Null-safe.
   *
   * @param id ID to compare
   * @return True when the provided ID is equal to ID of this object
   */
  private boolean isIdEqualTo(Integer id) {
    if (this.id != null) {
      return this.id.equals(id);
    } else {
      return id == null;
    }
  }
}
