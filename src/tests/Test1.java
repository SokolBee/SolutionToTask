package tests;

import org.testng.annotations.Test;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import static org.testng.Assert.assertEquals;

//@Listeners(MyImpListener.class) // as another way which a bit easy

public class Test1 {

    public enum Priority {
        Blocker, Critical, Major, Minor
    }

    @Retention(RetentionPolicy.RUNTIME)
    @Target({ElementType.TYPE, ElementType.METHOD}) //on class level
    public @interface TestMethodInfo {

        //Приоритет теста
        Priority priority() default Priority.Major;

        //Автор теста
        String author() default "Bill Gates";

        //Дата последних изменений в тесте
        String lastModified() default "01.01.2019";
    }


    @Test
    @TestMethodInfo(priority = Priority.Critical, author = "Test user", lastModified = "20.08.2019")
    public void annotation() {
        assertEquals(1, 1);
    }
}
