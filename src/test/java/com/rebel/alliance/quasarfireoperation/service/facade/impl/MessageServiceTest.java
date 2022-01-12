package com.rebel.alliance.quasarfireoperation.service.facade.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.rebel.alliance.quasarfireoperation.service.facade.IMessageService;

public class MessageServiceTest {
	
	@Test
	public void givenMessageValid_whenMessageListLags_thenReturnSuccesfulMessage() {
		String[] m1 = { "", "", "", "este", "", "", "mensaje", "" };
		String[] m2 = { "", "es", "", "", "secreto" };
		String[] m3 = { "este", "", "un", "", "" };
		IMessageService messageService = new MessageService();
		String messageConsulted = messageService.getMessage(m1, m2, m3);
		String expectedMensaje = "este es un mensaje secreto";
		Assertions.assertEquals(messageConsulted, expectedMensaje);
	}
	
	@Test
	public void givenMessageValid_whenMessageListHaveSamePositions_thenReturnSuccesfulMessage() {
		String[] m1 = { "este", "", "", "mensaje", "" };
		String[] m2 = { "", "es", "", "", "secreto" };
		String[] m3 = { "este", "", "un", "", "" };
		IMessageService messageService = new MessageService();
		String messageConsulted = messageService.getMessage(m1, m2, m3);
		String expectedMensaje = "este es un mensaje secreto";
		Assertions.assertEquals(messageConsulted, expectedMensaje);
	}
	
	@Test
	public void givenMessageInvalid_whenMessageListLessThanPositionsList_thenReturnBadRequest() {
		String[] m1 = { "este", "", "", "mensaje", "" };
		String[] m2 = { "", "es", "", "", "secreto" };
		IMessageService messageService = new MessageService();
		try {
			messageService.getMessage(m1, m2);
		} catch (Exception e) {
			Assertions.assertEquals("Bad Request Message(400) Número de mensajes insuficientes para construir el mensaje de la nave", e.getMessage());
		}
	}
	
	@Test
	public void givenMessageInvalid_whenMessageListEmpty_thenReturnBadRequest() {
		String[] m1 = {};
		String[] m2 = {};
		String[] m3 = {};
		IMessageService messageService = new MessageService();
		try {
			messageService.getMessage(m1, m2, m3);
		} catch (Exception e) {
			Assertions.assertEquals("Bad Request Message(400) Número de mensajes insuficientes para construir el mensaje de la nave", e.getMessage());
		}
	}
	
	@Test
	public void givenMessageInvalid_whenMessageGreaterThanNumberSatellites_thenReturnBadRequest() {
		String[] m1 = { "este", "", "", "mensaje", "" };
		String[] m2 = { "", "es", "", "", "secreto" };
		String[] m3 = { "este", "", "un", "", "" };
		String[] m4 = { "", "", "un", "", "secreto" };
		IMessageService messageService = new MessageService();
		try {
			messageService.getMessage(m1, m2, m3, m4);
		} catch (Exception e) {
			Assertions.assertEquals("Bad Request Message(400) Número de mensajes es diferente al número de satelites", e.getMessage());
		}
	}
}
