package it.exolab.tesina.mybank.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import it.exolab.tesina.mybank.factory.OtpCodeFactory;
import it.exolab.tesina.mybank.factory.OtpEmailFactory;
import it.exolab.tesina.mybank.factory.StaffAssignFactory;
import it.exolab.tesina.mybank.model.Faq;
import it.exolab.tesina.mybank.model.HTTPResponse;
import it.exolab.tesina.mybank.model.Staff;
import it.exolab.tesina.mybank.service.ExternalTransactionService;
import it.exolab.tesina.mybank.service.FaqService;
import it.exolab.tesina.mybank.service.StaffService;
import it.exolab.tesina.mybank.util.Util;

@CrossOrigin
@Controller
@RequestMapping(value = "staff")
public class StaffController {
	private StaffService staffService;
	private HTTPResponse response;
	private Util util = new Util();
	
	@Autowired(required = true)
	public void setStaffService(StaffService staffService) {
		this.staffService = staffService;
	}	

	@RequestMapping(value = "index", method = RequestMethod.GET)
	public String index(@ModelAttribute Staff staff, Model model, HttpSession session) {
		if (session.getAttribute("staff") != null) {
			model.addAttribute("staff", (Staff) session.getAttribute("staff"));
		}
		return "admin/login";
	}
	
	@RequestMapping(value = "test", method = RequestMethod.GET)
	public String test(Model model, HttpSession session) {
		if (session.getAttribute("staff") != null) {
			StaffAssignFactory saf = new StaffAssignFactory();
			Integer idValidator = saf.assignToValidator(staffService);
			session.setAttribute("idValidator",idValidator);
		}
		return "admin/test";
	}
	
	@RequestMapping(value = "confermaOTP/{OTP}", method = RequestMethod.POST)
	@ResponseBody
	public void confermaOTP(@PathVariable String OTP, HttpSession session, Model model, HttpServletResponse response)
			throws IOException {
		Staff staff = (Staff) session.getAttribute("staff");
		if (staffService.findByEmailAndPasswordAndOtpCode(staff.getEmail(), staff.getPassword(), OTP) != null) {
			Staff staffToUpdate=staffService.findByEmailAndPasswordAndOtpCode(staff.getEmail(), staff.getPassword(), OTP);
			if(staffToUpdate.getNextOtpCodeAfterDate().after(Timestamp.valueOf(LocalDateTime.now()))) {
				OtpCodeFactory.UpdateOtpExpirationForAccountOrStaff(staffToUpdate);
				staffToUpdate.setUpdatedAt(Timestamp.valueOf(LocalDateTime.now()));
				staffService.update(staffToUpdate);
				response.getWriter().append("1");
			} else {
				response.getWriter().append("2");
			}
		} else {
			response.getWriter().append("0");
		}
	}
	
	// x login diretto senza OTPCode - ancora non funzione
//	@RequestMapping(value="/confermaOTPbyStraightLogin", method = RequestMethod.GET)
//	public ModelAndView confermaOTPbyStraightLogin(@ModelAttribute Staff staff, HttpServletRequest request) {
////	    request.setAttribute("param1", "one");
////		Staff staff = (Staff) request.getAttribute("staff");
//		System.out.println("STAFF in STRAIGHT LOGIN:::"+staff);
//	    return new ModelAndView("forward:/staff/confermaOTP/"+staff.getOtpCode());
//	}

	@RequestMapping(value = "login", method = RequestMethod.POST)
	public ModelAndView login(Staff staff, HttpSession session, Model model) {
		ModelAndView ret = new ModelAndView("redirect:/staff/index");

		staff = staffService.findByEmailAndPassword(staff.getEmail(), staff.getPassword());
		
		if (staff != null ) {
			// login diretto senza otpcode - ancora da implementare - non funziona
//			if(Timestamp.valueOf(LocalDateTime.now()).before(staff.getNextOtpCodeAfterDate())) {
//				// non funziona, va nel get
//				ret= new ModelAndView("redirect:/staff/confermaOTPbyStraightLogin");
//				return ret;
//			} else
			if(staff.getNextOtpCodeAfterDate().before(Timestamp.valueOf(LocalDateTime.now()))) {
				OtpCodeFactory.UpdateOtpTimerForAccountOrStaff(staff);
				OtpCodeFactory.UpdateOtpExpirationForAccountOrStaff(staff);
				staff.setOtpCode(OtpCodeFactory.doGenerateNewOtpCode());
				staff.setNextOtpCodeAfterDate(Timestamp.valueOf(LocalDateTime.now().plusMinutes(3)));	
//				OtpEmailFactory.doSendOtpCodeViaEmail(staff.getEmail(),staff.getOtpCode());						// disabilitata per non inviare troppe mail
				System.out.println("OTP CODE PER LO STAFF ID: "+staff.getId()+" :::"+staff.getOtpCode());		// da cancellare
				
				staff.setUpdatedAt(Timestamp.valueOf(LocalDateTime.now()));
				staffService.update(staff);
			}
			session.setAttribute("staff", staff);
			return ret;
		} else {
			ModelAndView ret2 = new ModelAndView("admin/login");
			ret2.addObject("login", 1);
			return ret2;
		}
	}
	
