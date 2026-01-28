package com.cris959.ecomart.controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.prompt.ChatOptions;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/categorizador")
public class CategorizadorDeProductosController {

    private final ChatClient chatClient;

    public CategorizadorDeProductosController(ChatClient.Builder chatClientBuilder) {
        this.chatClient = chatClientBuilder.build();
    }


    @GetMapping
    public String categorizadorProductos(String producto) {

            var system = """
    Eres un experto en clasificación de catálogos. Tu objetivo es ayudar al usuario a organizar sus productos.
    
    Cuando recibas un producto, responde con un formato limpio y profesional siguiendo este esquema:
    
    PRODUCTO: [Nombre del producto]
    📂 CATEGORÍA: [Nombre de la Categoría]
    └─ 🏷️ SUBCATEGORÍA: [Nombre de la Subcategoría]
    
    Reglas:
    1. Usa emojis para mejorar la jerarquía visual.
    2. Usa mayúsculas para las categorías principales.
    3. No añadas introducciones como "Aquí tienes la clasificación". Ve directo al grano.
    """;
// Segundo ** prompt **
//    """
//    Eres un experto en gestión de inventarios y categorización de e-commerce.
//    Tu tarea es recibir el nombre de un producto y devolver ÚNICAMENTE la categoría principal y la subcategoría en formato JSON.
//
//    Reglas:
//    1. Si el producto es ambiguo, elige la categoría más probable.
//    2. Responde exclusivamente en el siguiente formato: {"categoria": "nombre", "subcategoria": "nombre"}.
//    3. No incluyas explicaciones, saludos ni puntos finales.
//    4. Usa un tono técnico y estandarizado.
//    """;
//                 **   Primer prompt **
//                    "Tu eres un categorizador de productos";
            return this.chatClient.prompt()
                    .system(system)
                    .user(producto)
                    .options(ChatOptions.builder().temperature(0.90).build())
                    .call()
                    .content();

    }
}
