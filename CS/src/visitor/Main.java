package visitor;

import visitor.element.ElementA;
import visitor.element.ElementB;
import visitor.element.IElement;
import visitor.visitor.VisitorA;
import visitor.visitor.VisitorB;

public class Main {

	public static void main(String[] args) {
		
		IElement elementA = new ElementA();
		IElement elementB = new ElementB();
		
		elementA.accept(new VisitorA());
		elementB.accept(new VisitorB());

		elementA.accept(new VisitorB());
		elementB.accept(new VisitorA());
	}
}