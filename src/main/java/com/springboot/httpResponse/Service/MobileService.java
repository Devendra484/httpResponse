package com.springboot.httpResponse.Service;

import java.util.List;

import com.springboot.httpResponse.ExceptionHandler.MobileNotFoundException;
import com.springboot.httpResponse.ExceptionHandler.RequestBodyMissingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.springboot.httpResponse.Model.Mobiles;
import com.springboot.httpResponse.Repository.MobileRepository;

@Service
public class  MobileService {
	
	@Autowired
	private  MobileRepository mobileRepository;
	
	public List<Mobiles> mobiles_List(){
		return mobileRepository.findAll();
	}
	
	public Mobiles mobileData(Long id)  {
		return mobileRepository.findById(id)
				.orElseThrow(() -> new MobileNotFoundException(HttpStatus.NOT_FOUND, "Mobile not found at :"+id));
	}

	public Mobiles newMobile(Mobiles mobile) {
		if(mobile==null ) throw  new RequestBodyMissingException(HttpStatus.BAD_REQUEST, "Request Body missing");
		return mobileRepository.save(mobile);
	}
	
	public void oldMobile(Long id) {
		 mobileRepository.delete(mobileData(id));
	}

	public Mobiles updateMobile(Mobiles mobiles, Long id) {
		Mobiles existedMobile=mobileData(id);
		existedMobile=Mobiles.builder()
				.Id(existedMobile.getId())
				.mobileName(mobiles.getMobileName())
				.model(mobiles.getModel())
				.price(mobiles.getPrice())
				.build();
		return mobileRepository.save(existedMobile);
	}
}

/*
	200 Ok Success - Get ,Post,Put,Delete
	201 Created   -Post
	204 No Content -Delete

	400
	405 Method Not Allowed- when different api hits with different method

 */