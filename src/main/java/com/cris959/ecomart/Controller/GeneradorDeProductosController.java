package com.cris959.ecomart.Controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/generador")
public class GeneradorDeProductosController {

    private final ChatClient chatClient;

    public GeneradorDeProductosController(ChatClient.Builder chatClientBuilder) {
        this.chatClient = chatClientBuilder.build();
    }

    @GetMapping
    public String generadorProductos() {
        try {
            // El método prompt() inicia la petición a Gemini
            return this.chatClient.prompt()
                    .user("Genera una lista de 5 productos ecológicos para una tienda online")
                    .call()
                    .content();
        } catch (Exception e) {
            // Si ves "Failed to generate content" aquí, revisa la consola de IntelliJ
            // Busca una línea que diga "Cause: ..." para ver el error real de Google
            e.printStackTrace();
            return "Error al conectar con Gemini: " + e.getLocalizedMessage();
        }
    }
    @GetMapping("/test")
    public String test() {
        return "El controlador funciona";
    }
}
