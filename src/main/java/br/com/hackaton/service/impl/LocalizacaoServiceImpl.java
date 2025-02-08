package br.com.hackaton.service.impl;

import br.com.hackaton.service.LocalizacaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LocalizacaoServiceImpl implements LocalizacaoService {

    @Override
    public double calcularDistancia(String lat1, String lon1, String lat2, String lon2) {
        double latitude1 = Double.parseDouble(lat1);
        double longitude1 = Double.parseDouble(lon1);
        double latitude2 = Double.parseDouble(lat2);
        double longitude2 = Double.parseDouble(lon2);

        final int R = 6371; // Raio da Terra em km
        double latDistance = calcularRadianos(latitude2, latitude1);
        double lonDistance = calcularRadianos(longitude2, longitude1);
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(latitude1)) * Math.cos(Math.toRadians(latitude2))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return R * c;
    }

    private static double calcularRadianos(double latitude2, double latitude1) {
        return Math.toRadians(latitude2 - latitude1);
    }
}
