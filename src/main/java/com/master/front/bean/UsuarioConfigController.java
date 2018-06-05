/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.master.front.bean;

import com.master.front.model.Car;
import com.master.front.model.Usuario;
import com.master.front.service.CarService;
import com.master.front.service.GenericService;
import com.master.front.service.UsuarioService;
import com.master.front.util.Utils;
import org.omnifaces.util.Faces;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;
import java.io.Serializable;

import static com.github.adminfaces.template.util.Assert.has;

/**
 * @author rmpestano
 */
@Named
@ViewScoped
public class UsuarioConfigController extends GenericController {

    @Inject
    UsuarioService usuarioService;

    Usuario usuario;

    @PostConstruct
    public void init() {

    }

}
