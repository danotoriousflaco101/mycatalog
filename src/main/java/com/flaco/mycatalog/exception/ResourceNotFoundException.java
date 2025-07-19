package com.flaco.mycatalog.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Eccezione personalizzata per indicare che una risorsa richiesta non è stata trovata.
 * L'annotazione @ResponseStatus fa sì che Spring Boot risponda automaticamente
 * con uno stato HTTP 404 (Not Found) quando questa eccezione viene lanciata da un controller.
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {

  public ResourceNotFoundException(String message) {
    super(message);
  }
}