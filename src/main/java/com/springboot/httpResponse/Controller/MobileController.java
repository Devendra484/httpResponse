package com.springboot.httpResponse.Controller;

import com.springboot.httpResponse.Model.Metadata;
import com.springboot.httpResponse.Model.Mobiles;
import com.springboot.httpResponse.Service.MobileService;
import com.springboot.httpResponse.Service.TimeStamp;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/mobiles")
public class MobileController {
	
	@Autowired
	private MobileService mobileService;
	
	@Autowired 
	private TimeStamp timeStamp;
	
	@GetMapping
	public ResponseEntity<Object>  mobiles_list() {

		Map<String,Object> response=new HashMap<String, Object>();
		Metadata metadata=new Metadata(200,"success",null);
		response.put("metadata", metadata);
		List<Mobiles> mobiles=mobileService.mobiles_List();
		if(mobiles.isEmpty()) {
			String s ="Mobiles list is empty";
			response.put("body",s);
		}
		else{
			response.put("body", mobiles);
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Object> mobile(@PathVariable Long id)  {
/*
		try {
*/
			Mobiles mobile=mobileService.mobileData( id);
			Metadata metadata=new Metadata(200,"success",null);
			Map<String,Object> response=new HashMap<String, Object>();		
			response.put("body", mobile);
			response.put("metadata", metadata);
			return new ResponseEntity<>(response, HttpStatus.OK);
		/*}
		catch(ResponseStatusException ex) {
			ErrorResponseBody errorResponseBody= new ErrorResponseBody(timeStamp.timeStamp, ex.getStatusCode().value(),ex.getStatusCode(), ex.getReason(),"/mobiles/"+id);
			return new ResponseEntity<> (errorResponseBody,ex.getStatusCode());
		}
		catch (Exception ex) {
	        Metadata metadata = new Metadata(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage(), null);
			Map<String,Object> response=new HashMap<String, Object>();
			response.put("metadata", metadata);
			response.put("Error",ex.getLocalizedMessage() );
	        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
*/
	}
	
	@PostMapping
	public ResponseEntity<Object> saveMobile(@Valid @RequestBody(required = false) Mobiles mobile) {
		    Mobiles newMobile=mobileService.newMobile(mobile);
			Metadata metadata=new Metadata(201,"success",null);
			Map<String,Object> response=new HashMap<String, Object>();
			response.put("body", mobile);
			response.put("metadata", metadata);
			return new ResponseEntity<>(response, HttpStatus.CREATED);
	}
	@PutMapping("{id}")
	public  ResponseEntity<Object> updateMobileData(@Valid @RequestBody Mobiles mobiles, Long id){
		Metadata metadata=new Metadata(HttpStatus.OK.value(), "success",null);
		Mobiles updatedMobile=mobileService.updateMobile(mobiles,id);
		HashMap<String,Object> response=new HashMap<>();
		response.put("Meta Data ",metadata);
		response.put("body",updatedMobile);
		return  new ResponseEntity<>(response,HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Object> deleteMobile(@PathVariable Long id) {
		mobileService.oldMobile(id);
		Metadata metadata=new Metadata(204,"success",null);
		Map<String,Object> response=new HashMap<String, Object>();
		response.put("body","[Deleted Successfully]");
		response.put("metadata", metadata);
		System.out.println(response);
		return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
	}

}
