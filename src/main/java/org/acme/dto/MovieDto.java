package org.acme.dto;

import jakarta.validation.constraints.NotNull;

public class MovieDto {

  private Long id;

  @NotNull
  private String title;

  @NotNull
  private String description;


  public MovieDto() {

  }

  public MovieDto(Long id, String title, String description) {
    this.id = id;
    this.title = title;
    this.description = description;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }


  public void setTitle(String title) {
    this.title = title;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }
}
