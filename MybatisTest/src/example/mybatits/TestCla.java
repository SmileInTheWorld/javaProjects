package example.mybatits;

public class TestCla {
	int id;
	String foo;
	int bar;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFoo() {
		return foo;
	}
	public void setFoo(String foo) {
		this.foo = foo;
	}
	public int getBar() {
		return bar;
	}
	public void setBar(int bar) {
		this.bar = bar;
	}
	
	@Override
	public String toString() {
		return "id:" + id +", foo:" + foo + ", bar:"+ bar;
	}
}
