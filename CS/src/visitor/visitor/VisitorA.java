package visitor.visitor;

import visitor.element.ElementA;
import visitor.element.IElement;

public class VisitorA implements IVisitor {

	@Override
	public void visit(IElement element) {
		if(element instanceof ElementA) {
			System.out.println("VisitorA : " + element.getClass());
		} //
		else {
			System.out.println("element는 ElementA 객체가 아닙니다.");
		}
	}
}