package it.exolab.tesina.mybank.util;

import java.time.LocalDate; 
import java.util.Date;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.exolab.tesina.mybank.model.dto.AccountDTO;
import it.exolab.tesina.mybank.model.dto.RoleDTO;



public class Utils {
	
	public static LocalDate stringToDate(String YearAsString, String MonthAsString, String DayAsString) {
		Integer year = Utils.getIntOrNull(YearAsString);
		Integer month = Utils.getIntOrNull(MonthAsString);
		Integer day = Utils.getIntOrNull(DayAsString);

		try {
			LocalDate localDate = LocalDate.of(year, month, day);
			return localDate;
		} catch (Exception e) {
			// eccezione più precisa : DateTimeException
		}

		return null;
	}

	public static Integer getIntOrNull(String numberAsString) {
		try {
			Integer ret = Integer.valueOf(numberAsString);
			return ret;
		} catch (Exception e) {

		}

		return null;
	}

	public static Integer getIntOrNull(String param, HttpServletRequest request) {
		try {
			Integer ret = Integer.valueOf(request.getParameter(param));
			return ret;
		} catch (Exception e) {

		}

		return null;
	}

	public static Integer getIntegerOrDefault(String param, HttpServletRequest request, Integer defValue) {

		try {
			Integer ret = Integer.valueOf(request.getParameter(param));
			return ret;
		} catch (Exception ex) {

		}

		return defValue;

	}

	public static Double getDoubleOrNull(String doubleAsString) {
		try {
			Double ret = Double.valueOf(doubleAsString);
			return ret;
		} catch (Exception e) {

		}
		return null;
	}

	public static boolean isNullOrEmpty(String value) {
		return value == null || value.equals("");
	}

	public static boolean isNullOrEmpty(Integer value) {
		return value == null;
	}

	public static boolean isNullOrEmpty(LocalDate value) {
		return value == null;
	}

	public static boolean isNullOrEmpty(Object value) {
		return value == null;
	}

	public static boolean isNullOrEmpty(Double value) {
		return value == null;
	}

	public static String createOrGetCookie(HttpServletRequest request, HttpServletResponse response) {
		if (request.getSession().getAttribute("session_id") != null)
			return (String) request.getSession().getAttribute("session_id");
		Cookie[] cookies = request.getCookies();
		String value = null;
		for (Cookie cookie : cookies) {
			if (cookie.getName().equals("session_id")) {
				value = cookie.getValue();
				break;
			}
		}
		if (value == null) {
			value = request.getSession().getId();
			Cookie cookie = new Cookie("session_id", value);
			cookie.setMaxAge(3600);
			response.addCookie(cookie);

		}
		request.getSession().setAttribute("session_id", value);

		return value;
	}

	public static boolean validatePassword(String password) {
		int conta_numeri = 0;
		for (int i = 0; i < password.length(); i++) {
			if (Character.isDigit(password.charAt(i)))
				conta_numeri++;
		}
		if (conta_numeri < 2) {
			return true;
		}
		if (password.contains(" "))
			return true;

		return false;
	}

	public static boolean validateEmail(String email) {
		if (!(email.endsWith(".com") || email.endsWith(".it"))) {
			return true;
		}
		int contaChiocciola = 0;
		for (int i = 0; i < email.length(); i++) {
			if (email.charAt(i) == '@') {
				contaChiocciola++;
				if (contaChiocciola >= 1)
					return true;
			}

		}
		if (email.contains(" "))
			return true;
		return false;
	}

//	public static boolean findValueIntoList(Object object, String value) {
//		boolean ret = false;
//		if (object instanceof AccountDTO) {
//			AccountDTO utente = (AccountDTO) object;
//			for (RoleDTO r : utente.getRuoli()) {
//				if (r.getNome().equals(value))
//					return true;
//			}
//		}
//		return ret;
//	}



}
