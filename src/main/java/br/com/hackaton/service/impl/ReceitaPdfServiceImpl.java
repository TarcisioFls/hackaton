package br.com.hackaton.service.impl;

import br.com.hackaton.exception.ExceptionAdvice;
import br.com.hackaton.service.ReceitaPdfService;
import br.com.hackaton.service.ReceitaService;
import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import static br.com.hackaton.exception.CodigoError.ERROR_DESCONHECIDO;
import static com.lowagie.text.Element.ALIGN_CENTER;
import static com.lowagie.text.Font.BOLD;
import static com.lowagie.text.Font.HELVETICA;
import static com.lowagie.text.PageSize.A4;

@Slf4j
@Service
public class ReceitaPdfServiceImpl implements ReceitaPdfService {

    private final ReceitaService receitaService;

    public ReceitaPdfServiceImpl(ReceitaService receitaService) {
        this.receitaService = receitaService;
    }

    @Override
    public Optional<byte[]> downloadPdf(Long id) {

        var receitaResponse = receitaService.buscarPorId(id);
        try {
            var document = new Document(A4);
            var baos = new ByteArrayOutputStream();
            PdfWriter.getInstance(document, baos);
            document.open();

            var headerFont = new Font(HELVETICA, 12, BOLD);
            var bodyFont = new Font(HELVETICA, 10);

            var header = new Paragraph("Receita Médica", headerFont);
            header.setAlignment(ALIGN_CENTER);
            document.add(header);

            document.add(new Paragraph("Receituario", headerFont));
            document.add(new Paragraph("Nome:" + receitaResponse.getPaciente().getNome(), bodyFont));
            document.add(new Paragraph("Prescrição:" + receitaResponse.getDataCriacao()
                    .format(DateTimeFormatter.ofPattern("dd/MM/yyyy")), bodyFont));

            receitaResponse.getPosologias().forEach(posologia -> {
                document.add(new Paragraph("Medicamento:" + posologia.getMedicamento().getNome(), bodyFont));
                document.add(new Paragraph("Quantidade:" + posologia.getQuantidade(), bodyFont));
                document.add(new Paragraph("Posologia:" + posologia.getDescricao(), bodyFont));
            });

            document.add(new Paragraph("Medico:" + receitaResponse.getMedico().getNome(), bodyFont));

            document.close();

            return Optional.ofNullable(baos.toByteArray());
        } catch (Exception e) {

            log.error("Erro ao gerar pdf", e);
            throw new ExceptionAdvice(ERROR_DESCONHECIDO);
        }

    }
}
