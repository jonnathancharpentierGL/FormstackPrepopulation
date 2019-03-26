package com.davita.controller;

import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.keycloak.representations.AccessToken;
import org.keycloak.representations.IDToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import java.net.URISyntaxException;

@Controller
@RequestMapping("/api/v1")
public class SecurityController {

    @Autowired
    AccessToken accessToken;

    @GetMapping("/formstack")
    public RedirectView redirect(HttpServletRequest request, RedirectAttributes attributes) throws URISyntaxException {
        KeycloakAuthenticationToken principal = (KeycloakAuthenticationToken) request.getUserPrincipal();
        IDToken user = principal.getAccount().getKeycloakSecurityContext().getIdToken();
        attributes.addAttribute("id", user.getId());
        attributes.addAttribute("name", user.getName());
        attributes.addAttribute("email", user.getEmail());

        return new RedirectView("https://davitaoneview.formstack.com/forms/gl_jonnathan");
    }
}
