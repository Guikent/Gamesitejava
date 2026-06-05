package com.example.gamestore;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    private final JogoRepository jogoRepository;

    public DataInitializer(JogoRepository jogoRepository) {
        this.jogoRepository = jogoRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (jogoRepository.count() == 0) {
            jogoRepository.save(new Jogo("Neon Cyberpunk", "Uma cidade futurista onde implantes ditam a vida.", "89.90", "https://images.unsplash.com/photo-1542751371-adc38448a05e?w=500", "Ação"));
            jogoRepository.save(new Jogo("Star Odyssey", "Comande sua frota pelo espaço em busca de um novo lar.", "119.90", "https://images.unsplash.com/photo-1614728894747-a83421e2b9c9?w=500", "Aventura"));
            jogoRepository.save(new Jogo("Shadows of Asylum", "Investigue um sanatório abandonado em um jogo de terror.", "49.90", "https://images.unsplash.com/photo-1511512578047-dfb367046420?w=500", "Terror"));
            jogoRepository.save(new Jogo("Asphalt Overdrive", "Corridas clandestinas em alta velocidade por Tóquio.", "69.90", "https://images.unsplash.com/photo-1560253023-3ec5d502959f?w=500", "Corrida"));
        }
    }
}