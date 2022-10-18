package visitor.element;

import visitor.visitor.IVisitor;

public interface IElement {

	public void accept(IVisitor visitor);
}
