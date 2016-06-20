package dcc;

import org.objectweb.asm.Label;
import org.objectweb.asm.Type;

public class CallWrapInfo {

    Label callStart;
    Type[] stack;
    Type[] locals;
    Label handler;
    Object[] handlerFrame;

    public CallWrapInfo(Label callStart, Type[] stack, Type[] locals, Label handler,
            Object[] handlerFrame) {
        this.callStart = callStart;
        this.stack = stack;
        this.locals = locals;
        this.handler = handler;
        this.handlerFrame = handlerFrame;
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
}