	@RequestMapping(value = "logout", method = RequestMethod.GET)
	public String logout(@ModelAttribute Staff staff, HttpSession session) {
		session.invalidate();
		return "admin/login";
	}

	@RequestMapping(value = "home", method = RequestMethod.GET)
	public String home(HttpSession session, Model model) {
		session=util.sessionCleanerFromHome(session);
		model.addAttribute("staff", (Staff) session.getAttribute("staff"));
		return "admin/homeAdmin";
	}

	@RequestMapping(value = "registrazione", method = RequestMethod.GET)
	public String register(HttpSession session, Model model) {
		Staff staffRegistrato = new Staff();
		session=util.sessionCleanerFromRegistrazione(session);
		model.addAttribute("staffRegistrato", staffRegistrato);
		return "admin/registrazione";
	}

	@RequestMapping(value = "registrazione", method = RequestMethod.POST)
	public ModelAndView registrazione(Staff staffRegistrato, HttpSession session) {
		ModelAndView ret = new ModelAndView("redirect:/staff/home");
		if (staffRegistrato.getEmail() != null || staffRegistrato.getPassword() != null
				|| staffRegistrato.getName() != null || staffRegistrato.getSurname() != null
				|| staffRegistrato.getRoleId() != 0) {
			OtpCodeFactory.CreateAccountOrStaff(staffRegistrato);
			staffRegistrato.setNextOtpCodeAfterDate(Timestamp.valueOf(LocalDateTime.now().plusDays(1)));
			staffService.insert(staffRegistrato);
			session.setAttribute("staffAdded", 0);
//			ret.addObject("staffAdded", "Membro dello staff inserito.");
			return ret;
		} else {
			session.setAttribute("staffAdded", 1);
//			ret.addObject("staffAdded", "Errore inserimento, staff non inserito.");
			return ret;
		}
	}

	@RequestMapping(value = "updatePassword/{newPass}", method = RequestMethod.POST)
	@ResponseBody
	public void updatePassword(@PathVariable(value="newPass") String updatePass, HttpSession session, Model model,
			HttpServletResponse response) throws IOException {
		Staff staff = (Staff) session.getAttribute("staff");
		if (staffService.findByEmailAndPassword(staff.getEmail(), staff.getPassword()) != null) {
			staff.setPassword(updatePass);
			staffService.update(staff);
			response.getWriter().append("1");
			session.setAttribute("staff", staff);
			session.setAttribute("passwordUpdated", 0);
		} else {
			response.getWriter().append("0");
			session.setAttribute("passwordUpdated", 1);
		}
	}
	
	@RequestMapping(value = "staffList", method = RequestMethod.GET)
	public ModelAndView staffList(Model model, HttpSession session) {
		ModelAndView ret = new ModelAndView("admin/staffList");
		// da creare
//		session=util.sessionCleanerFromTransactions(session);
		Staff staff = (Staff) session.getAttribute("staff");
		if (staff != null) {
			List<Staff> staffList = this.staffService.findAll();
			ret.addObject("staffList",staffList);
		}
		return ret;
	}
	
	@RequestMapping(value = "staffUpdate/{id}", method = RequestMethod.GET)
	public ModelAndView staffUpdate(@PathVariable String id, HttpSession session, Model model) throws IOException {
		ModelAndView ret = new ModelAndView("admin/staffUpdate");
		Staff staffToUpdate = staffService.findById(Integer.valueOf(id));
		if (staffToUpdate != null) {
			ret.addObject("staffToUpdate", staffToUpdate);
//			session.setAttribute("staffToUpdate", staffToUpdate);
		} else {
			ret.addObject("idNotFound", 1);
//			session.setAttribute("idNotFound", 1);
		}
		return ret;
	}
	
