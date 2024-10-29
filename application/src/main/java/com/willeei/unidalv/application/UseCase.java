package com.willeei.unidalv.application;

public abstract class UseCase<I, O> {

    public abstract O execute(I anIn);
}
