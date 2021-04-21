package controllers;

import java.io.IOException;
import java.util.List;
import models.Empleado;
import services.ServiceEmpleados;

public class ControllerEmpleados {

    ServiceEmpleados service;

    public ControllerEmpleados() {
        this.service = new ServiceEmpleados();
    }

    public List<Empleado> getEmpleados() throws IOException {
        List<Empleado> empleados = this.service.getEmpleados();
        return empleados;
    }

    public List<String> getOficios() throws IOException {
        List<String> oficios = this.service.getOficios();
        return oficios;
    }

    public String incrementarSalarioOficios(String oficio, String incremento) throws IOException {
        this.service.incrementarSalariosOficio(oficio, incremento);
        return "<h1 style='color:blue'>Incremento salarial correcto</h1>";
    }
}
