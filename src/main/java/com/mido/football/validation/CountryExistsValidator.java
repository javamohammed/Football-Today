package com.mido.football.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mido.football.entity.Country;
import com.mido.football.service.country.CountryService;

@Component
public class CountryExistsValidator implements ConstraintValidator<CountryExists, String> {
	
	@Autowired
	private CountryService countryService;

	@Override
	public boolean isValid(String nameCountry, ConstraintValidatorContext theConstraintValidatorContext) {

		boolean result = true;
		try {
			Country country = countryService.getCountry(nameCountry);
			if ( country == null) {
				result = false;
			}
			}catch (Exception ex) {
			 
			}
		return result;
	}

}
