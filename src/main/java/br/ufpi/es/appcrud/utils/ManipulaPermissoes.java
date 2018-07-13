package br.ufpi.es.appcrud.utils;

import java.util.ArrayList;
import java.util.List;

import br.ufpi.es.appcrud.modelo.Role;

public class ManipulaPermissoes {
	public List<Role> checaPapelUsuario(String papel){
		List<Role> papeis = new ArrayList<Role>();
		Role papelAdmin = new Role(); 
		Role papelUser = new Role();
		
		papelAdmin.setId(1);
		papelAdmin.setNome("ROLE_ADMIN");
		papelUser.setId(2);
		papelUser.setNome("ROLE_USER");
		
		switch (papel) {
		case "admin":
			papeis.add(papelAdmin);
			break;
		case "usuario":
			papeis.add(papelUser);
			break;
		default:
			break;
		}
				
		return papeis;
	}
}
