package dcc;

import org.objectweb.asm.Label;
import org.objectweb.asm.Type;

public class CallWrapInfo {

    Label callStart;
    Type[] stack;
    Type[] locals;
    Label handler;
    Object[] handlerFrame;
    int contIndexOnStack;

    public CallWrapInfo(Label callStart, Type[] stack, Type[] locals, Label handler,
            Object[] handlerFrame, int contIndexOnStack) {
        this.callStart = callStart;
        this.stack = stack;
        this.locals = locals;
        this.handler = handler;
        this.handlerFrame = handlerFrame;
        this.contIndexOnStack = contIndexOnStack;
    }

    public Label getCallStart() {
        return callStart;
    }

    public Type[] getStack() {
        return stack;
    }

    public Type[] getLocals() {
        return locals;
    }

    public Label getHandler() {
        return handler;
    }

    public Object[] getHandlerFrame() {
        return handlerFrame;
    }

    public int getContIndexOnStack() {
        return contIndexOnStack;
    }

}
