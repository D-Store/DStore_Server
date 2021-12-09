package gg.dstore.controller;

import gg.dstore.domain.dto.email.request.SendEmailDto;
import gg.dstore.domain.dto.jwt.request.RefreshDto;
import gg.dstore.domain.dto.token.LoginJwtDto;
import gg.dstore.domain.dto.user.request.LoginDto;
import gg.dstore.domain.dto.user.request.UserDto;
import gg.dstore.domain.response.Response;
import gg.dstore.domain.dto.user.response.LoginResponse;
import gg.dstore.lib.Log;
import gg.dstore.service.auth.AuthService;
import gg.dstore.service.jwt.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthController {
	private final JwtService jwtService;
	private final AuthService authService;
	private final Log log;

	/*
	 *회원가입
	 */
	@PostMapping("/create")
	public Response userCreate(@RequestBody UserDto userDto) {
		Response response = new Response();
		try {
			authService.userCreate(userDto);

			response.setMessage("유저 저장 성공");
			response.setHttpStatus(HttpStatus.OK);
			return response;
		} catch (HttpClientErrorException | HttpServerErrorException e) {
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR, "서버 에러");
		}
	}

	/*
	 * 로그인
	 */
	@PostMapping("/login")
	public LoginResponse login(@RequestBody LoginDto loginDto) {
		LoginResponse response = new LoginResponse();
		try {
			LoginJwtDto tokens = authService.login(loginDto);

			response.setMessage("로그인 성공");
			response.setHttpStatus(HttpStatus.OK);
			response.setTokens(tokens);
			return response;
		} catch (HttpClientErrorException | HttpServerErrorException e) {
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR, "서버 에러");
		}
	}

	/*
	 *토큰 재생성
	 */
	@PostMapping("/refresh")
	public LoginResponse refreshToken(@RequestBody RefreshDto refresh) {
		LoginResponse loginResponse = new LoginResponse();
		try {
			log.info(refresh.getRefresh());
			String subject = jwtService.refreshTokenDecoding(refresh.getRefresh());
			LoginJwtDto tokens = authService.makeTokens(subject);

			loginResponse.setTokens(tokens);
			loginResponse.setMessage("성공");
			loginResponse.setHttpStatus(HttpStatus.OK);
			return loginResponse;
		} catch (HttpClientErrorException | HttpServerErrorException e) {
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR, "서버 에러");
		}
	}

	/*
	 *이메일 인증 주소 보내기
	 */
	@PostMapping("/email")
	public Response sendEmail(@RequestBody @Valid SendEmailDto sendEmailDto) {
		Response response = new Response();
		try {
			authService.sendMail(sendEmailDto.getEmail());

			response.setMessage("이메일 보내기 성공");
			response.setHttpStatus(HttpStatus.OK);
			return response;
		} catch (HttpClientErrorException | HttpServerErrorException e) {
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR, "서버 에러");
		}
	}
}
