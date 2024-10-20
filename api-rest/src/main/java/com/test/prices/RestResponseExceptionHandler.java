package com.test.prices;

import com.test.prices.exception.ExternalServiceException;
import com.test.prices.exception.PriceNotFound;
import lombok.extern.slf4j.Slf4j;
import org.openapitools.model.ErrorDTO;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@ControllerAdvice
public class RestResponseExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler(value = {PriceNotFound.class})
  protected ResponseEntity<Object> handlePriceNotFound(RuntimeException ex, WebRequest request) {
    ErrorDTO errorDTO = new ErrorDTO();
    errorDTO.setStatus(HttpStatus.NOT_FOUND.value());
    errorDTO.error(ex.getMessage());
    return new ResponseEntity<>(errorDTO, new HttpHeaders(), HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(value = {ExternalServiceException.class})
  protected ResponseEntity<Object> handleUnavailable(RuntimeException ex, WebRequest request) {
    ErrorDTO errorDTO = new ErrorDTO();
    errorDTO.setStatus(HttpStatus.SERVICE_UNAVAILABLE.value());
    errorDTO.error(ex.getMessage());
    return new ResponseEntity<>(errorDTO, new HttpHeaders(), HttpStatus.SERVICE_UNAVAILABLE);
  }

  @ExceptionHandler
  protected ResponseEntity<Object> handleInternalError(RuntimeException ex, WebRequest request) {
    ErrorDTO errorDTO = new ErrorDTO();
    errorDTO.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
    errorDTO.error(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
    log.error("Internal server error: {}", ex.getMessage());
    return new ResponseEntity<>(errorDTO, new HttpHeaders(), HttpStatus.SERVICE_UNAVAILABLE);
  }

}
