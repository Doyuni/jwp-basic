package core.ref;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import next.model.Question;
import next.model.User;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

public class ReflectionTest {
    private static final Logger logger = LoggerFactory.getLogger(ReflectionTest.class);

    @Test
    public void showClass()  {
        Class<Question> clazz = Question.class;
        logger.debug(clazz.getName());
        logger.info("Constructors");
        Constructor[] constructors = clazz.getConstructors();
        for (Constructor constructor : constructors) {
            System.out.println(constructor.toString());
        }
        logger.info("Methods");
        Method[] methods = clazz.getMethods();
        for (Method method : methods) {
            System.out.println(method.toString());
        }
        logger.info("Fields");
        Field[] fields = clazz.getDeclaredFields();
        for(Field field : fields) {
            System.out.println(field.toString());
        }
    }
    
    @Test
    public void newInstanceWithConstructorArgs() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Class<User> clazz = User.class;
        Constructor<User> constructor = clazz.getDeclaredConstructor(String.class, String.class, String.class, String.class);
        logger.debug(constructor.getName());
        User user = constructor.newInstance("doyuni", "password", "doyun", "yun@email.com");
        logger.debug("User: {}", user.toString());
    }
    
    @Test
    public void privateFieldAccess() throws Exception {
        Class<Student> clazz = Student.class;
        logger.debug(clazz.getName());
        Student student = new Student();
        Field field = clazz.getDeclaredField("name");
        field.setAccessible(true);
        field.set(student, "Doyun");
        field = clazz.getDeclaredField("age");
        field.setAccessible(true);
        field.setInt(student, 26);
        System.out.println("Name: " + student.getName() +
                " Age : " + student.getAge());
    }
}
