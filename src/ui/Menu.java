package ui;


import java.util.LinkedList;
import java.util.Scanner;

import entities.*;
import logic.Login;

public class Menu {
	Scanner s=null;
	Login ctrlLogin = new Login();

	public void start() {
		s = new Scanner(System.in);
		Persona p=login();
		System.out.println("Bienvenido "+p.getNombre()+" "+p.getApellido());
		System.out.println();
		
		String command;
		do {
			command=getCommand();
			executeCommand(command);
			System.out.println();
			
		}while(!command.equalsIgnoreCase("exit"));
		
		s.close();
	}

	private void executeCommand(String command) {
		switch (command) {
		case "list":
			System.out.println(ctrlLogin.getAll());
			break;
		case "find":
			System.out.println(find());
			break;
		case "search":
			System.out.println(ctrlLogin.getByApellido());
			break;
		case "new":
			agregar();
			break;
		case "edit":
			editar();
			break;
		case "delete":
			borrar();
			break;
		default:
			break;
		}
	}

	private String getCommand() {
		System.out.println("Ingrese el comando según la opción que desee realizar");
		System.out.println("list\t\tlistar todos");
		System.out.println("find\t\tbuscar por tipo y nro de documento"); //solo debe devolver 1
		System.out.println("search\t\tlistar por apellido"); //puede devolver varios
		System.out.println("new\t\tcrea una nueva persona y asigna un rol existente");
		System.out.println("edit\t\tbusca por tipo y nro de documento y actualiza todos los datos");
		System.out.println("delete\t\tborra por tipo y nro de documento");
		System.out.println();
		System.out.print("comando: ");
		return s.nextLine();
	}
	
	public Persona login() {
		Persona p=new Persona();
		
		System.out.print("Email: ");
		p.setEmail(s.nextLine());

		System.out.print("password: ");
		p.setPassword(s.nextLine());
		
		p=ctrlLogin.validate(p);
		
		return p;	
	}
	
	private Persona find() {
		System.out.println();
		Persona p=new Persona();
		Documento d=new Documento();
		p.setDocumento(d);
		System.out.print("Tipo doc: ");
		d.setTipo(s.nextLine());

		System.out.print("Nro doc: ");
		d.setNro(s.nextLine());
		
		return ctrlLogin.getByDocumento(p);
	}
	
	private void agregar()
	{
		Persona p=new Persona();
		Documento doc = new Documento();
		Rol r = new Rol();
		
		System.out.print("Ingresa tipo documento (dni | cuit): ");
		doc.setTipo(s.nextLine());
		
		System.out.print("Ingresa nro documento: ");
		doc.setNro(s.nextLine());
		
		p.setDocumento(doc);
		
		System.out.print("Ingresa nombre: ");
		p.setNombre(s.nextLine());
		
		System.out.print("Ingresa apellido: ");
		p.setApellido(s.nextLine());
		
		System.out.print("Ingresa 1-habilitado | 0-no habilitado: ");
		if(s.nextLine() == "1")
		{
			p.setHabilitado(true);
		}
		else if(s.nextLine() == "0")
		{
			p.setHabilitado(false);
		}

		System.out.print("Ingresa telefono: ");
		p.setTel(s.nextLine());
		
		System.out.print("Ingresa rol (1-admin | 2-user): ");
		r.setId(Integer.parseInt(s.nextLine()));
		
		p.addRol(r);
		
		System.out.print("Ingresa email: ");
		p.setEmail(s.nextLine());

		System.out.print("Ingresa password: ");
		p.setPassword(s.nextLine());
		
		ctrlLogin.agregar(p);
	}
	
	private void editar()
	{
		System.out.println();
		Persona p=new Persona();
		Documento d=new Documento();
		p.setDocumento(d);
		System.out.print("Tipo doc: ");
		d.setTipo(s.nextLine());

		System.out.print("Nro doc: ");
		d.setNro(s.nextLine());
		
		p = ctrlLogin.getByDocumento(p);
		System.out.println(p);

		if(p != null)
		{
			System.out.print("Ingresa nuevo nombre: ");
			p.setNombre(s.nextLine());
			
			System.out.print("Ingresa nuevo apellido: ");
			p.setApellido(s.nextLine());
			
			System.out.print("Ingresa 1-habilitado | 0-no habilitado: ");
			if(s.nextLine() == "1")
			{
				p.setHabilitado(true);
			}
			else if(s.nextLine() == "0")
			{
				p.setHabilitado(false);
			}

			System.out.print("Ingresa nuevo telefono: ");
			p.setTel(s.nextLine());
			
			System.out.print("Ingresa nuevo email: ");
			p.setEmail(s.nextLine());

			System.out.print("Ingresa nueva password: ");
			p.setPassword(s.nextLine());
			
			ctrlLogin.editar(p);
		}
	}
	
	private void borrar()
	{
		System.out.println();
		Persona p=new Persona();
		Documento d=new Documento();
		p.setDocumento(d);
		System.out.print("Tipo doc: ");
		d.setTipo(s.nextLine());

		System.out.print("Nro doc: ");
		d.setNro(s.nextLine());
		
		p = ctrlLogin.getByDocumento(p);
		
		if(p != null)
		{
			ctrlLogin.borrar(p);
		}
	}
}