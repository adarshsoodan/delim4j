package org.decon;

import org.objectweb.asm.Label;

public class CallWrapInfo {

    private Label callStart;
    private Object[] stack;
    private Object[] locals;
    private Label handler;
    private Object[] handlerFrame;
    private int contIndexOnStack;

    public CallWrapInfo(Label callStart, Object[] stack, Object[] locals, Label handler,
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

    public Object[] getStack() {
        return stack;
    }

    public Object[] getLocals() {
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
