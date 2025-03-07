package br.com.hackaton.utils;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GeoUtilsTest {

    @Nested
    class CalcularDistancia {
        @Test
        void deveCalcularDistanciaCorretamente() {
            // Given
            double latitude1 = -23.550520;
            double longitude1 = -46.633308;
            double latitude2 = -22.906847;
            double longitude2 = -43.172896;

            // When
            double distancia = GeoUtils.calcularDistancia(latitude1, longitude1, latitude2, longitude2);

            // Then
            assertEquals(360, distancia, 1);
        }
        @Test
        void deveCalcularDistanciaZeroParaMesmasCoordenadas() {
            // Given
            double latitude = -23.550520;
            double longitude = -46.633308;

            // When
            double distancia = GeoUtils.calcularDistancia(latitude, longitude, latitude, longitude);

            // Then
            assertEquals(0.0, distancia);
        }
    }

}