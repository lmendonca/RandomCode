package org.roo.inaction.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.roo.addon.entity.RooEntity;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooEntity
public class Student extends Person {

	@NotNull
	@Size(min = 1, max = 30)
	private String emergencyContactName;
	
	@NotNull
	@Size(min = 1, max = 80)
	private String emergencyContactInfo;
	
	@Size(max = 30)
	private String dietaryRestrictions;
}
