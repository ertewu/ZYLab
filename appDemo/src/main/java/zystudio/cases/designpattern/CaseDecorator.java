package zystudio.cases.designpattern;

import android.util.Log;


/**
 * 装饰模式的例子，主要是为了理解java.io 这里边的东西，据说这里边全是用decorator模式的
 */
public class CaseDecorator {

    private static CaseDecorator sCase;
    public static CaseDecorator getInstance(){
        if(sCase==null){
            sCase=new CaseDecorator();
        }
        return sCase;
    }

    abstract class Component {
        public abstract void Operation();
    }

    class ConcreteComponent extends Component {

        @Override
        public void Operation() {
            // 具体的对象操作
            Log.i("ertewu", "ConcreteComponent action origial occured\t这是本质上要做的Action！");
        }
    }

    abstract class Decorator extends Component {

        protected Component component;

        public void SetComponent(Component component) {
            this.component = component;
        }

        @Override
        public void Operation() {
            if (component != null) {
                component.Operation();
            }
        }
    }

    class ConcreteDecoratorA extends Decorator {
        private String addedState;

        @Override
        public void Operation() {
            super.Operation();
            addedState = "New State";
            Log.i("ertewu", "ConcreteDecoratorA action occured");
        }
    }

    class ConcreteDecoratorB extends Decorator{
        @Override
        public void Operation() {
            super.Operation();
            AddedBehavior();
            Log.i("ertewu", "ConcreteDecoratorB action occured");
        }

        private void AddedBehavior(){

        }
    }

    public void work(){
        ConcreteComponent c=new ConcreteComponent();
        ConcreteDecoratorA d1=new ConcreteDecoratorA();
        ConcreteDecoratorB d2=new ConcreteDecoratorB();

        d1.SetComponent(c);
        d2.SetComponent(d1);

        d2.Operation();
    }
}
