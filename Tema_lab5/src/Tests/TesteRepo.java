package Tests;

import Domain.Emisiune;
import Repository.ProgramTV;

public class TesteRepo {
	private ProgramTV p;
	
	public TesteRepo(){
		p = new ProgramTV(10);
	}
	
	public void test_constructor() {
		assert(10 == p.get_capacity());
	}
	
	public void test_crud() {
		
		//create
		Emisiune e1= new Emisiune(20,"sport",3,"luni");
		Emisiune e2= new Emisiune(16,"stire",1,"vineri");
		Emisiune e3= new Emisiune(1,"film",2,"miercuri");
		this.p.create_emisiune(e1);
		this.p.create_emisiune(e2);
		this.p.create_emisiune(e3);
		assert(p.get_elem_pos(0).equals(e1));
		assert(p.get_elem_pos(1).equals(e2));
		assert(p.get_elem_pos(2).equals(e3));
		System.out.println("Aici");
		
		//read
		Emisiune[] emi = p.get_all();
		assert(emi[0] == e1);
		assert(emi[1] == e2);
		assert(emi[2] == e3);
		
		//update
		Emisiune e4= new Emisiune(15,"film",2,"vineri");
		p.update_emisiune(e1, e4);
		assert(p.get_elem_pos(0) == e4);
		
		//delete
		p.delete_emisiune(e2);
		assert(p.get_size() == 2);
	}
}

