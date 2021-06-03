package it.exolab.tesina.mybank.controller;

import java.sql.Timestamp; 

import java.time.LocalDateTime;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import it.exolab.tesina.mybank.model.HTTPResponse;
import it.exolab.tesina.mybank.model.Staff;
import it.exolab.tesina.mybank.model.dto.StaffDTO;
import it.exolab.tesina.mybank.service.StaffService;
import it.exolab.tesina.mybank.factory.OtpCodeFactory;
import it.exolab.tesina.mybank.factory.OtpEmailFactory;

@CrossOrigin
@Controller
@RequestMapping(value = "staff")
public class StaffController {
	private StaffService staffService;
	private HTTPResponse response;
	private OtpCodeFactory otpfactory = new OtpCodeFactory();
	private OtpEmailFactory otpemailfactory = new OtpEmailFactory();
	
	@Autowired(required = true)
	public void setStaffService(StaffService staffService) {
		this.staffService = staffService;
	}

//	 @RequestMapping(value="login", method=RequestMethod.GET)
//		public String login(User user, Model model) {
//			model.addAttribute("messaggio","Ciao inserisci le credenziali");
//			model.addAttribute("user",user);
//			return "login";
//		}
	
	@RequestMapping(value = "index", method = RequestMethod.GET)
	   public String index(@ModelAttribute Staff staff) {
	      return "admin/login";
	   }
	 @RequestMapping(value="login", method=RequestMethod.POST)
	 public ModelAndView login(Staff staff, HttpSession session) {
		 ModelAndView ret = new ModelAndView("redirect:/staff/home");
		 	staff = staffService.findByEmailAndPassword(staff.getEmail(), staff.getPassword());
			if(staff!=null) {
		 	ret.addObject("staff",staff);
		 	session.setAttribute("staff", staff);
			return ret;
			} else {
				ModelAndView ret2 = new ModelAndView("login");
				ret2.addObject("messaggio", "Credenziali errate");
					return ret2;
			}
		}
	 @RequestMapping(value="home", method=RequestMethod.GET)
		public String home(HttpSession session,Model model) {
			model.addAttribute("staff",(Staff)session.getAttribute("staff"));
			return "admin/homeAdmin";
		}
	
//	@RequestMapping(value = "login", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
//	@ResponseBody
//	public HTTPResponse login(@RequestBody Staff staff) {
//		if (staff.getEmail()!=null || staff.getPassword()!=null) {
//			staff = staffService.findByEmailAndPassword(staff.getEmail(), staff.getPassword());
//			otpfactory.setNewOtpUpdate(staff);
//			staffService.update(staff);
//			otpemailfactory.doSendOtpCodeViaEmail(staff.getEmail(), staff.getOtpCode());
//			response = new HTTPResponse(staff);
//			return response;
//		}
//		response = new HTTPResponse("err", "errore");
//		return response;
//
//	}

	@RequestMapping(value="registrazione", method=RequestMethod.GET)
		public String register(HttpSession session,Model model) {
			Staff staffRegistrato = new Staff();
			boolean otp = false;
			model.addAttribute("staffRegistrato", staffRegistrato);
			return "admin/registrazione";
		}
	
	
	 
	@RequestMapping(value = "registrazione", method = RequestMethod.POST)
	public ModelAndView registrazione(Staff staffRegistrato) {
		ModelAndView ret = new ModelAndView("redirect:/staff/home");
		if (staffRegistrato.getEmail()!=null || staffRegistrato.getPassword()!=null || staffRegistrato.getName()!=null || staffRegistrato.getSurname()!=null || staffRegistrato.getRoleId()!=0) {
			otpfactory.setCreatedUpdatedAndOtp(staffRegistrato);
			staffService.insert(staffRegistrato);
			ret.addObject("messaggio", "Membro dello staff inserito.");
			return ret;

		} else {
			ret.addObject("messaggio", "Errore inserimento, staff non inserito.");
			return ret;
		}

	}

	@RequestMapping(value = "controlloOtp", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public HTTPResponse controlloOtp(@RequestBody Staff staffOTP) {
		
		if(staffService.findByEmailAndPasswordAndOtpCode(staffOTP.getEmail(), staffOTP.getPassword(), staffOTP.getOtpCode())!=null && !Timestamp.valueOf(LocalDateTime.now()).after(staffOTP.getOtpCodeExpiresAt())) {
			boolean data = true;
			HTTPResponse risposta = new HTTPResponse(data);
			return risposta;
			} else {
			HTTPResponse risposta = new HTTPResponse("Errore OTP errato/scaduto", "00");
			return risposta;
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

		List<Staff> staff = this.staffService.findAll();
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
