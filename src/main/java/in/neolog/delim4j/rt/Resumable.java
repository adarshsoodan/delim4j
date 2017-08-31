package in.neolog.delim4j.rt;

import java.util.function.Function;

public class Resumable<T, S> {

    private final Context<T, S>              context;
    private final Function<Context<T, S>, T> frames;

    //TODO Should this have @Cc Context as first argument?
    public T resume(S o) {
        Context<T, S> cloned = context.clone();
        cloned.setSubstitution(o);
        cloned.startResumption();
        return frames.apply(cloned);
    }

    public Resumable(Context<T, S> context, Function<Context<T, S>, T> frames) {
        this.context = context;
        this.frames = frames;
    }

}
