package com.rebel.alliance.quasarfireoperation.service.facade.impl;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import com.rebel.alliance.quasarfireoperation.exception.MessageException;
import com.rebel.alliance.quasarfireoperation.service.facade.IMessageService;
import com.rebel.alliance.quasarfireoperation.utilities.Constant;

@Service
public class MessageService implements IMessageService {

	@Override
	public String getMessage(String[]... message) {
		List<Integer> matrizSize = validateArraySize(message);
		String[][] matrixMsg = buildMatrix(matrizSize, message);
		String secretMessage = buildMessage(matrixMsg, matrizSize.get(0));
		return secretMessage;
	}
	
	private List<Integer> validateArraySize(String[]... message) {
		int row = 0;
		int column = 0;
		for (String[] msg : message) {
			if (msg.length > column) {
				column = msg.length;
			}
			row++;
		}
		if (column == 0 && row < 3 ) {
			throw new MessageException(Constant.MESSAGE_ERROR);
		}
		List<Integer> matrizSize = Arrays.asList(row, column);
		return matrizSize;
	}

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

	private String buildMessage(String[][] matrixMsg, int rowSizeMatrix) {
		int column = 0;
        int row = 0;
        int sizeColumna = matrixMsg[row].length;
        StringBuilder mensaje = new StringBuilder(); 
        while(column < sizeColumna) {
        	row = 0;
			while (row < 3) {
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
