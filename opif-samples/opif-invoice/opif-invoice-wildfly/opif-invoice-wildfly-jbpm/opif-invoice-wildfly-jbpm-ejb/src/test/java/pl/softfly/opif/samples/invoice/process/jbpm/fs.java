package pl.softfly.opif.samples.invoice.process.jbpm;

import org.junit.Test;

import java.io.IOException;

public class fs {

    @Test
    public void test() throws IOException {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();

        if (classLoader == null) {
            classLoader = Class.class.getClassLoader();
        }

        System.out.println(classLoader.getResource("MainFlow.bpmn2"));

    }

}
