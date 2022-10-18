package visitor.visitor;

import visitor.element.ElementB;
import visitor.element.IElement;

public class VisitorB implements IVisitor {

	@Override
	public void visit(IElement element) {
		if(element instanceof ElementB) {
			System.out.println("VisitorB : " + element.getClass());
		} //
		else {
			System.out.println("element는 ElementB 객체가 아닙니다.");
		}
	}
}
