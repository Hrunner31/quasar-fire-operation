package com.rebel.alliance.quasarfireoperation.service.facade.impl;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import com.rebel.alliance.quasarfireoperation.exception.MessageException;
import com.rebel.alliance.quasarfireoperation.service.facade.IMessageService;
import com.rebel.alliance.quasarfireoperation.utilities.Constant;

/**
 * La Clase MessageService es la encargada de obtener el mensaje enviado por la nave rebelde a traves de los 
 * satelites
 * @author @author H. Leonardo A. Corredor
 *
 */
@Service
public class MessageService implements IMessageService {

	/**
	 * El método getMessage es el orquestador de realizar las validaciones del tamaño de los arrays de los mensajes
	 * de los satelites, adicional se encarga de hacer el llamado para construir la matriz para obtener el mensaje de 
	 * la nave rebelde
	 * @param message: Array de arrays mensajes de los diferentes satelites
	 * @return String: Mensaje filtrado
	 */
	@Override
	public String getMessage(String[]... message) {
		List<Integer> matrizSize = validateArraySize(message);
		String[][] matrixMsg = buildMatrix(matrizSize, message);
		String secretMessage = buildMessage(matrixMsg);
		return secretMessage;
	}
	
	/**
	 * El método validateArraySize se encarga de validar el número de columnas y de filas que debe tener
	 * la matriz para poder obtener el mensaje de la nave rebelde
	 * @param message: Array de arrays mensajes de los diferentes satelites
	 * @return List<Integer>: Lista con el tamaño de las filas y columnas para construir la matriz
	 */
	private List<Integer> validateArraySize(String[]... message) {
		int row = 0;
		int column = 0;
		for (String[] msg : message) {
			if (msg.length > column) {
				column = msg.length;
			}
			row++;
		}
		if (column == 0 || row < Constant.SATELLITE_NUMBERS ) {
			throw new MessageException(Constant.MESSAGE_ERROR);
		} else if (row > Constant.SATELLITE_NUMBERS) {
			throw new MessageException(Constant.MESSAGE_LIST_ERROR);
		}
		List<Integer> matrizSize = Arrays.asList(row, column);
		return matrizSize;
	}

	/**
	 * El método buildMatrix se encarga de construir la matriz de los mensajes de los satelites
	 * @param matrizSize: Lista con el tamaño de las filas y columnas para construir la matriz
	 * @param message: Array de arrays mensajes de los diferentes satelites
	 * @return String[][]: Matriz de mensajes
	 */
	private String[][] buildMatrix(List<Integer> matrizSize, String[]... message) {
		int columnsMatrix = matrizSize.get(1);
		String[][] matrixMsg = new String[matrizSize.get(0)][matrizSize.get(1)]; // Se asigna las dimensiones de la matriz
		int row = 0;
		for (String[] arrayMsg : message) {
			int posiciones = matrizSize.get(1) - arrayMsg.length ; // Obtener posiciones que faltan por llenar
			int posicionesArray = 0;
			for(int  column = 0 ; column < columnsMatrix; column++) {
				if (column < posiciones) {
					matrixMsg[row][column] = "";
				} else {
					matrixMsg[row][column] = arrayMsg[posicionesArray++];
				}
			}
			row ++;
		}
		return matrixMsg;
	}

	/**
	 * El método buildMessage se encarga de construir el mensaje enviado por la nave rebelde
	 * @param matrixMsg: Matriz con los mensajes para realizar el filtrado de desfase y obtener el mensaje enviado
	 * @return String: Mensaje sin desfase de la nave rebelde
	 */
	private String buildMessage(String[][] matrixMsg) {
		int column = 0;
        int row = 0;
        int sizeColumna = matrixMsg[row].length;
        StringBuilder mensaje = new StringBuilder(); 
        while(column < sizeColumna) {
        	row = 0;
			while (row < Constant.SATELLITE_NUMBERS) {
				if (matrixMsg[row][column] != "") {
					if (column == (sizeColumna - 1)) {
						mensaje.append(matrixMsg[row][column]);
					} else {
						mensaje.append(matrixMsg[row][column]).append(" ");
					}
					break;
				}
				row++;
			}
			column++;
        }
		return mensaje.toString();
	}
}
