package Tests;

import Controller.EmisiuneController;
import Domain.Emisiune;
import Repository.ProgramTV;

public class TesteService {
	private EmisiuneController ec;
	
	public TesteService(){
		ProgramTV p = new ProgramTV(10);
		ec = new EmisiuneController(p);
	}
	
	public void test_crud() {
		
		//create & read
		Emisiune e1= new Emisiune(20,"sport",3,"luni");
		Emisiune e2= new Emisiune(16,"stire",1,"vineri");
		Emisiune e3= new Emisiune(1,"film",2,"miercuri");
		this.ec.add_emisiune(e1);
		this.ec.add_emisiune(e2);
		this.ec.add_emisiune(e3);
		Emisiune[] e=ec.get_all();
		assert(e[0] == e1);
		assert(e[1] == e2);
		assert(e[2] == e3);
		
		//update
		Emisiune e4= new Emisiune(15,"film",2,"vineri");
		ec.update_emisiune(e1, e4);
		e=ec.get_all();
		assert(e[0] == e4);
		
		//delete
		ec.delete_emisiune(e2);
		e=ec.get_all();
		assert(e.length == 2);
	}
}


