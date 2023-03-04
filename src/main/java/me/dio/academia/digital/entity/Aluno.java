package me.dio.academia.digital.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.dio.academia.digital.entity.form.AlunoForm;
import me.dio.academia.digital.entity.form.AlunoUpdateForm;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_alunos")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Aluno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @Column(unique = true)
    private String cpf;

    private String bairro;

    private LocalDate dataDeNascimento;

    @OneToMany(mappedBy = "aluno", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    private List<AvaliacaoFisica> avaliacoes = new ArrayList<>();

    @OneToOne(mappedBy = "aluno", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    private Matricula matricula;

    public Aluno(AlunoForm form) {
        this.nome = form.getNome();
        this.cpf = form.getCpf();
        this.bairro = form.getBairro();
        this.dataDeNascimento = form.getDataDeNascimento();
    }

    public void update(AlunoUpdateForm formUpdate) {
        if (formUpdate.getNome() != null) {
            this.nome = formUpdate.getNome();
        }
        if (formUpdate.getBairro() != null) {
            this.bairro = formUpdate.getBairro();
        }
        if (formUpdate.getDataDeNascimento() != null) {
            this.dataDeNascimento = formUpdate.getDataDeNascimento();
        }
    }

}