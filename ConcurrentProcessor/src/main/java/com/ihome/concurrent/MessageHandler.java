package com.ihome.concurrent;

/**
 * @version V1.0
 * @Project: MyTools
 * @Title: 消息处理器, 由子类派生
 * @Package com.ihome.concurrent
 * @Description:
 * @Author xiaolei-0228@163.com
 * @Date 13-12-30 下午11:12
 * @Copyright: 2013 ihome.com
 */
public abstract class MessageHandler<E> {
    public abstract void consume(E e);
}
