package com.rebel.alliance.quasarfireoperation.service.facade.impl;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import com.rebel.alliance.quasarfireoperation.service.facade.IMessageService;

@Service
public class MessageService implements IMessageService {

	@Override
	public String getMessage(String... message) {
		List<Integer> matrizSize = validateArraySize(message);
		String[][] matrixMsg = buildMatrix(matrizSize, message);
		String secretMessage = buildMessage(matrixMsg, matrizSize.get(0));
		return secretMessage;
	}

	private String[][] buildMatrix(List<Integer> matrizSize, String[]... message) {
		int columnsMatrix = matrizSize.get(1);
		String[][] matrixMsg = new String[matrizSize.get(0)][matrizSize.get(1)]; // Se asigna las dimensiones de la matriz
		int row = 0;
		for (String[] arrayMsg : message) {
			Integer posiciones = arrayMsg.length - matrizSize.get(1); // Obtener posiciones que faltan por llenar
			for(int  column = 0 ; column < columnsMatrix; column++) {
				if (column < posiciones) {
					matrixMsg[row][column] = "";
				} else {
					matrixMsg[row][column] = arrayMsg[column];
				}
			}
			row ++;
		}
		
		return matrixMsg;
	}

	public List<Integer> validateArraySize(String[]... message) {
		int row = 0;
		int column = 0;
		for (String[] msg : message) {
			if (msg.length > column) {
				column = msg.length;
			}
			row++;
		}
		List<Integer> matrizSize = Arrays.asList(row, column);
		
		System.out.println("row: " + row + " " + "Column: " + column);
		return matrizSize;
		
	}

	private String buildMessage(String[][] matrixMsg, int rowSizeMatrix) {
		int column = 0;
        int row = 0;
        int sizeColumna = matrixMsg[row].length;
        System.out.println("Size: " + sizeColumna);
        StringBuilder mensaje = new StringBuilder(); 
        while(column < sizeColumna) {
        	row = 0;
			while (row < 3) {
				if (matrixMsg[row][column] != "") {
					mensaje.append(matrixMsg[row][column]).append(" ");
					break;
				}
				row++;
			}
			column++;
        }
		return mensaje.toString();
	}

}
