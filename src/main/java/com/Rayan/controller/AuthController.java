package com.Rayan.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.Rayan.config.JwtProvider;
import com.Rayan.model.TwoFactorOTP;
import com.Rayan.model.User;
import com.Rayan.repo.UserRepo;
import com.Rayan.response.AuthResponse;
import com.Rayan.service.CustomerUserDetailService;
import com.Rayan.service.EmailService;
import com.Rayan.service.TwoFactorOtpService;
import com.Rayan.utils.OtpUtils;

@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private TwoFactorOtpService twoFactorOtpService;
	
	@Autowired
	private CustomerUserDetailService customerUserDetailService;
	
	@Autowired
	private EmailService emailService;
	
	@PostMapping("/signup")
	public ResponseEntity<AuthResponse> register(@RequestBody User user) throws Exception{
		
		
		User isEmailExist=userRepo.findByEmail(user.getEmail());
		
		if(isEmailExist!=null) {
			throw new Exception("Email already exists");
		}
		User newUser= new User();
		newUser.setEmail(user.getEmail());
		newUser.setPassword(user.getPassword());
		newUser.setFullName(user.getFullName());
		
		User savedUser= userRepo.save(newUser);
		
		
		Authentication auth= new UsernamePasswordAuthenticationToken(user.getEmail(),user.getPassword());
		
		SecurityContextHolder.getContext().setAuthentication(auth);
		
		String jwt=JwtProvider.generateToken(auth);
		
		AuthResponse res= new AuthResponse();
		res.setJwt(jwt);
		res.setStatus(true);
		res.setMessage("register success");
		
		return new ResponseEntity<>(res,HttpStatus.CREATED);
	}
	
	@PostMapping("/signin")
	public ResponseEntity<AuthResponse> login(@RequestBody User user) throws Exception{
		
		String userName=user.getEmail();
		String password=user.getPassword();
		Authentication auth= authenticate(userName,password);
		SecurityContextHolder.getContext().setAuthentication(auth);
		
		String jwt=JwtProvider.generateToken(auth);
		
		User authUser=userRepo.findByEmail(userName);
		
		if(user.getTwoFactorAuth().isInEnabled()) {
			
			AuthResponse res= new AuthResponse();
			res.setMessage("Two Factor aith is enabled");
			res.setTwoFactorAuthEnabled(true);
			String otp=OtpUtils.generateOTP();
			
			TwoFactorOTP oldTwoFactorOtp=twoFactorOtpService.findByUser(authUser.getId());
			
			if(oldTwoFactorOtp!=null) {
				twoFactorOtpService.deleteTwoFactorOtp(oldTwoFactorOtp);
			}
			
			TwoFactorOTP newTwoFactorOtp=twoFactorOtpService.createTwoFactorOtp(authUser, otp, jwt);
			
			emailService.sendVerificationOtpEmail(otp,userName);
			
			res.setSession(newTwoFactorOtp.getId());
			
			return new ResponseEntity<>(res,HttpStatus.ACCEPTED);
			
		}
		
		AuthResponse res= new AuthResponse();
		res.setJwt(jwt);
		res.setStatus(true);
		res.setMessage("login success");
		
		return new ResponseEntity<>(res,HttpStatus.CREATED);
	}

	private Authentication authenticate(String userName, String password) {
		
		UserDetails  userDetails=customerUserDetailService.loadUserByUsername(userName);
		
		if(userDetails==null) {
			throw new BadCredentialsException("invalid username");
			
		}
		
		if(!password.equals(userDetails.getPassword())){
			throw new BadCredentialsException("invalid Password");
		}
		
		return new UsernamePasswordAuthenticationToken(userDetails,password,userDetails.getAuthorities());
	}
	
	@PostMapping("/two-factor/otp/{otp}")
	public ResponseEntity<AuthResponse> verifySigninOtp(@PathVariable String otp,@RequestParam String id  ) throws Exception{
		
		TwoFactorOTP twoFactorOtp=twoFactorOtpService.findById(id);
		
		if(twoFactorOtpService.verifyTwoFactorOtp(twoFactorOtp,otp)) {
			AuthResponse res= new AuthResponse();
			res.setMessage("Two Factor authenticate verified");
			res.setTwoFactorAuthEnabled(true);
			res.setJwt(twoFactorOtp.getJwt());
			
			return new ResponseEntity<AuthResponse>(res,HttpStatus.OK);
			
		}
		throw new Exception("invalid Otp");

	}
}
