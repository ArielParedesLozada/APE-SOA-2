package com.mycompany.frontend.data.network;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.MediaType;
import okhttp3.Response;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * Cliente HTTP genérico basado en OkHttp y Jackson.
 */
public class ApiService {
    private static final MediaType JSON = MediaType.get("application/json; charset=utf-8");

    private final OkHttpClient client = new OkHttpClient();
    private final ObjectMapper mapper = new ObjectMapper();
    private final String baseUrl;

    /**
     * Crea un ApiService apuntando al servidor.
     * @param baseUrl p.ej. "http://localhost:3100"
     */
    public ApiService(String baseUrl) {
        // No incluir slash final para concatenar rutas correctamente
        this.baseUrl = baseUrl;
    }

    /**
     * Ejecuta un GET que devuelve un array JSON y lo convierte en List<T>.
     * @param path ruta relativa (p.ej. "/waza" o "/vehiculos")
     * @param clazz clase del array JSON (p.ej. ColorDTO[].class)
     * @param <T> tipo de elemento
     * @return lista de elementos
     * @throws IOException si hay error de red o parseo
     */
    public <T> List<T> getList(String path, Class<T[]> clazz) throws IOException {
        Request request = new Request.Builder()
            .url(baseUrl + path)
            .get()
            .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected HTTP code: " + response.code());
            }
            String body = response.body().string();
            T[] array = mapper.readValue(body, clazz);
            return Arrays.asList(array);
        }
    }

    /**
     * Ejecuta un GET que devuelve un único objeto JSON.
     * @param path ruta relativa (p.ej. "/vehiculos/123")
     * @param clazz clase del objeto (p.ej. VehiculoDTO.class)
     * @param <T> tipo de objeto
     * @return instancia de T
     * @throws IOException si hay error de red o parseo
     */
    public <T> T getOne(String path, Class<T> clazz) throws IOException {
        Request request = new Request.Builder()
            .url(baseUrl + path)
            .get()
            .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected HTTP code: " + response.code());
            }
            String body = response.body().string();
            return mapper.readValue(body, clazz);
        }
    }

    /**
     * Ejecuta un POST con payload JSON y parsea la respuesta a T.
     * @param path ruta relativa (p.ej. "/vehiculos")
     * @param payload objeto a serializar
     * @param clazz clase de la respuesta
     * @param <T> tipo de respuesta
     * @return instancia de T parseada
     * @throws IOException si hay error de red o parseo
     */
    public <T> T post(String path, Object payload, Class<T> clazz) throws IOException {
        String json = mapper.writeValueAsString(payload);
        RequestBody body = RequestBody.create(json, JSON);
        Request request = new Request.Builder()
            .url(baseUrl + path)
            .post(body)
            .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected HTTP code: " + response.code());
            }
            String respBody = response.body().string();
            return mapper.readValue(respBody, clazz);
        }
    }

    /**
     * Ejecuta un PUT con payload JSON y parsea la respuesta a T.
     * @param path ruta relativa (p.ej. "/vehiculos/123")
     * @param payload objeto a serializar
     * @param clazz clase de la respuesta
     * @param <T> tipo de respuesta
     * @return instancia de T parseada
     * @throws IOException si hay error de red o parseo
     */
    public <T> T put(String path, Object payload, Class<T> clazz) throws IOException {
        String json = mapper.writeValueAsString(payload);
        RequestBody body = RequestBody.create(json, JSON);
        Request request = new Request.Builder()
            .url(baseUrl + path)
            .put(body)
            .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected HTTP code: " + response.code());
            }
            String respBody = response.body().string();
            return mapper.readValue(respBody, clazz);
        }
    }

    /**
     * Ejecuta un DELETE sobre la ruta dada.
     * @param path ruta relativa (p.ej. "/vehiculos/123")
     * @return true si el código HTTP fue 2xx
     * @throws IOException si hay error de red
     */
    public boolean delete(String path) throws IOException {
        Request request = new Request.Builder()
            .url(baseUrl + path)
            .delete()
            .build();

        try (Response response = client.newCall(request).execute()) {
            return response.isSuccessful();
        }
    }
}
