package com.bestdeals.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import com.bestdeals.annotations.AccessCheck;

@Aspect
@Component
public class AccessAspect {

	@Before("@annotation(accessCheck)")
	public void logAround(AccessCheck accessCheck)  {
		
		String permission = accessCheck.value();
		System.out.println("Perform access check here");
		
		//throw runtime exception if the user is not permissioned
		if (permission.equals("Exception")){
			throw new RuntimeException("Not Permissioned");
		}
		
		
	}

}
