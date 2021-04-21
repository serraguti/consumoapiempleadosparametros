package services;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import models.Empleado;

public class ServiceEmpleados {

    private String urlapi;

    public ServiceEmpleados() {
        this.urlapi = "https://apiempleadosjavaparametros.azurewebsites.net/";
    }

    //CREAMOS UN METODO PARA CONVERTIR EL STREAM
    //DE LA PETICION HTTP A STRING
    private String readStreamHttp(InputStream stream) throws IOException {
        BufferedReader buffer
                = new BufferedReader(new InputStreamReader(stream));
        StringBuffer data = new StringBuffer();
        String linea = "";
        String separador = "";
        while ((linea = buffer.readLine()) != null) {
            data.append(separador + linea);
            separador = "\n";
        }
        return data.toString();
    }

    private String getRequestHttp(String request) throws MalformedURLException, IOException {
        URL url = new URL(this.urlapi + request);
        HttpURLConnection conexion = (HttpURLConnection) url.openConnection();
        conexion.setRequestMethod("GET");
        conexion.setRequestProperty("Accept", "application/json");
        if (conexion.getResponseCode() == 200) {
            InputStream stream = conexion.getInputStream();
            String data = this.readStreamHttp(stream);
            conexion.disconnect();
            return data;
        } else {
            conexion.disconnect();
            return null;
        }
    }

    public List<Empleado> getEmpleados() throws IOException {
        String request = "api/empleados";
        String data = this.getRequestHttp(request);
        Gson converter = new Gson();
        List<Empleado> empleados
                = converter.fromJson(data,
                        new TypeToken<List<Empleado>>() {
                        }.getType());
        return empleados;
    }

    public List<String> getOficios() throws IOException {
        String request = "api/empleados/oficios";
        String data = this.getRequestHttp(request);
        Gson converter = new Gson();
        List<String> oficios
                = converter.fromJson(data,
                        new TypeToken<List<String>>() {
                        }.getType());
        return oficios;
    }

    public void incrementarSalariosOficio(String oficio, String incremento) throws MalformedURLException, IOException {
        String request = "api/empleados/incrementaroficios/" + oficio + "/" + incremento;
        URL url = new URL(this.urlapi + request);
        HttpURLConnection conexion = (HttpURLConnection) url.openConnection();
        conexion.setRequestMethod("PUT");
        int code = conexion.getResponseCode();
        System.out.println("Codigo: " + code);
        conexion.disconnect();
    }
}
