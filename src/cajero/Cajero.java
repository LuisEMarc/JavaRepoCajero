package cajero;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.Stack;

public class Cajero {
	
	public static Scanner sc = new Scanner(System.in);

	public static Double saldo = 1000.00;
	public static Double saldoInicial = 1000.00;
	public static Stack<String> movimiento = new Stack<String>();
	public static Stack<String> fecha = new Stack<String>();
	public static Stack<Double> saldos = new Stack<Double>();

	public static String getDate() {

		LocalDateTime myDateObj = LocalDateTime.now();
		DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

		String formattedDate = myDateObj.format(myFormatObj);

		return formattedDate;
	}
	
	public static String setName() {
		System.out.print("Ingrese su nombre: ");
		String user = sc.next();
		
		return user;
	}
	
	public static String user = setName();
	
	public static void menu(String user) {
		
		System.out.print("\n\n¡Bienvenido " + user
				+ "!\n============================\n\n[1] Consultar saldo\n[2] Retirar saldo\n[3] Histórico de Movimientos\n[0] Salir\n\n============================\n\nIngrese opcion: ");
		int opcion = sc.nextInt();

		switch (opcion) {
		case 1:

			System.out.println("Consultar saldo.");
			System.out.println("Su saldo actual es de: $" + saldo);
			movimiento.push("Consulta de saldo");
			fecha.push(getDate());
			saldos.push(saldo);
			opExitosa();

			break;
		case 2:
			System.out.println("Retirar saldo.");
			if (saldo <= 0) {

				System.out.println(
						"¡UPS! Parece que no dispone de fondos suficientes para realizar un retiro.");
				opExitosa();

			} else {

				System.out.print("Indique la cantidad que va a retirar: ");
				double saldoretirado = sc.nextDouble();
				System.out.println("");

				if (saldoretirado > saldoInicial) {
					
					System.out.println(
							"¡UPS! Parece que no dispone de fondos suficientes para realizar este retiro.");
					opExitosa();
					
				} else {
					System.out.println("Retirando saldo, espere...");
					saldo = saldo - saldoretirado;
					movimiento.push("Retiro de saldo");
					fecha.push(getDate());
					saldos.push(saldo);
					opExitosa();

				}

			}

			break;
		case 3:
			System.out.println("------------------Historico-----------------");
			System.out.println("[Movimiento][Saldo][Fecha]");
			for (int i = 0; i < movimiento.size(); i++) {
				System.out.println("[" + movimiento.elementAt(i) + "][" + saldos.elementAt(i) + "]["
						+ fecha.elementAt(i) + "]");
			}
			System.out.println("--------------------------------------------");
			opExitosa();

			break;
		default:

			System.out.println("Introduce una de las opciones validas.");
			opExitosa();

			break;
		}
		
	}
	
	public static void opExitosa() {

		System.out.println(
				"\n============================\n\n¿Qué desea hacer?\n[1] Volver al menu\n[2] Finalizar\n\n============================");
		System.out.print("Introduzca opcion: ");
		int opcion2 = sc.nextInt();
		System.out.println("");

		if (opcion2 == 1) {
			menu(user);
		} else {
			System.exit(0);
		}

	}

	public static void main(String[] args) {

		try {
			//Esta será la nueva línea
			System.out.println("Cajero automatico.");

			int intentos = 3;

			do {
				System.out.print("Ingrese pin: ");
				int pin = sc.nextInt();
				
				if (pin == 1235) {

					menu(user);

				} else {
					intentos -= 1;
					System.out
							.println("PIN incorrecto: Intente nuevamente... \n\nIntentos restantes: " + intentos + ".");
				}

			} while (intentos > 0);

		} catch (Exception InputMismatchException) {
			System.out.println("ERROR: Debe introducir solo numeros enteros.");
		} 

	}

}
