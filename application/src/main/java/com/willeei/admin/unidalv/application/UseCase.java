package com.willeei.admin.unidalv.application;

public abstract class UseCase<I, O> {

    public abstract O execute(I anIn);
}