	@RequestMapping(value = "staffUpdate", method = RequestMethod.POST)
	public ModelAndView staffUpdate(Staff staffToUpdate, HttpSession session) {
		ModelAndView ret = new ModelAndView("redirect:/staff/staffList");
		if (staffToUpdate.getEmail() != null || staffToUpdate.getPassword() != null
				|| staffToUpdate.getName() != null || staffToUpdate.getSurname() != null
				|| staffToUpdate.getRoleId() != 0) {
			staffToUpdate.setNextOtpCodeAfterDate(Timestamp.valueOf(LocalDateTime.now()));
			Staff staffUpdated=staffService.findById(staffToUpdate.getId());
			staffUpdated.setName(staffToUpdate.getName());
			staffUpdated.setSurname(staffToUpdate.getSurname());
			staffUpdated.setEmail(staffToUpdate.getEmail());
			staffUpdated.setRoleId(staffToUpdate.getRoleId());
			staffUpdated.setUpdatedAt(Timestamp.valueOf(LocalDateTime.now()));
			staffService.update(staffUpdated);
			session.setAttribute("staffUpdated", 0);
//			ret.addObject("staffUpdated", "Membro dello staff inserito.");
			return ret;
		} else {
			session.setAttribute("staffUpdated", 1);
//			ret.addObject("staffUpdated", "Errore inserimento, staff non inserito.");
			return ret;
		}
	}
	
	// qui sotto dialogo con angular - deprecato
//	@RequestMapping(value = "findOne", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE)
//	@ResponseBody
//	public HTTPResponse findOne(@RequestBody Integer id) {
//
//		if (id != null) {
//			this.staffService.findById(id);
//			response = new HTTPResponse(id);
//			return response;
//		} else {
//			response = new HTTPResponse("errore", "01");
//			return response;
//		}
//	}

//	 @RequestMapping(value = "login", method = RequestMethod.POST, consumes =
//	 MediaType.APPLICATION_JSON_VALUE)
//	 @ResponseBody
//	 public HTTPResponse login(@RequestBody Staff staff) {
//		 if (staff.getEmail()!=null || staff.getPassword()!=null) {
//		 staff = staffService.findByEmailAndPassword(staff.getEmail(),
//			 staff.getPassword());
//	 		otpfactory.setNewOtpUpdate(staff);
//	 		staffService.update(staff);
//	 		otpemailfactory.doSendOtpCodeViaEmail(staff.getEmail(), staff.getOtpCode());
//	 		response = new HTTPResponse(staff);
//	 		return response;
//	 	}
//	 	response = new HTTPResponse("err", "errore");
//	 	return response;
//	
//	 }

	// controlloOtp che comunica solo con angular - discontinuato
//	 @RequestMapping(value = "controlloOtp", method = RequestMethod.POST, consumes
//	 	= MediaType.APPLICATION_JSON_VALUE)
//	 @ResponseBody
//	 public HTTPResponse controlloOtp(@RequestBody Staff staffOTP) {
//	
//	 if (staffService.findByEmailAndPasswordAndOtpCode(staffOTP.getEmail(),
//	 	staffOTP.getPassword(),
//	 	staffOTP.getOtpCode()) != null
//	 	&&
//	 	!Timestamp.valueOf(LocalDateTime.now()).after(staffOTP.getOtpCodeExpiresAt()))
//	 	{
//	 		boolean data = true;
//	 		HTTPResponse risposta = new HTTPResponse(data);
//	 		return risposta;
//	 	} else {
//	 		HTTPResponse risposta = new HTTPResponse("Errore OTP errato/scaduto", "00");
//	 		return risposta;
//	 	}
//	
//	 }
	
//	@RequestMapping(value = "findAll", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE)
//	@ResponseBody
//	public HTTPResponse findAll() {
//		List<Staff> staff = this.staffService.findAll();
//		response = new HTTPResponse(staff);
//		return response;
//	}
//
//	@RequestMapping(value = "delete", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
//	@ResponseBody
//	public HTTPResponse delete(@RequestBody Integer id) {
//		HTTPResponse response = new HTTPResponse();
//		if (id != null) {
//			this.staffService.delete(id);
//			response.setData(id);
//			response.setSuccess(true);
//			return response;
//		} else {
//			response.setSuccess(false);
//			response.setErr("Errore");
//			response.setErr_code("01");
//			return response;
//		}
//	}

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
