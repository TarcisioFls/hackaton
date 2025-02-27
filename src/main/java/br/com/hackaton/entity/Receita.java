package br.com.hackaton.entity;

import br.com.hackaton.controller.response.MedicoResponse;
import br.com.hackaton.controller.response.PacienteResponse;
import br.com.hackaton.exception.ExceptionAdvice;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.List;

import static br.com.hackaton.exception.CodigoError.MEDICO_RECEITA_OBRIGATORIO;
import static br.com.hackaton.exception.CodigoError.PACIENTE_RECEITA_OBRIGATORIO;

@Getter
@Entity
@SuperBuilder
@Table(name = "receita")
public class Receita extends BaseEntity {

    @Setter
    @OneToMany(mappedBy = "receita", cascade = CascadeType.ALL)
    private List<Posologia> posologias;

    @ManyToOne
    @JoinColumn(name = "medico_id", unique = false)
    private Medico medico;

    @ManyToOne
    @JoinColumn(name = "paciente_id", unique = false)
    private Paciente paciente;

    public Receita() {}

    public Receita(Long id, Medico medico, Paciente paciente) {

        super(id);
        this.medico = validaMedico(medico);
        this.paciente = validaPaciente(paciente);

    }

    public Receita (Medico medico, Paciente paciente) {

        this.medico = validaMedico(medico);
        this.paciente = validaPaciente(paciente);
    }

    public Receita(MedicoResponse medicoResponse, PacienteResponse pacienteResponse) {

        this.medico = new Medico(medicoResponse);
        this.paciente = new Paciente(pacienteResponse);
    }

    private Paciente validaPaciente(Paciente paciente) {

        if (paciente == null) {
            throw new ExceptionAdvice(PACIENTE_RECEITA_OBRIGATORIO);
        }

        return paciente;
    }

    private Medico validaMedico(Medico medico) {

        if (medico == null) {
            throw new ExceptionAdvice(MEDICO_RECEITA_OBRIGATORIO);
        }

        return medico;
    }
}
