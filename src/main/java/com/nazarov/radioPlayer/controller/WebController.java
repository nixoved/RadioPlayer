package com.nazarov.radioPlayer.controller;

import com.nazarov.radioPlayer.audio.StationSwitcher;
import com.nazarov.radioPlayer.osdependent.PowerOff;
import com.nazarov.radioPlayer.osdependent.VolumeControl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(path="/")
public class WebController extends HttpServlet implements WebMvcConfigurer {

    StationSwitcher stationSwitcher = new StationSwitcher();
    VolumeControl volumeControl = new VolumeControl();
    PowerOff powerOff = new PowerOff();

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView webRadioPlayer() {
        ModelAndView mav = new ModelAndView("webRadioPlayer");
mav.addObject("message", "IIIHAAAA!!");
        return mav;
    }

    @RequestMapping(value = "/test", method = RequestMethod.GET)

    public String printHello(ModelMap model) {
        model.addAttribute("message", "Hello Spring MVC Framework!");
        return "hello";
    }

    @RequestMapping(method = RequestMethod.POST)

    public ModelAndView buttons(@RequestParam(value = "action", required = true) String action) {

        if (action.equals("Ambient")) {
            stationSwitcher.ambient();
        }
        if (action.equals("Jazz")) {
            stationSwitcher.jazz();
        }
        if (action.equals("Trance")) {
            stationSwitcher.trance();
        }
        if (action.equals("Retro")) {
            stationSwitcher.retro();
        }
        if (action.equals("Variable")) {
            stationSwitcher.other();
        }
        if (action.equals("Next_Station")) {
            stationSwitcher.nextStation();
        }
        if (action.equals("Volume_up")) {
            volumeControl.volumeUp();
        }
        if (action.equals("Volume_dn")) {
            volumeControl.volumeDn();
        }
        if (action.equals("Mute")) {
            stationSwitcher.stopRadio();
        }
        if (action.equals("Shutdown")) {
            powerOff.powerOff();
        }

        return webRadioPlayer();
    }

    @RequestMapping("/banner")
    public void bannerJpg(HttpServletRequest request,
                          HttpServletResponse response
    ) {
        String fileName = "banner.png";
        String dataDirectory = request.getServletContext().getRealPath("/WEB-INF/static/images/");
        Path file = Paths.get(dataDirectory, fileName);
        if (Files.exists(file)) {
            response.setContentType("image/png");
        }
        try {
            Files.copy(file, response.getOutputStream());
            response.getOutputStream().flush();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @RequestMapping("/css")
    public void buttonsCss(HttpServletRequest request,
                           HttpServletResponse response
    ) {
        String fileName = "buttons.css";
        String dataDirectory = request.getServletContext().getRealPath("/WEB-INF/static/");
        Path file = Paths.get(dataDirectory, fileName);
        if (Files.exists(file)) {
            response.setContentType("text/css");
        }
        try {
            Files.copy(file, response.getOutputStream());
            response.getOutputStream().flush();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @RequestMapping("/bgr_metall.jpg")
    public void background3(HttpServletRequest request,
                            HttpServletResponse response
    ) {
        String fileName = "bgr_metall.jpg";
        String dataDirectory = request.getServletContext().getRealPath("/WEB-INF/static/images/");
        Path file = Paths.get(dataDirectory, fileName);
        if (Files.exists(file)) {
            response.setContentType("image/jpg");
        }
        try {
            Files.copy(file, response.getOutputStream());
            response.getOutputStream().flush();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @RequestMapping(value = "/help", method = RequestMethod.GET)
    public String help() {

        return "help";
    }
}
