/*
 * Copyright Adarsh Soodan, 2016
 * Licensed under http://www.apache.org/licenses/LICENSE-2.0
 */
package in.neolog.delim4j;

import org.objectweb.asm.Label;

public class CallWrapInfo {

    private final Label callStart;
    private final Object[] stack;
    private final Object[] locals;
    private final Label handler;
    private final Object[] handlerFrame;
    private final int contIndexOnStack;

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
