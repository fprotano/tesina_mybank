package it.exolab.tesina.mybank.controller;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;



import it.exolab.tesina.mybank.model.HTTPResponse;
import it.exolab.tesina.mybank.model.Staff;
import it.exolab.tesina.mybank.model.dto.StaffDTO;
import it.exolab.tesina.mybank.service.StaffService;

@CrossOrigin
@Controller
@RequestMapping(value = "staff")
public class StaffController {
	private StaffService staffService;
	private HTTPResponse response;

	@Autowired(required = true)
	public void setStaffService(StaffService staffService) {
		this.staffService = staffService;
	}

	@RequestMapping(value = "login", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public HTTPResponse login(@RequestBody Staff staff) {
		if (staff != null) {
			staff = this.staffService.findByEmailAndPassword(staff.getEmail(), staff.getPassword());
			response = new HTTPResponse(staff);
			return response;
		}
		response = new HTTPResponse("err", "errore");
		return response;

	}

	@RequestMapping(value = "registrazione", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody

	public HTTPResponse registrazione(@RequestBody StaffDTO staff) {
		if (staff != null) {
			this.staffService.insert(staff);
			response = new HTTPResponse(staff);
			return response;

		} else {
			response = new HTTPResponse("errore", "01");
			return response;
		}

	}

	@RequestMapping(value = "findOne", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public HTTPResponse findOne(@RequestBody Integer id) {

		if (id != null) {
			this.staffService.findById(id);
			response = new HTTPResponse(id);

			return response;
		} else {

			response = new HTTPResponse("errore", "ciaobelli");
			return response;
		}

	}

	@RequestMapping(value = "findAll", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public HTTPResponse findAll() {

		List<StaffDTO> staff = this.staffService.findAll();
		response = new HTTPResponse(staff);
		return response;

	}

	@RequestMapping(value = "delete", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public HTTPResponse delete(@RequestBody Integer id) {
		HTTPResponse response = new HTTPResponse();
		if (id != null) {
			this.staffService.delete(id);
			response.setData(id);
			response.setSuccess(true);
			return response;
		} else {
			response.setSuccess(false);
			response.setErr("Errore");
			response.setErr_code("01");
			return response;

		}
	}

	// @RequestMapping(value="update", method=RequestMethod.POST,consumes =
	// MediaType.APPLICATION_JSON_VALUE)
	// @ResponseBody
	// public HTTPResponse update(@RequestBody Integer id) {
	// HTTPResponse response = new HTTPResponse();
	// LocalDateTime dataNow = LocalDateTime.now();
	// Staff staff= new Staff ();
	// if(id!=null) {
	// this.staffService.update(id);
	//
	// response.setData(id);
	// response.setSuccess(true);
	// staff.setUpdatedAt(Timestamp.valueOf(dataNow));
	// return response;
	// } else {
	// response.setSuccess(false);
	// response.setErr("Errore");
	// response.setErr_code("01");
	// return response;
	//
	// }
	// }

}
