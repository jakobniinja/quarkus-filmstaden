package org.acme.dto;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class MovieDtoTest {

  @Test
  void onInit(){
      assertNotNull(new MovieDto());
  }

}