package visitor.element;

import visitor.visitor.IVisitor;

public class ElementB implements IElement {

	@Override
	public void accept(IVisitor visitor) {
		visitor.visit(this);
	}
}