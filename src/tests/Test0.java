package tests;

import org.testng.annotations.Test;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.lang.reflect.Proxy;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

public class Test0 {
    @Retention(java.lang.annotation.RetentionPolicy.RUNTIME)
    @Target({METHOD, TYPE})
    @interface Selector {

        String xpath();
    }

    public interface MainPage {

        @Selector(xpath = ".//*[@test-attr='input_search']")
        String textInputSearch();

        @Selector(xpath = ".//*[@test-attr='button_search']")
        String buttonSearch();
    }

    class MethodInterception {

        @Test
        public void annotationValue() {
            MainPage mainPage = createPage(MainPage.class);
            assertNotNull(mainPage);
            assertEquals(mainPage.buttonSearch(), ".//*[@test-attr='button_search']");
            assertEquals(mainPage.textInputSearch(), ".//*[@test-attr='input_search']");
        }

        private MainPage createPage(Class<? extends MainPage> clazz) {
            return (MainPage)Proxy.newProxyInstance(getClass().getClassLoader(),
                    new Class[]{clazz}, (proxy, method, args) -> method.getAnnotation(Selector.class).xpath());
        }
    }
}
