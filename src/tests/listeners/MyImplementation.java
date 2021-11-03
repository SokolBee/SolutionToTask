package tests.listeners;

import org.testng.ISuite;
import org.testng.ISuiteListener;
import tests.Test1;

public class MyImplementation implements ISuiteListener {
    @Override
    public void onFinish(ISuite suite) {
    }

    @Override
    public void onStart(ISuite suite) {
        suite.getAllMethods().stream()
                .map(testNGMethod-> testNGMethod.getConstructorOrMethod().getMethod())
                .map(method -> method.getAnnotation(Test1.TestMethodInfo.class))
                .map(an-> an.priority()+"\n"+an.author()+"\n"+an.lastModified())
                .forEach(System.out::println);
    }
}