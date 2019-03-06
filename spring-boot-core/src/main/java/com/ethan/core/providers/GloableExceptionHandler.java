package com.ethan.core.providers;

import com.ethan.core.model.APIError;
import io.jsonwebtoken.ExpiredJwtException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.Map;


@ControllerAdvice
@RestController
@Slf4j
public class GloableExceptionHandler {

    /**
     *
     * @param:
     * @return 
     * @date 06/03/2019 5:40 PM
     */ 
    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<Object> handleUsernameNotFoundException(UsernameNotFoundException e, HttpServletRequest request) {
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        String message = getExceptionMesasge(e);

		String errorCode = httpStatus.value()+"";
		String errorDesc = e.getMessage();
        log.info("UsernameNotFoundException :{} {} {}", errorCode, errorDesc, message);
		return getApiErrorResponse(httpStatus.value(), request, httpStatus, errorCode, errorDesc, message);
    }

	/**
	 *
	 * @param e
	 * @param request
	 * @date 06/03/2019 5:40 PM
	 */
	@ExceptionHandler(AuthenticationException.class)
	public ResponseEntity<Object> handleAuthenticationException(AuthenticationException e, HttpServletRequest request) {
		HttpStatus httpStatus = HttpStatus.UNAUTHORIZED;
		String message = getExceptionMesasge(e);

		String errorCode = httpStatus.value()+"";
		String errorDesc = e.getMessage();
		log.info("AuthenticationException :{} {} {}", errorCode, errorDesc, message);
		return getApiErrorResponse(httpStatus.value(), request, httpStatus, errorCode, errorDesc, message);
	}
	@ExceptionHandler(ExpiredJwtException.class)
	public ResponseEntity<Object> handleExpiredJwtException(ExpiredJwtException e, HttpServletRequest request) {
		HttpStatus httpStatus = HttpStatus.UNAUTHORIZED;
		String message = "验证码过期，请重新发送验证码。";

		String errorCode = httpStatus.value()+"";
		String errorDesc = e.getMessage();
		log.info("ExpiredJwtException :{} {} {}", errorCode, errorDesc, message);
		return getApiErrorResponse(httpStatus.value(), request, httpStatus, errorCode, errorDesc, message);
	}

	/**
	 * To handle Exception.class thrown by Fund Transfer API MicroService
	 *
	 * @param e
	 * @param request
	 * @return 400 Bad Request
	 */
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<Object> handleAllException(Exception e, HttpServletRequest request) {
		HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
		String message = getExceptionMesasge(e);

		String errorCode = httpStatus.value()+"";
		String errorDesc = e.getMessage();

		log.info("IllegalArgumentException :{} {} {}", errorCode, errorDesc, message);
		return getApiErrorResponse(httpStatus.value(), request, httpStatus, errorCode, errorDesc, message);

	}

	private String getExceptionMesasge(Throwable e) {
		return e.getClass() + " - " + e.getMessage();
	}
	private ResponseEntity<Object> getApiErrorResponse(int statusCode, HttpServletRequest request, HttpStatus httpStatus, String errorCode, String errorDesc, String errorMessage) {
		APIError errorResponse = APIError.builder()
				.statusCode(statusCode)
				.status(httpStatus)
				.path(request.getRequestURI())
				.timestamp(TimeProvider.getCurrentTimeStamp())
				.message(errorMessage)
				.responseCode(errorCode)
				.responseMessage(errorDesc)
				.build();

		Map<String, APIError> response = Collections.singletonMap("error", errorResponse);
		return new ResponseEntity<>(response, new HttpHeaders(), httpStatus);
	}

}
