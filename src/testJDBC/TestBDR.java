package testJDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class TestBDR {
	static Connection con = null;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int opcao = 0;
		Scanner s = new Scanner(System.in);
		try {
			con = DriverManager.getConnection("jdbc:ucanaccess://C:/AMD/Sistema_Bancario_Tabajara.mdb");
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		while (opcao != 9) {

		 System.out.println("\nSistema Bancário Tabajara\n");
		 System.out.println("Digite a sua opção:");
		 System.out.println("1 - Cadastrar Conta");
		 System.out.println("2 - Consultar Conta");
		 System.out.println("3 - Alterar Conta");
		 System.out.println("4 - Remover Conta");
		 System.out.println("5 - Exibe todas as contas");
		 System.out.println("9 - Sair do sistema");
		 System.out.print("Sua opção: ");

		 opcao = s.nextInt();
		 switch (opcao){
		 	 case 1: // cadastrar conta
		 		String numero = "", nome = "";
		 		System.out.println("Digite o número da conta:");
		 		numero = s.next();
		 		System.out.println("Digite o nome do cliente dono da conta:");
		 		nome = Leia(nome);
		 		System.out.println("Digite o saldo inicial da conta:");
		 		Double saldo = s.nextDouble();
		 		try {
		 		Statement stmt = con.createStatement();
		 		String query = "INSERT INTO CONTA (numero, nome_cliente, saldo) VALUES(" + numero + " , '" + nome + "' , " + saldo + ")";

		 		stmt.executeUpdate(query);
		 		stmt.close();
		 		System.out.println("Conta cadastrada com sucesso!");

		 		}catch(Exception e) {
					System.out.println("Conta inexistente");
				 }
		 		 break;
			 case 2: // consultar conta
				 try {
					System.out.println("Digite o seu codigo da conta");
					String num = s.next();
					Statement st = con.createStatement();
					String query = "SELECT numero, nome_cliente , saldo FROM CONTA WHERE numero = "+ num+ "";
					ResultSet rs = st.executeQuery(query);
					if(rs.next()) {
						System.out.println("código: " + rs.getString("numero") + " nome do cliente: "+ rs.getString("nome_cliente") + " saldo: "+ rs.getDouble("saldo"));
					}else {
						System.out.println("Conta inexistente");
					}
				 }catch(Exception e) {
					System.out.println("Conta inexistente");
				 }
				 break;
	
			 case 3: // alterar conta
				 	numero = "";
				 	nome = "";
			 		System.out.println("Digite o número da conta:");
			 		numero = s.next();
			 		System.out.println("Digite o nome do cliente dono da conta:");
			 		nome = Leia(nome);
			 		System.out.println("Digite o saldo inicial da conta:");
			 		saldo = s.nextDouble();
			 		try {
				 		Statement stmt = con.createStatement();
				 		String query = "UPDATE CONTA SET nome_cliente='" + nome + "'" + ", saldo = " + saldo + " WHERE " + "numero =" + numero + "";
	
				 		int numLinhas = stmt.executeUpdate(query);
				 		stmt.close();
				 		if(numLinhas == 0) {
							System.out.println("Conta inexistente");	
						}else {
							System.out.println("Conta alterada com sucesso");
						}
			 		}catch(Exception e) {
						System.out.println("Conta inexistente");
					 }
				 break;
	
			 case 4: // remover conta
				 try {
					System.out.println("Digite o seu codigo da conta a qual deseja deletar:");
					String cod = s.next();
					String query = "DELETE FROM CONTA WHERE numero = "+ cod+ "";
					Statement stmt = con.createStatement();
					int numLinhas = stmt.executeUpdate(query);
					stmt.close();
					if(numLinhas == 0) {
						System.out.println("Conta inexistente");	
					}else {
						System.out.println("Conta deletada com sucesso");
					}
				 }catch(Exception e) {
					System.out.println("Erro!");
				 }
				 break;
	
			 case 5: // exibe todas as contas
				try{
					Statement st = con.createStatement();
					ResultSet rs = st.executeQuery("SELECT numero, nome_cliente , saldo FROM CONTA");
					System.out.println("Contas na base de dados:\n");
					while (rs.next()){
						System.out.println("código: " + rs.getString("numero") + " nome do cliente: "+ rs.getString("nome_cliente") + " saldo: "+ rs.getDouble("saldo"));
					}

					 rs.close();
					 st.close();
				}catch(Exception e) {
					System.out.println("Hi, deu bronca...");
				}
				 break;
		 	}

		 try{ Thread.sleep(4000);}
		 catch(Exception e){e.printStackTrace();}
		 // encerramento do programa...

		 System.out.println("Tchau...");
		}
	}

	public static String Leia(String param) {

		java.io.DataInputStream dado_lido;

		String stemporario = "";

		try {
			dado_lido = new java.io.DataInputStream(System.in);
			stemporario = dado_lido.readLine();
		} catch (Exception e){}

		return stemporario;

	}
}
