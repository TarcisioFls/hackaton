package br.com.hackaton.service.impl;

import br.com.hackaton.controller.request.UbsRequest;
import br.com.hackaton.controller.response.UbsResponse;
import br.com.hackaton.entity.Ubs;
import br.com.hackaton.exception.ExceptionAdvice;
import br.com.hackaton.repository.UbsRepository;
import br.com.hackaton.service.UbsService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import static br.com.hackaton.exception.CodigoError.UBS_NAO_ENCONTRADA;

@Service
public class UbsServiceImpl implements UbsService {

    private final UbsRepository ubsRepository;

    public UbsServiceImpl(UbsRepository ubsRepository) {
        this.ubsRepository = ubsRepository;
    }

    @Override
    public void cria(UbsRequest request) {

        var ubs = new Ubs(request);

        ubsRepository.save(ubs);
    }

    @Override
    public UbsResponse buscarPorId(Long id) {

        var ubs = ubsRepository.findById(id).orElseThrow(() -> new ExceptionAdvice(UBS_NAO_ENCONTRADA));

        return new UbsResponse(ubs);
    }

    @Override
    public UbsResponse atualiza(Long id, UbsRequest request) {

        var ubs = ubsRepository.findById(id).orElseThrow(() -> new ExceptionAdvice(UBS_NAO_ENCONTRADA));

        ubs.atualiza(request);

        ubs = ubsRepository.save(ubs);

        return new UbsResponse(ubs);
    }

    @Override
    public Page<UbsResponse> buscarTodos(int page, int size) {
        
        var pageRequest = PageRequest.of(page, size);

        var ubsPage = ubsRepository.findAll(pageRequest);

        return ubsPage.map(UbsResponse::new);
    }
}
