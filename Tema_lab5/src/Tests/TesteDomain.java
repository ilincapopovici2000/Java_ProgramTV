package Tests;

import Domain.Emisiune;

public class TesteDomain {
	private Emisiune e;
	
	public TesteDomain(){
		e = new Emisiune(1,"film",2,"marti");
	}
	
	public void test_constructor() {
		assert(1 == e.get_ora());
		assert("film" == e.get_categoria());
		assert(2 == e.get_durata());
		assert("marti" == e.get_zi());
	}
	
	public void test_set() {
		e.set_ora(14);
		e.set_categoria("show");
		e.set_durata(1);
		e.set_zi("joi");
		assert(14 == e.get_ora());
		assert("show" == e.get_categoria());
		assert(1 == e.get_durata());
		assert("joi" == e.get_zi());
	}
	
	public void test_equals() {
		Emisiune e2 = new Emisiune(1,"film",2,"marti");
		assert(false == e.equals(e2));
	}
	
	public void test_toString() {
		assert("14, show, 1, joi" == e.toString());
	}
	
	
	
	
}
