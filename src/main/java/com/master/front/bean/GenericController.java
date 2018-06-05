/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.master.front.bean;

import com.master.front.model.Car;
import com.master.front.service.GenericService;
import com.master.front.util.Utils;
import org.omnifaces.util.Faces;

import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;

/**
 * @author rmpestano
 */
@Named
@ViewScoped
public class GenericController {

    @Inject
    FacesContext facesContext;
}
