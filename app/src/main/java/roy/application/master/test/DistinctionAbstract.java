package roy.application.master.test;

public abstract class DistinctionAbstract {

    /**
     * 什么时候使用抽象类和接口
     *
     * 1.如果你拥有一些方法并且想让他们中的一些有默认的实现,那么使用抽象类吧。
     * 2.如果你想实现多重继承,那么你必须使用接口。由于Java不支持多重继承,子类不能够继承多个类,
     * 但可以实现多个接口。因此你就可以使用接口来解决它。
     * 3.如果基本功能在不断改变,那么就需要使用抽象类。如果不断改变基本功能并且使用接口,那么就需要改变所有
     * 实现了该接口的类。
     */

    public void distinction(){

    }

    public void fatherDistinction(){

    }

    public abstract void abstractMethod();

    public abstract void abstractFather();

//    abstract void abstractDefaultMethod();
}
