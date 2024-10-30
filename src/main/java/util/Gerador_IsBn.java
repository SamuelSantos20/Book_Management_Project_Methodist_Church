package util;

import java.security.SecureRandom;

import org.springframework.stereotype.Service;

/**
 * Serviço para gerar códigos de ISBN (International Standard Book Number).
 */
@Service
public class Gerador_IsBn {

    private SecureRandom random = new SecureRandom(); // Gera números aleatórios de forma segura
    private static final int TAMANHO = 8; // Tamanho da parte gerada do ISBN

    /**
     * Gera um código ISBN no formato "97885XXXXXXXX", onde X representa um número aleatório.
     *
     * @return o código ISBN gerado como uma String.
     */
    public String GeradorCodigo() {
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < TAMANHO; i++) {
            Long number = random.nextLong(10); // Gera um número aleatório entre 0 e 9
            builder.append(number); // Adiciona o número gerado ao StringBuilder
        }
        
        // Retorna o código ISBN gerado com a prefixo "97885"
        return "97885" + builder.toString();
    }
}
