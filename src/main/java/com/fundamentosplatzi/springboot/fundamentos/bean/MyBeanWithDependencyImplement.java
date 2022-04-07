package com.fundamentosplatzi.springboot.fundamentos.bean;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class MyBeanWithDependencyImplement implements MyBeanWithDependency{
    Log LOGGER = LogFactory.getLog(MyBeanWithDependencyImplement.class);
    private MyOperation myOperation;

    public MyBeanWithDependencyImplement(MyOperation myOperation) {
        this.myOperation = myOperation;
    }

    @Override
    public void printWithDependency() {
        LOGGER.info("Hemos ingresado al metodo printWithDependency()");
        int numero = 1;
        LOGGER.debug("El numero enviado como parametro a la dependencia operacion es "+numero);
        System.out.print(myOperation.sum(numero));
        System.out.println("Hola desde la implementancion de un bean con demependencia");
    }
}
