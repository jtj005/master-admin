package com.master.front.infra.security;

import com.github.adminfaces.template.session.AdminSession;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.master.front.model.Usuario;
import com.master.front.util.ClientHttp;
import com.master.front.util.Utils;
import org.omnifaces.util.Faces;

import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Specializes;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;


import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;

/**
 * Created by rmpestano on 12/20/14.
 * <p>
 * This is just a login example.
 * <p>
 * AdminSession uses isLoggedIn to determine if user must be redirect to login page or not.
 * By default AdminSession isLoggedIn always resolves to true so it will not try to redirect user.
 * <p>
 * If you already have your authorization mechanism which controls when user must be redirect to initial page or logon
 * you can skip this class.
 */
@Named
@SessionScoped
@Specializes
@Primary
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class LogonController extends AdminSession implements Serializable {

    private ClientHttp clientHttp = new ClientHttp();
    private static final String uri = "http://localhost:8000";
    private static final String uri_login_create = "/login/create";
    private static final String uri_login_token = "/login/token";
    private static final String uri_recupera_senha = "/login/recoverPassword/";
    private FacesContext context = FacesContext.getCurrentInstance();

    private Usuario usuario;
    private String email;
    private String password;
    private String token = "";
    private boolean remember;


    public void login() {
        try {
            usuario = new Usuario(email, password);
            Gson gson = new Gson();
            gson.toJson(usuario);

            Map<String, Object> map = new HashMap<String, Object>();
            map.put("Content-Type", "application/json");

            clientHttp.preparaClientHttp(uri, uri_login_token, map, null);
            Response response = clientHttp.post(gson.toJson(usuario));
            MultivaluedMap<String, Object> headerResponse = response.getHeaders();
            if (headerResponse.containsKey("authorization")) {
                loginRealizado(headerResponse);
            } else
                loginNaoRealizado();
        } catch (Exception e) {
            loginNaoRealizado();
        }
    }

    public void recuperarSenha() {
        try {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("Content-Type", "application/json");

            clientHttp.preparaClientHttp(uri, uri_recupera_senha+email, map, null);
            Response response = clientHttp.get();
            MultivaluedMap<String, Object> headerResponse = response.getHeaders();
            if (headerResponse.containsKey("authorization")) {
                loginRealizado(headerResponse);
            } else
                loginNaoRealizado();
        } catch (Exception e) {
            context.addMessage(null, new FacesMessage("Atenção",  "Email não encontrado") );
        }
    }

    private void loginRealizado(MultivaluedMap<String, Object> headerResponse) throws IOException {
        headerResponse.get("authorization").get(0).toString();
        String token = headerResponse.get("authorization").get(0).toString();
        this.token = token.replace("Bearer", "");
        Faces.setSessionAttribute("token", this.token);
        setIsLoggedIn(true);
        Faces.getExternalContext().getFlash().setKeepMessages(true);
        Faces.redirect("index.jsf");

    }

    public void logOut() {
        loginNaoRealizado();
    }

    private void loginNaoRealizado() {
        setIsLoggedIn(false);
        if (Faces.getSessionAttribute("token") != null) {
            Faces.removeSessionAttribute("token");
        }
        Utils.addDetailMessage("Senha ou Email não existe");
        Faces.getExternalContext().getFlash().setKeepMessages(true);
    }

    @Override
    public boolean isLoggedIn() {
        return !token.isEmpty();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isRemember() {
        return remember;
    }

    public void setRemember(boolean remember) {
        this.remember = remember;
    }


}
