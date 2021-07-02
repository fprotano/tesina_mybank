package it.exolab.tesina.mybank.test;

import java.time.LocalDateTime;

import it.exolab.tesina.mybank.factory.ExternalTransactionCodeFactory;

public class customCodeTrasformationTest {
	static ExternalTransactionCodeFactory externalTransactionCodeFactory = new ExternalTransactionCodeFactory();

	public static void main(String[] args) {
		String customCode = "22JI03RJ0I20I3DIJ2JI00J308FH2F3ONWZZZ";
		System.out.println("customCode::" + customCode);
		String appendedCode = externalTransactionCodeFactory.doAppendLabelOnCustomCode(customCode);
		System.out.println("appendedCode::" + appendedCode);
		String originalCode = externalTransactionCodeFactory.doRetrieveCustomCodeFromVoided(appendedCode);
		System.out.println("originalCode::" + originalCode);
		LocalDateTime data = externalTransactionCodeFactory.doRetrieveWhenVoided(appendedCode);
		System.out.println("whenVoided::" + data);
	}

}
