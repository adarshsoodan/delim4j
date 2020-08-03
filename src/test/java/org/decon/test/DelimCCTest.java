package org.decon.test;

import static org.junit.Assert.assertEquals;

import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

import org.junit.Test;

import in.neolog.delim4j.rt.Cc;
import in.neolog.delim4j.rt.Context;
import in.neolog.delim4j.rt.Resumable;

public class DelimCCTest {

    @SuppressWarnings("boxing")
    @Test
    public void test1() {
        assertEquals(Context.start(null, (@Cc Context<Integer, Integer> cc) -> 1),
                Integer.valueOf(1));
    }

    @SuppressWarnings("boxing")
    @Test
    public void test2() {
        assertEquals(Context.start(null,
                (@Cc Context<Integer, Integer> cc) -> Context.start(null,
                        (@Cc Context<Integer, Integer> cc1) -> 5)),
                Integer.valueOf(5));
    }

    @SuppressWarnings("boxing")
    @Test
    public void test3() {
        assertEquals(
                Context.start(null,
                        (@Cc Context<Integer, Integer> cc) -> Context.capture(cc, (cc1, resume) -> 5) + 6),
                Integer.valueOf(5));
    }

    @SuppressWarnings("boxing")
    @Test
    public void test3_1() {
        assertEquals(
                Context.start(null,
                        (@Cc Context<Integer, Integer> cc) -> Context.start(null,
                                (@Cc Context<Integer, Integer> cc1) -> Context.capture(cc1, (cc3, resume) -> 5) + 6)),
                Integer.valueOf(5));
    }

    @SuppressWarnings("boxing")
    @Test
    public void test3_2() {
        assertEquals(Context.start(null, (@Cc Context<Integer, Integer> cc) -> {
            int v = Context.start(null,
                    (@Cc Context<Integer, Integer> cc1) -> Context.capture(cc1, (cc3, resume) -> 5) + 6);
            int v1 = Context.capture(cc, (cc4, resume) -> 7);
            return v + v1 + 10;
        }), Integer.valueOf(7));
    }

    @SuppressWarnings("boxing")
    @Test
    public void test4() {
        assertEquals(Context.start(null, (@Cc Context<Integer, Integer> cc) -> {
            int v = Context.capture(cc, (cc1, resume) -> Context.start(null,
                    (@Cc Context<Integer, Integer> cc2) -> resume.resume(5)));
            return v + 10;
        }), Integer.valueOf(15));
    }

    @SuppressWarnings("boxing")
    @Test
    public void test5() {
        assertEquals(
                10 + Context.start(null, (@Cc Context<Integer, Integer> cc) -> 2 + Context.capture(cc,
                        (cc1, resume) -> 100 + resume.resume(resume.resume(3)))),
                117);
    }

    @SuppressWarnings("boxing")
    @Test
    public void test5_1() {
        assertEquals(10 + Context.start(null,
                (@Cc Context<Integer, Integer> cc) -> 2 + Context.capture(cc, (cc1, resume) -> 100 + resume.resume(3))),
                115);
    }

    @SuppressWarnings("boxing")
    @Test
    public void test5_2() {
        assertEquals(
                10 + Context.start(null, (@Cc Context<Integer, Integer> cc) -> 2 + Context.capture(cc,
                        (@Cc Context<Integer, Integer> cc1, Resumable<Integer, Integer> resume) -> {
                            int v = Context.start(null, (@Cc Context<Integer, Integer> cc2) -> {
                                int v1 = Context.capture(cc2,
                                        (@Cc Context<Integer, Integer> cc3, Resumable<Integer, Integer> resume1) -> 3);
                                return 9 + resume.resume(v1);
                            });
                            return 100 + resume.resume(v);
                        })),
                115);
    }

    @SuppressWarnings("boxing")
    @Test
    public void test5_3() {
        /**
         * <pre>
         let test5''' = 
            let () = printf "\ntest5'''\n" in
            let p0 = new_prompt () in
            let p1 = new_prompt () in
            let v0 = push_prompt p0 (fun () -> 
                let v = shift p0 
                            (fun sk -> 
                                100 + sk (fun () ->
                                        push_prompt p1 
                                            (fun () -> 9 + sk (fun () -> abort p1 3))
                                          )
                             )
                in v () + 2) in
                shouldbe "test5'''" int 115 (v0+10)
         * </pre>
         */
        BiFunction<Context<Integer, Integer>, Resumable<Integer, Integer>, Integer> return3 = (
                @Cc Context<Integer, Integer> cc, Resumable<Integer, Integer> resume) -> 3;

        Function<Resumable<Integer, Supplier<Integer>>, Function<Context<Integer, Integer>, Integer>> frame1 = (
                resume) -> (@Cc Context<Integer, Integer> cc) -> {
                    Supplier<Integer> v1 = () -> Context.capture(cc, return3);
                    return 9 + resume.resume(v1);
                };

        BiFunction<Context<Integer, Supplier<Integer>>, Resumable<Integer, Supplier<Integer>>, Integer> shift = (
                @Cc Context<Integer, Supplier<Integer>> cc, Resumable<Integer, Supplier<Integer>> resume) -> {
            Supplier<Integer> x = () -> Context.start(null, frame1.apply(resume));
            return 100 + resume.resume(x);
        };

        Function<Context<Integer, Supplier<Integer>>, Integer> frame = (@Cc Context<Integer, Supplier<Integer>> cc) -> {
            Supplier<Integer> v = Context.capture(cc, shift);
            return v.get();
        };
        assertEquals(115, 10 + Context.<Integer, Supplier<Integer>>start(null, frame) + 2);
    }

    @SuppressWarnings("boxing")
    @Test
    public void test54() {
        /**
         * <pre>
            let test54 = 
              let () = printf "\ntest54\n" in
              let p0 = new_prompt () in
              let p1 = new_prompt () in
              let v0 = push_prompt p0 (fun () -> 
                let v = shift p0 (fun sk -> 
                  sk (fun () ->
                        push_prompt p1 
                           (fun () -> 9 + sk (fun () -> abort p0 3))) + 100)
                in v () + 2) in
              shouldbe "test54" int 124 (v0+10)
         * </pre>
         */
        BiFunction<Context<Integer, Integer>, Resumable<Integer, Integer>, Integer> return3 = (
                @Cc Context<Integer, Integer> cc, Resumable<Integer, Integer> resume) -> 3;

        Function<Resumable<Integer, Supplier<Integer>>, Function<Context<Integer, Integer>, Integer>> frame1 = (
                resume) -> (@Cc Context<Integer, Integer> cc) -> {
                    Integer v1 = Context.capture(cc, return3);
                    return 9 + resume.resume(() -> v1);
                };

        BiFunction<Context<Integer, Supplier<Integer>>, Resumable<Integer, Supplier<Integer>>, Integer> shift = (
                @Cc Context<Integer, Supplier<Integer>> cc, Resumable<Integer, Supplier<Integer>> resume) -> {
            Supplier<Integer> x = () -> Context.start(null, frame1.apply(resume));
            return 100 + resume.resume(x);
        };

        Function<Context<Integer, Supplier<Integer>>, Integer> frame = (@Cc Context<Integer, Supplier<Integer>> cc) -> {
            Supplier<Integer> v = Context.capture(cc, shift);
            return v.get();
        };
        assertEquals(124, 10 + Context.<Integer, Supplier<Integer>>start(null, frame) + 2);
    }
}
