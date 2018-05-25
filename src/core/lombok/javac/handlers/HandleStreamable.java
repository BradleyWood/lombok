package lombok.javac.handlers;

import com.sun.tools.javac.tree.JCTree;
import lombok.ConfigurationKeys;
import lombok.Streamable;
import lombok.core.AnnotationValues;
import lombok.javac.JavacAnnotationHandler;
import lombok.javac.JavacNode;
import org.mangosdk.spi.ProviderFor;

import static lombok.core.handlers.HandlerUtil.handleFlagUsage;
import static lombok.javac.handlers.JavacHandlerUtil.deleteAnnotationIfNeccessary;
import static lombok.javac.handlers.JavacHandlerUtil.isClass;

@ProviderFor(JavacAnnotationHandler.class)
public class HandleStreamable extends JavacAnnotationHandler<Streamable> {

	@Override
	public void handle(AnnotationValues<Streamable> annotation, JCTree.JCAnnotation ast, JavacNode annotationNode) {
		handleFlagUsage(annotationNode, ConfigurationKeys.STREAMABLE_FLAG_USAGE, "@Streamable");

		deleteAnnotationIfNeccessary(annotationNode, Streamable.class);
		JavacNode typeNode = annotationNode.up();
		boolean notAClass = !isClass(typeNode);

		if (notAClass) {
			annotationNode.addError("@Streamable is only supported on a class.");
			return;
		}

		// todo verify type is an iterable type

		// todo generate stream()
	}

}
