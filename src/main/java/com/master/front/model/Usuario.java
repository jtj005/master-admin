package com.master.front.model;


import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name="usuarios")
//  USER
public class Usuario implements Serializable {

//    private Long id;

    private String username;

    private String password;

  //  private LocalDateTime dataHoraCadastro;


//    private StatusUsuario statusUsuario;
//
//    @OneToOne(cascade = CascadeType.ALL)
//    private Pessoa pessoa;

//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }


    public Usuario(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

//    public LocalDateTime getDataHoraCadastro() {
//        return dataHoraCadastro;
//    }
//
//    public void setDataHoraCadastro(LocalDateTime dataHoraCadastro) {
//        this.dataHoraCadastro = dataHoraCadastro;
//    }

//    public StatusUsuario getStatusUsuario() {
//        return statusUsuario;
//    }
//
//    public void setStatusUsuario(StatusUsuario statusUsuario) {
//        this.statusUsuario = statusUsuario;
//    }
//
//    public Pessoa getPessoa() {
//        return pessoa;
//    }
//
//    public void setPessoa(Pessoa pessoa) {
//        this.pessoa = pessoa;
//    }
}
